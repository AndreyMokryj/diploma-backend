package PowerPlantPackage.Workflow;

import PowerPlantPackage.Model.Coordinates;
import PowerPlantPackage.Model.PanelVO;
import PowerPlantPackage.Model.PreviousVO;
import PowerPlantPackage.Model.StateVO;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class WorkProcess {
    private static WorkProcess workProcess;

    private WorkProcess(){
        panels = new ArrayList<Object>();
        restTemplate = new RestTemplate();
        userId = null;
        index = 0;
    }

    public static WorkProcess getInstance(){
        if(workProcess == null){
            workProcess = new WorkProcess();
        }
        return workProcess;
    }

    public final String baseUrl = "http://localhost:4444/";
    public final String sunUrl = "http://localhost:4441/sun/power-coef/";


    public List<Object> panels;
    private RestTemplate restTemplate;
    private String userId;

    private int index;

    public void execute(){
        if(!(userId == null)) {
            panels = (List) (restTemplate.exchange( baseUrl + "panels/", HttpMethod.GET, null, Iterable.class).getBody());
            for (Object object : panels){
                PanelVO panel = PanelVO.fromMap((Map) object);
                double power = getPower(panel);
                if (power >= 10){
                    findSun(panel);
                }
                else {
                    System.out.println("No sun found");
                }
            }
            index++;
            System.out.println("Task executed on " + new Date());
        }
    }

    public void findSun(PanelVO panel) {
        double azPlus = 0;
        double azMinus = 0;
        double altPlus = 0;
        double altMinus = 0;

        while (azPlus >= 0 || azMinus >= 0 || altPlus >= 0 || altMinus >= 0) {
            double prevPower = getPower(panel);
            Random random = new Random();

            StateVO prevState = getState(panel);
            azPlus = prevState.getAzPlus();
            azMinus = prevState.getAzMinus();
            altPlus = prevState.getAltPlus();
            altMinus = prevState.getAltMinus();

            int code = 0;

            int randomInt = random.nextInt(20);
            if (randomInt == 5) {
                code = random.nextInt(4);
            } else {
                if (azPlus >= azMinus && azPlus >= altPlus && azPlus >= altMinus) {
                    code = 2;
                }
                if (azMinus >= azPlus && azMinus >= altPlus && azMinus >= altMinus) {
                    code = 3;
                }
                if (altPlus >= azMinus && altPlus >= azPlus && altPlus >= altMinus) {
                    code = 0;
                }
                if (altMinus >= azMinus && altMinus >= azPlus && altMinus >= altPlus) {
                    code = 1;
                }
            }

            PreviousVO previousVO = new PreviousVO();

            switch (code) {
                case 0:
                    if (panel.getAltitude() < 90) {
                        panel.setAltitude(panel.getAltitude() + 1);
                        break;
                    } else {
                        previousVO.setAltPlus(-1);
                        code++;
                    }

                case 1:
                    if (panel.getAltitude() > 5) {
                        panel.setAltitude(panel.getAltitude() - 1);
                        break;
                    } else {
                        previousVO.setAltMinus(-1);
                        code++;
                    }

                case 2:
                    if (panel.getAzimuth() < 359) {
                        panel.setAzimuth(panel.getAzimuth() + 1);
                    } else {
                        panel.setAzimuth(0);
                    }
                    break;
                case 3:
                    if (panel.getAzimuth() > 0) {
                        panel.setAzimuth(panel.getAzimuth() - 1);
                    } else {
                        panel.setAzimuth(359);
                    }
                    break;
            }

            StateVO newState = getState(panel);
            double newPower = getPower(panel);

            double diff = newPower - prevPower;

            PreviousVO currentVO = new PreviousVO();
            previousVO.setId(prevState.getId());
            currentVO.setId(newState.getId());

            double k = 10;
            switch (code) {
                case 0:
                    previousVO.setAltPlus(diff);
                    currentVO.setAltPlus(diff / k);

                    previousVO.setAltMinus(-diff / k);
                    currentVO.setAltMinus(-diff / k / k);
                    break;
                case 1:
                    previousVO.setAltMinus(diff);
                    currentVO.setAltMinus(diff / k);

                    previousVO.setAltPlus(-diff / k);
                    currentVO.setAltPlus(-diff / k / k);
                    break;
                case 2:
                    previousVO.setAzPlus(diff);
                    currentVO.setAzPlus(diff / k);

                    previousVO.setAzMinus(-diff / k);
                    currentVO.setAzMinus(-diff / k / k);
                    break;
                case 3:
                    previousVO.setAzMinus(diff);
                    currentVO.setAzMinus(diff / k);

                    previousVO.setAzPlus(-diff / k);
                    currentVO.setAzPlus(-diff / k / k);
                    break;
            }

            sendUpdate(previousVO);
            sendUpdate(currentVO);
            updatePanel(panel);
        }

        System.out.println("Panel " + panel.getName() + ": final azimuth: " + panel.getAzimuth() + "; altitude: " + panel.getAltitude() + "; index: " + index + "; power: " + getPower(panel));
    }

    public double getPower(PanelVO panel){
        double coef = 0;
        try {
            coef = restTemplate.postForObject(sunUrl + index, new Coordinates(panel.getAzimuth(), 0,0,panel.getAltitude(),0,0), Double.class);
        }
        catch (Exception e){
            coef = restTemplate.postForObject(sunUrl + "0", new Coordinates(panel.getAzimuth(), 0,0,panel.getAltitude(),0,0), Double.class);
            index = 0;
        }
        return coef * panel.getNominalPower();

    }

    public StateVO getState(PanelVO panel){
        StateVO stateVOSent = new StateVO();
        stateVOSent.setPanelId(panel.getId());
        stateVOSent.setAzimuth(panel.getAzimuth());
        stateVOSent.setAltitude(panel.getAltitude());
        StateVO state = restTemplate.postForObject(baseUrl + "states/get/", stateVOSent, StateVO.class);
        return state;
    }

    public void sendUpdate(PreviousVO previousVO){
        Void response = restTemplate.postForObject(baseUrl + "states/update/", previousVO, void.class);
    }

    public void updatePanel(PanelVO panelVO){
        Void response = restTemplate.postForObject(baseUrl + "panels/" + panelVO.getId(), panelVO, void.class);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RestTemplate getRestTemplate(){
        return restTemplate;
    }
}

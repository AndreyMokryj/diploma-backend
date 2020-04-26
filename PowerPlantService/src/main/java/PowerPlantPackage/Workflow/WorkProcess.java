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
//                System.out.println("Something");
                double prevPower = getPower(panel);
                if (prevPower >= 10){
                    Random random = new Random();

                    StateVO prevState = getState(panel);
                    double azPlus = prevState.getAzPlus();
                    double azMinus = prevState.getAzMinus();
                    double altPlus = prevState.getAltPlus();
                    double altMinus = prevState.getAltMinus();

                    int code = 0;

                    int randomInt = random.nextInt(20);
                    if(randomInt == 5){
                        code = random.nextInt(4);
                    }
                    else {
                        if (azPlus >= azMinus && azPlus >= altPlus && azPlus >= altMinus){
                            code = 0;
                        }
                        if (azMinus >= azPlus && azMinus >= altPlus && azMinus >= altMinus){
                            code = 1;
                        }
                        if (altPlus >= azMinus && altPlus >= azPlus && altPlus >= altMinus){
                            code = 2;
                        }
                        if (altMinus >= azMinus && altMinus >= azPlus && altMinus >= altPlus){
                            code = 3;
                        }
                    }

                    switch (code){
                        case 0:
                            panel.setAzimuth(panel.getAzimuth() + 1);
                            break;
                        case 1:
                            panel.setAzimuth(panel.getAzimuth() - 1);
                            break;
                        case 2:
                            panel.setAltitude(panel.getAltitude() + 1);
                            break;
                        case 3:
                            panel.setAltitude(panel.getAltitude() - 1);
                            break;
                    }

                    StateVO newState = getState(panel);
                    double newPower = getPower(panel);

                    double diff = newPower - prevPower;
                    PreviousVO previousVO = new PreviousVO();
                    PreviousVO currentVO = new PreviousVO();
                    previousVO.setId(prevState.getId());
                    currentVO.setId(newState.getId());
                    switch (code){
                        case 0:
                            previousVO.setAzPlus(diff);
                            currentVO.setAzPlus(diff / 10);
                            break;
                        case 1:
                            previousVO.setAzMinus(diff);
                            currentVO.setAzMinus(diff / 10);
                            break;
                        case 2:
                            previousVO.setAltPlus(diff);
                            currentVO.setAltPlus(diff / 10);
                            break;
                        case 3:
                            previousVO.setAltMinus(diff);
                            currentVO.setAltMinus(diff / 10);
                            break;
                    }

                    sendUpdate(previousVO);
                }
                else {
                    System.out.println("No sun found");
                }
            }
            index++;
            System.out.println("Task executed on " + new Date());
        }
    }

    public double getPower(PanelVO panel){
        double coef = restTemplate.postForObject(sunUrl + index, new Coordinates(panel.getAzimuth(), 0,0,panel.getAltitude(),0,0), Double.class);
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
        restTemplate.postForObject(baseUrl + "states/update/", previousVO, void.class);
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

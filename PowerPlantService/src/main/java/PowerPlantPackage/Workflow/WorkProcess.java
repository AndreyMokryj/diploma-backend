package PowerPlantPackage.Workflow;

import PowerPlantPackage.Model.Coordinates;
import PowerPlantPackage.Model.PanelVO;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkProcess {
    private static WorkProcess workProcess;

    private WorkProcess(){
        panels = new ArrayList<PanelVO>();
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


    public List<PanelVO> panels;
    private RestTemplate restTemplate;
    private String userId;

    private int index;

    public void execute(){
        if(!(userId == null)) {
            panels = (List) (restTemplate.exchange( baseUrl + "panels/", HttpMethod.GET, null, Iterable.class).getBody());
            for (Object object : panels){
                PanelVO panel = (PanelVO) object;
                System.out.println("Something");
                double prevPower = getPower(panel);
                System.out.println("Previous power: " + prevPower);
            }

            System.out.println("Task executed on " + new Date());
        }
    }

    public double getPower(PanelVO panel){
        double coef = restTemplate.postForObject(sunUrl + index, new Coordinates(panel.getAzimuth(), 0,0,panel.getAltitude(),0,0), double.class);
        return coef * panel.getNominalPower();
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

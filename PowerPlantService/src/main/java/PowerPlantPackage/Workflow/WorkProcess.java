package PowerPlantPackage.Workflow;

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
    public List<PanelVO> panels;
    private RestTemplate restTemplate;
    private String userId;

    private int index;
//    private String previousId;
//    private String currentId;

    public void execute(){
        if(!(userId == null)) {
            panels = (List<PanelVO>) (restTemplate.exchange( baseUrl + "panels/", HttpMethod.GET, null, Iterable.class).getBody());
            for (PanelVO panel : panels){
                int a = 0;
            }

            System.out.println("Task executed on " + new Date());
        }
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

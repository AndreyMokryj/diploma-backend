package PowerPlantPackage.Workflow;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

public class WorkProcess {
    private static WorkProcess workProcess;

    private WorkProcess(){
//        panels = new ArrayList<PanelE>();
        restTemplate = new RestTemplate();
        userId = null;
    }

    public static WorkProcess getInstance(){
        if(workProcess == null){
            workProcess = new WorkProcess();
        }
        return workProcess;
    }

    public List panels;
    private RestTemplate restTemplate;
    private String userId;

    public void execute(){
        if(!(userId == null)) {
            panels = (List) (restTemplate.exchange("http://localhost:4444/panels/", HttpMethod.GET, null, Iterable.class).getBody());
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

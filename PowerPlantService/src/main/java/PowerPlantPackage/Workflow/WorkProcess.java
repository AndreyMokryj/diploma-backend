package PowerPlantPackage.Workflow;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

public class WorkProcess {
    private static WorkProcess workProcess;

    private WorkProcess(){
//        panels = new ArrayList<PanelE>();
        restTemplate = new RestTemplate();
    }

    public static WorkProcess getInstance(){
        if(workProcess == null){
            workProcess = new WorkProcess();
        }
        return workProcess;
    }

    public List panels;
    private RestTemplate restTemplate;

    public void execute(){
        String url = "";
        try {
            url = InetAddress.getLocalHost().getHostAddress()  ;
        } catch (UnknownHostException e) {

        }
        String userId = restTemplate.postForObject("http://localhost:4444/connect/", url, String.class);
//        String userId1 = restTemplate.exchange("http://localhost:4444/connect/", HttpMethod.GET, null, String.class).getBody();
        panels = (List) (restTemplate.exchange("http://localhost:4444/panels/", HttpMethod.GET, null, Iterable.class).getBody());
        System.out.println("Task executed on " + new Date());
    }
}

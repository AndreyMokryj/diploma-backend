package PowerPlantPackage.Workflow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements ApplicationListener<ServletWebServerInitializedEvent> {
    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
//        int port = event.getWebServer().getPort();
//        String host = "";
//        String url = "";
//
//        try {
//            host = InetAddress.getLocalHost().getHostAddress() ;
//        } catch (UnknownHostException e) {
//        }
//
//        if (port == 443){
//            url = "https://" + host + "/";
//        }
//        else {
//            url = "http://" + host + ":" + port + "/";
//        }

        String userId = null;
        while (userId == null) {
            try {
                userId = WorkProcess.getInstance().getRestTemplate().postForObject(WorkProcess.getInstance().baseUrl + "connect/", serviceName, String.class);
                Thread.sleep(10000L);
            } catch (Exception e) {
            }
        }
        WorkProcess.getInstance().setUserId(userId);
    }
}

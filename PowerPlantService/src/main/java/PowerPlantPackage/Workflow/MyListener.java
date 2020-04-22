package PowerPlantPackage.Workflow;

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class MyListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        String host = "";
        String url = "";

        try {
            host = InetAddress.getLocalHost().getHostAddress() ;
        } catch (UnknownHostException e) {
        }

        if (port == 443){
            url = "https://" + host + "/";
        }
        else {
            url = "http://" + host + ":" + port + "/";
        }

        String userId = WorkProcess.getInstance().getRestTemplate().postForObject("http://localhost:4444/connect/", url, String.class);
        WorkProcess.getInstance().setUserId(userId);
    }
}

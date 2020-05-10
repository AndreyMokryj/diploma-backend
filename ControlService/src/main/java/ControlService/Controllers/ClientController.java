package ControlService.Controllers;

import ControlService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path="/app")
@Component
public class ClientController {
    //Requests from app
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/panels/userId/{userId}")
    public @ResponseBody
    Iterable<Object> getByUserIdA(@PathVariable String userId) {
        String sid = userRepository.findSID(userId);
        List<Object> response = restTemplate.exchange("http://" + sid + "/panels/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>() {}).getBody();

        return response;
    }
}

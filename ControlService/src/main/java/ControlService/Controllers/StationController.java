package ControlService.Controllers;

import ControlService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/connect")
@Component
public class StationController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping(path="/")
    public @ResponseBody
    String getUserId(@RequestBody String url) {
//        return userRepository.findByUrl(url);
        return userRepository.findBySID(url);
    }
}

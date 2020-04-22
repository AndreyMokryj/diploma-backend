package ControlService.Controllers;

import ControlService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/connect")
@Component
public class StationController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping(path="/")
    public @ResponseBody
    String getUserId(@RequestBody String url) {
        return userRepository.findByUrl(url);
//        return userRepository.findByUrl(request.getURI().getHost());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public String getUserIdg(HttpServletRequest request) {
        String url = request.getHeader("X-FORWARDED-FOR");
        return userRepository.findByUrl(url);
//        return userRepository.findByUrl(request.getURI().getHost());
    }
}

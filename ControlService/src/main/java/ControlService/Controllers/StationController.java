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
    @GetMapping(path="/")
    public @ResponseBody
    String getUserId(HttpServletRequest request) {
        String uri = request.getRemoteHost();
//        String uri = request.getRequestURI();
//uri = "/context/someAction"
        String url = request.getRequestURL().toString();
// url = "http://server.name:8080/context/someAction"
        String ctxPath = request.getContextPath();
// ctxPath = "/context";
        url = url.replaceFirst(uri, "");
// url = "http://server.name:8080"

        return userRepository.findByUrl(url);
//        return userRepository.findByUrl(request.getURI().getHost());
    }
}

package ControlService.Controllers;

import ControlService.Entities.SessionE;
import ControlService.Repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/sessions")
@Component
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<SessionE> getAll() {
        // This returns a JSON or XML with the users
        return sessionRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/mid/{mid}")
    public @ResponseBody
    Iterable<SessionE> getByMID(@PathVariable long mid) {
        // This returns a JSON or XML with the users
        return sessionRepository.findByMID(mid);
    }
}

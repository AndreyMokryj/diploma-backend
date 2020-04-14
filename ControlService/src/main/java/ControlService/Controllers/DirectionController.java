package ControlService.Controllers;

import ControlService.Entities.DirectionE;
import ControlService.Repositories.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/directions")
@Component
public class DirectionController {
    @Autowired
    private DirectionRepository directionRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<DirectionE> getAll() {
        return directionRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/{id}")
    public @ResponseBody
    DirectionE getById(@PathVariable String id) {
        Optional<DirectionE> direction = directionRepository.findById(id);
        return direction.get();
    }
}

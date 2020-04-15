package ControlService.Controllers;

import ControlService.Entities.DirectionE;
import ControlService.Entities.UserE;
import ControlService.Repositories.DirectionRepository;
import ControlService.vo.DirectionVO;
import ControlService.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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

    private DirectionE upsertDirection(DirectionVO directionVO) {
        DirectionE direction = DirectionE.fromVO(directionVO);
        try {
            Optional<DirectionE> direction1 = directionRepository.findByParams(
                    direction.getPanelId(),
                    direction.getPower(),
                    direction.getAzimuth(),
                    direction.getAltitude()
                );
            return direction1.get();
        }
        catch (Exception ex){
            direction.setId(UUID.randomUUID().toString());
            DirectionE saved = directionRepository.save(direction);
            return saved;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/get/")
    public DirectionE getDir(@RequestBody DirectionVO directionVO) {
        DirectionE direction = upsertDirection(directionVO);
        return direction;
    }
}

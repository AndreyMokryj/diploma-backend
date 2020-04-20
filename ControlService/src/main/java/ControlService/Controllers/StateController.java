package ControlService.Controllers;

import ControlService.Entities.StateE;
import ControlService.Repositories.StateRepository;
import ControlService.vo.StateVO;
import ControlService.vo.PreviousVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/states")
@Component
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<StateE> getAll() {
        return stateRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/{id}")
    public @ResponseBody
    StateE getById(@PathVariable String id) {
        Optional<StateE> direction = stateRepository.findById(id);
        return direction.get();
    }

    private StateE getState(StateVO stateVO) {
        StateE direction = StateE.fromVO(stateVO);
        try {
            Optional<StateE> direction1 = stateRepository.findByParams(
                    direction.getPanelId(),
//                    direction.getPower(),
                    direction.getAzimuth(),
                    direction.getAltitude()
                );
            return direction1.get();
        }
        catch (Exception ex){
            direction.setId(UUID.randomUUID().toString());
            StateE saved = stateRepository.save(direction);
            return saved;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/get/")
    public StateE getDir(@RequestBody StateVO stateVO) {
        StateE direction = getState(stateVO);
        return direction;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/update/")
    public void updatePrevDirection(@RequestBody PreviousVO previousVO) {
        StateE direction = getById(previousVO.getId());
        direction.setAltPlus(direction.getAltPlus() + previousVO.getAltPlus());
        direction.setAltMinus(direction.getAltMinus() + previousVO.getAltMinus());
        direction.setAzPlus(direction.getAzPlus() + previousVO.getAzPlus());
        direction.setAzMinus(direction.getAzMinus() + previousVO.getAzMinus());
        stateRepository.save(direction);
    }
}

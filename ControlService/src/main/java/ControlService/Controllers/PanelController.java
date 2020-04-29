package ControlService.Controllers;

import ControlService.Entities.PanelE;
import ControlService.Entities.StateE;
import ControlService.Repositories.PanelRepository;
import ControlService.Repositories.StateRepository;
import ControlService.vo.PanelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/panels")
@Component
public class PanelController {
    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    private StateRepository stateRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<PanelE> getAll() {
        return panelRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/userId/{userId}")
    public @ResponseBody
    Iterable<PanelE> getByUserId(@PathVariable String userId) {
        return panelRepository.findByUserId(userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/{id}")
    public @ResponseBody
    PanelE getById(@PathVariable String id) {
        Optional<PanelE> panel = panelRepository.findById(id);
        return panel.get();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/{id}")
    public @ResponseBody
    void updateById(@PathVariable String id, @RequestBody PanelVO panelVO) {
        PanelE panel = PanelE.fromVO(panelVO);
        panelRepository.save(panel);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/reduce/{panelId}")
    public void reduceForUser(@PathVariable String panelId) {
        int k = 10;

        List<StateE> states = (List<StateE>) stateRepository.findByPanelId(panelId);
        PanelE panel = getById(panelId);
        for (StateE state : states) {
            if(state.getAltitude() == panel.getAltitude() && state.getAzimuth() == panel.getAzimuth()){
                state.setAzPlus(0.1);
                state.setAzMinus(0);
                state.setAltPlus(0);
                state.setAltMinus(0);
            }
            else {
                state.setAzPlus(state.getAzPlus() / k);
                state.setAzMinus(state.getAzMinus() / k);
                state.setAltPlus(state.getAltPlus() / k);
                state.setAltMinus(state.getAltMinus() / k);
            }
        }

        stateRepository.saveAll(states);

    }
}

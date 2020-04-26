package ControlService.Controllers;

import ControlService.Entities.PanelE;
import ControlService.Repositories.PanelRepository;
import ControlService.vo.PanelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/panels")
@Component
public class PanelController {
    @Autowired
    private PanelRepository panelRepository;

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
}

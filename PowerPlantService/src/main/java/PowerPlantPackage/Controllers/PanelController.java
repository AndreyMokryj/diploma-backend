package PowerPlantPackage.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/panels")
@Component
public class PanelController {
//    @Autowired
//    private PanelRepository panelRepository;
//
//    @CrossOrigin(origins = "*")
//    @GetMapping(path="/")
//    public @ResponseBody
//    Iterable<PanelE> getAll() {
//        return panelRepository.findAll();
//    }
//
//    @CrossOrigin(origins = "*")
//    @GetMapping(path="/{id}")
//    public @ResponseBody
//    PanelE getById(@PathVariable String id) {
//        Optional<PanelE> panel = panelRepository.findById(id);
//        return panel.get();
//    }
}

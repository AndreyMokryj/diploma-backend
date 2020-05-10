package PowerPlantPackage.Controllers;

import PowerPlantPackage.Model.PanelVO;
import PowerPlantPackage.Workflow.WorkProcess;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/panels")
@Component
public class PanelController {
    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<PanelVO> getAll() {
        return WorkProcess.getInstance().panels;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/{id}")
    public @ResponseBody
    PanelVO getById(@PathVariable String id) {
        for (PanelVO panel : WorkProcess.getInstance().panels){
            if (panel.getId().equals(id)){
                return panel;
            }
        }
        return null;
    }
}

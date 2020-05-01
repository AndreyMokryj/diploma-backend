package GridPackage.Controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping(path="/power")
@Component
public class PowerController {
    private double nominalVoltage = 31.8;
    private double nominalPower = 280;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/coef/")
    public @ResponseBody
    double getPower() {
        Random random = new Random();
        double coef = (random.nextInt(5) + 5) / 10.0;
        return coef;
    }
}

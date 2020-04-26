package SunPackage1.Controllers;

import SunPackage1.Coordinates.Coordinates;
import SunPackage1.Coordinates.SunCoordinatesList;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/sun")
@Component
public class SunController {
    @CrossOrigin(origins = "*")
    @GetMapping(path="/{index}")
    public @ResponseBody
    Coordinates getCoords(@PathVariable int index) {
        return SunCoordinatesList.getCoordinates(index);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/power-coef/{index}")
    public @ResponseBody
    double getPowerCoef(@RequestBody Coordinates coordinates, @PathVariable int index) {
        Coordinates sunCoordinates = SunCoordinatesList.getCoordinates(index);
        if(sunCoordinates == null){
            return -1;
        }
        double daz = Math.abs(coordinates.getAzimuth() - sunCoordinates.getAzimuth());
        double dalt = Math.abs(coordinates.getAltitude() - sunCoordinates.getAltitude());
        if (daz >= 90 || dalt >= 90){
            return 0;
        }
        double coef = Math.cos(daz * Math.PI / 180.0) * Math.cos(dalt * Math.PI / 180.0);
        return coef;
    }
}

package ControlService.Controllers;

import ControlService.Entities.PlaceE;
import ControlService.Entities.UserE;
import ControlService.Repositories.PlaceRepository;
import ControlService.Repositories.UserRepository;
import ControlService.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/places")
@Component
public class PlaceController {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    private UserE retrieveUser(String username) {
        try {
            Optional<UserE> user = userRepository.findByUN(username);
            return user.get();
        }
        catch (Exception ex){
            return null;
        }
    }

    private PlaceE getPlace(long id) {
        try {
            Optional<PlaceE> place = placeRepository.findById(id);
            return place.get();
        }
        catch (Exception ex){
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<PlaceE> getAll() {
        return placeRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/sid/{sid}")
    public @ResponseBody
    Iterable<PlaceE> getBySID(@PathVariable long sid) {
        // This returns a JSON or XML with the users
        return placeRepository.findBySID(sid);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/user/")
    public @ResponseBody
    Iterable<PlaceE> getByUN(@RequestBody UserVO userVO) {
        // This returns a JSON or XML with the users
        return placeRepository.findByUN(userVO.getUsername());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/select/{id}")
    public boolean selectPlace(@PathVariable long id, @RequestBody UserVO userVO) {
        PlaceE place = getPlace(id);
        if(place.getStatus() == 0 && retrieveUser(userVO.getUsername()) != null){
            place.setStatus(1);
            place.setUsername(userVO.getUsername());
            placeRepository.save(place);
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/unselect/{id}")
    public void unselectPlace(@PathVariable long id) {
        PlaceE place = getPlace(id);
        place.setStatus(0);
        place.setUsername(null);
        placeRepository.save(place);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/unbook/")
    public void unbookPlaces(@RequestBody Iterable<Long> ids) {
        for (Long id : ids) {
            PlaceE placeE = getPlace(id);
            placeE.setStatus(0);
            placeE.setUsername(null);
            placeRepository.save(placeE);
        }
    }
}

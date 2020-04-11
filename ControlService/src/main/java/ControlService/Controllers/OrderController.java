package ControlService.Controllers;

import ControlService.Entities.OrderE;
import ControlService.Entities.PlaceE;
import ControlService.Entities.UserE;
import ControlService.Repositories.OrderRepository;
import ControlService.Repositories.PlaceRepository;
import ControlService.Repositories.UserRepository;
import ControlService.vo.OrderVO;
import ControlService.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/orders")
@Component
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

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

    public void unbookPlaces(Iterable<Long> ids) {
        for (Long id : ids) {
            PlaceE placeE = getPlace(id);
            placeE.setStatus(0);
            placeE.setUsername(null);
            placeRepository.save(placeE);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<OrderE> getAll() {
        return orderRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/user/")
    public @ResponseBody
    Iterable<OrderE> getByUN(@RequestBody UserVO user) {
        // This returns a JSON or XML with the users
        return orderRepository.findByUN(user.getUsername());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/pay/")
    public boolean payOrder(@RequestBody OrderVO orderVO) {
        OrderE order = OrderE.fromVO(orderVO);
        if (retrieveUser(orderVO.getUsername()) == null){
            return false;
        }

        order.setStatus(1);
        orderRepository.save(order);
        return true;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/cancel/")
    public boolean cancelOrder(@RequestBody OrderVO orderVO) {
        OrderE order = OrderE.fromVO(orderVO);
        if (retrieveUser(orderVO.getUsername()) == null){
            return false;
        }

        unbookPlaces(orderVO.getPlaceIds());
        order.setStatus(-1);
        orderRepository.save(order);
        return true;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/book/")
    public boolean bookPlaces(@RequestBody OrderVO orderVO) {
        OrderE order = OrderE.fromVO(orderVO);
        if (retrieveUser(orderVO.getUsername()) == null){
            return false;
        }

        for (Long id : orderVO.getPlaceIds()) {
            PlaceE placeE = getPlace(id);
            if (placeE.getStatus() != 2) {
                placeE.setStatus(2);
                placeRepository.save(placeE);
            }
            else {
                return false;
            }
        }
        orderRepository.save(order);

        for (Long id : orderVO.getPlaceIds()) {
            PlaceE placeE = getPlace(id);
            placeE.setUsername(orderVO.getUsername());
            placeRepository.save(placeE);
        }
        return true;
    }
}

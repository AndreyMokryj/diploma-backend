package ControlService.Controllers;

import ControlService.Entities.UserE;
import ControlService.vo.UserVO;
import ControlService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/users")
@Component
public class UserController {
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

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public boolean createUser(@RequestBody UserVO userVO) {
        UserE user = UserE.fromVO(userVO);
        UserE savedUser;
        if (retrieveUser(user.getUsername()) == null) {
            user.setId(UUID.randomUUID().toString());
            savedUser = userRepository.save(user);
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/check/")
    public boolean checkUser(@RequestBody UserVO userVO) {
        UserE user = UserE.fromVO((UserVO) userVO);
        UserE savedUser;
        try {
            savedUser = retrieveUser(user.getUsername());
            return savedUser.getPassword().equals(user.getPassword());
        }
        catch (Exception e) {
            return false;
        }

    }
}

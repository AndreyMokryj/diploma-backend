package ControlService.Entities;

import ControlService.vo.UserVO;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class UserE {
    @Id
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String name) {
        this.password = name;
    }

    public static UserE fromVO(UserVO userVO){
        UserE user = new UserE();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        return user;
    }

    public String toLog(){
        String x  = "{username: " + getUsername() + ", password: " + getPassword() + "}";
        return x;
    }

    public String toString(){
        return username;
    }
}

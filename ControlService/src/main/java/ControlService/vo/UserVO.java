package ControlService.vo;

public class UserVO {
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

    public String toString(){
        String x  = "{username: " + getUsername() + ", password: " + getPassword() + "}";
        return x;
    }
}

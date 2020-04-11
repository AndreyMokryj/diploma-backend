package ControlService.vo;

import java.util.List;

public class OrderVO {
    private Long id;
    private String username;
    private int sum;
    private int status;

    private List<Long> placeIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Long> getPlaceIds() {
        return placeIds;
    }

    public void setPlaceIds(List<Long> placeIds) {
        this.placeIds = placeIds;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

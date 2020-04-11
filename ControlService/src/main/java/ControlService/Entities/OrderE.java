package ControlService.Entities;

import ControlService.vo.OrderVO;

import javax.persistence.*;

//@Where(clause = "status = 0")
@Entity(name = "orders")
public class OrderE {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String username;

    @Column(name="order_status")
    private int status;

    @Column(name="order_sum")
    private int sum;

    @Column(name="place_ids")
    private String placeIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public static OrderE fromVO(OrderVO orderVO){
        OrderE order = new OrderE();
        order.setId(orderVO.getId());
        order.setUsername(orderVO.getUsername());
        order.setStatus(orderVO.getStatus());
        order.setSum(orderVO.getSum());
        String ids = "";
        for (Long id : orderVO.getPlaceIds()){
            ids += id + " ";
        }
        order.setPlaceIds(ids);
        return order;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPlaceIds() {
        return placeIds;
    }

    public void setPlaceIds(String placeIds) {
        this.placeIds = placeIds;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

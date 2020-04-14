package ControlService.Entities;

import ControlService.vo.PanelVO;
import ControlService.vo.UserVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "panels")
public class PanelE {
    @Id
    private String id;

    @Column(name = "panel_name")
    private String name;

    @Column(name = "panel_model")
    private String model;

    @Column(name = "nominal_power")
    private int nominalPower;

    @Column(name = "user_id")
    private String userId;


    public static PanelE fromVO(PanelVO panelVO){
        PanelE panel = new PanelE();
        panel.setId(panelVO.getId());
        panel.setName(panelVO.getName());
        panel.setModel(panelVO.getModel());
        panel.setNominalPower(panelVO.getNominalPower());
        panel.setUserId(panelVO.getUserId());
        return panel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNominalPower() {
        return nominalPower;
    }

    public void setNominalPower(int nominalPower) {
        this.nominalPower = nominalPower;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

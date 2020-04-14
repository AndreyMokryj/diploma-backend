package ControlService.Entities;

import ControlService.vo.DirectionVO;
import ControlService.vo.PanelVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "directions")
public class DirectionE {
    @Id
    private String id;

    @Column(name = "panel_id")
    private String panelId;
    private int power;
    private int azimuth;
    private int altitude;

    @Column(name = "az_plus")
    private int azPlus;

    @Column(name = "az_minus")
    private int azMinus;

    @Column(name = "alt_plus")
    private int altPlus;

    @Column(name = "alt_minus")
    private int altMinus;

    public static DirectionE fromVO(DirectionVO directionVO){
        DirectionE direction = new DirectionE();
        direction.setId(directionVO.getId());
        direction.setPanelId(directionVO.getPanelId());
        direction.setPower(directionVO.getPower());
        direction.setAzimuth(directionVO.getAzimuth());
        direction.setAltitude(directionVO.getAltitude());
        direction.setAzPlus(directionVO.getAzPlus());
        direction.setAzMinus(directionVO.getAzMinus());
        direction.setAltPlus(directionVO.getAltPlus());
        direction.setAltMinus(directionVO.getAltMinus());
        return direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(int azimuth) {
        this.azimuth = azimuth;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getAzPlus() {
        return azPlus;
    }

    public void setAzPlus(int azPlus) {
        this.azPlus = azPlus;
    }

    public int getAzMinus() {
        return azMinus;
    }

    public void setAzMinus(int azMinus) {
        this.azMinus = azMinus;
    }

    public int getAltPlus() {
        return altPlus;
    }

    public void setAltPlus(int altPlus) {
        this.altPlus = altPlus;
    }

    public int getAltMinus() {
        return altMinus;
    }

    public void setAltMinus(int altMinus) {
        this.altMinus = altMinus;
    }
}

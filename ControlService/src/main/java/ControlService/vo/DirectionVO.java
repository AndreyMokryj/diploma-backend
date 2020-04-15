package ControlService.vo;

public class DirectionVO {
    private String id;
    private String panelId;
    private double power;
    private int azimuth;
    private int altitude;
    private int azPlus;
    private int azMinus;
    private int altPlus;
    private int altMinus;

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

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
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

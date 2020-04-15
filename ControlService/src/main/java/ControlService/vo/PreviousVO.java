package ControlService.vo;

public class PreviousVO {
    private String id;

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

package ControlService.vo;

public class AccumulatorVO {
    private String id;
    private int maxPower;
    private double energy;
    private int gridStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public int getGridStatus() {
        return gridStatus;
    }

    public void setGridStatus(int gridStatus) {
        this.gridStatus = gridStatus;
    }
}

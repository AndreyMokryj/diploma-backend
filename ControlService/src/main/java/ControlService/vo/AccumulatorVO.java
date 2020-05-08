package ControlService.vo;

public class AccumulatorVO {
    private String id;
    private int maxPower;
    private double energy;
    private int gridConnection;
    private int stationConnection;

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

    public int getGridConnection() {
        return gridConnection;
    }

    public void setGridConnection(int gridConnection) {
        this.gridConnection = gridConnection;
    }

    public int getStationConnection() {
        return stationConnection;
    }

    public void setStationConnection(int stationConnection) {
        this.stationConnection = stationConnection;
    }
}

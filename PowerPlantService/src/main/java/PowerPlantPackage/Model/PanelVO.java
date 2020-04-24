package PowerPlantPackage.Model;

public class PanelVO {
    private String id;
    private String name;
    private String model;
    private int nominalPower;
    private String userId;

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

package ControlService.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "history_logs")
public class HistoryLogE {
    @Id
    private String id;

    @Column(name = "date_time")
    private String dateTime;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "panel_id")
    private String panelId;

    private double produced;
    private double given;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getProduced() {
        return produced;
    }

    public void setProduced(double produced) {
        this.produced = produced;
    }

    public double getGiven() {
        return given;
    }

    public void setGiven(double given) {
        this.given = given;
    }
}

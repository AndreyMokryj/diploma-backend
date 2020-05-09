package ControlService.Entities;

import ControlService.vo.LogVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "history_given_logs")
public class HistoryGivenLogE {
    @Id
    private String id;

    @Column(name = "date_time")
    private String dateTime;

    @Column(name = "user_id")
    private String userId;

    private double given;

    public static HistoryGivenLogE fromVO(LogVO logVO){
        HistoryGivenLogE historyLog = new HistoryGivenLogE();
        historyLog.setId(logVO.getId());
        historyLog.setUserId(logVO.getUserId());

        String dateTime = logVO.getDateTime().substring(0, 10);
        historyLog.setDateTime(dateTime);

        return historyLog;
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getGiven() {
        return given;
    }

    public void setGiven(double given) {
        this.given = given;
    }
}
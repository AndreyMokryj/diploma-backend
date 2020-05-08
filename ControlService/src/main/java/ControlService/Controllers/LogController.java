package ControlService.Controllers;

import ControlService.Entities.HistoryGivenLogE;
import ControlService.Entities.HistoryProducedLogE;
import ControlService.Entities.TodayGivenLogE;
import ControlService.Entities.TodayProducedLogE;
import ControlService.Repositories.HistoryGivenLogRepository;
import ControlService.Repositories.HistoryProducedLogRepository;
import ControlService.Repositories.TodayGivenLogRepository;
import ControlService.Repositories.TodayProducedLogRepository;
import ControlService.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/logs")
@Component
public class LogController {
    @Autowired
    private HistoryProducedLogRepository historyProducedLogRepository;

    @Autowired
    private TodayProducedLogRepository todayProducedLogRepository;

    @Autowired
    private HistoryGivenLogRepository historyGivenLogRepository;

    @Autowired
    private TodayGivenLogRepository todayGivenLogRepository;

    private HistoryProducedLogE getHistoryProducedLog(LogVO logVO) {
        HistoryProducedLogE historyLog = HistoryProducedLogE.fromVO(logVO);
        try {
            Optional<HistoryProducedLogE> historyLog1 = historyProducedLogRepository.findByParams(
                    historyLog.getUserId(),
                    historyLog.getPanelId(),
                    historyLog.getDateTime()
            );
            return historyLog1.get();
        }
        catch (Exception ex){
            historyLog.setId(UUID.randomUUID().toString());
            HistoryProducedLogE saved = historyProducedLogRepository.save(historyLog);
            return saved;
        }
    }

    private HistoryGivenLogE getHistoryGivenLog(LogVO logVO) {
        HistoryGivenLogE historyLog = HistoryGivenLogE.fromVO(logVO);
        try {
            Optional<HistoryGivenLogE> historyLog1 = historyGivenLogRepository.findByParams(
                    historyLog.getUserId(),
                    historyLog.getDateTime()
            );
            return historyLog1.get();
        }
        catch (Exception ex){
            historyLog.setId(UUID.randomUUID().toString());
            HistoryGivenLogE saved = historyGivenLogRepository.save(historyLog);
            return saved;
        }
    }

    private TodayProducedLogE getTodayProducedLog(LogVO logVO) {
        TodayProducedLogE todayLog = TodayProducedLogE.fromVO(logVO);
        try {
            Optional<TodayProducedLogE> todayLog1 = todayProducedLogRepository.findByParams(
                    todayLog.getUserId(),
                    todayLog.getPanelId(),
                    todayLog.getTime()
            );
            return todayLog1.get();
        }
        catch (Exception ex){
            todayLog.setId(UUID.randomUUID().toString());
            TodayProducedLogE saved = todayProducedLogRepository.save(todayLog);
            return saved;
        }
    }

    private TodayGivenLogE getTodayGivenLog(LogVO logVO) {
        TodayGivenLogE todayLog = TodayGivenLogE.fromVO(logVO);
        try {
            Optional<TodayGivenLogE> todayLog1 = todayGivenLogRepository.findByParams(
                    todayLog.getUserId(),
//                    todayLog.getPanelId(),
                    todayLog.getTime()
            );
            return todayLog1.get();
        }
        catch (Exception ex){
            todayLog.setId(UUID.randomUUID().toString());
            TodayGivenLogE saved = todayGivenLogRepository.save(todayLog);
            return saved;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/update/")
    public @ResponseBody
    void updateLogs(@RequestBody LogVO logVO) {
        if (logVO.getPanelId() == null){
            HistoryGivenLogE historyLog = getHistoryGivenLog(logVO);
//            historyLog.setProduced(historyLog.getProduced() + logVO.getProduced());
            historyLog.setGiven(historyLog.getGiven() + logVO.getGiven());
            historyGivenLogRepository.save(historyLog);

            if(logVO.getDateTime().contains("00:00:00")){
                todayGivenLogRepository.deleteByUserId(logVO.getUserId());
            }

            TodayGivenLogE todayLog = getTodayGivenLog(logVO);
//            todayLog.setProduced(todayLog.getProduced() + logVO.getProduced());
            todayLog.setGiven(todayLog.getGiven() + logVO.getGiven());
            todayGivenLogRepository.save(todayLog);
        }
        else {
            HistoryProducedLogE historyLog = getHistoryProducedLog(logVO);
            historyLog.setProduced(historyLog.getProduced() + logVO.getProduced());
//            historyLog.setGiven(historyLog.getGiven() + logVO.getGiven());
            historyProducedLogRepository.save(historyLog);

            if(logVO.getDateTime().contains("00:00:00")){
                todayProducedLogRepository.deleteByPanelId(logVO.getPanelId());
            }

            TodayProducedLogE todayLog = getTodayProducedLog(logVO);
            todayLog.setProduced(todayLog.getProduced() + logVO.getProduced());
//            todayLog.setGiven(todayLog.getGiven() + logVO.getGiven());
            todayProducedLogRepository.save(todayLog);
        }
    }
}

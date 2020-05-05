package ControlService.Controllers;

import ControlService.Entities.HistoryLogE;
import ControlService.Entities.TodayLogE;
import ControlService.Repositories.HistoryLogRepository;
import ControlService.Repositories.TodayLogRepository;
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
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private TodayLogRepository todayLogRepository;

    private HistoryLogE getHistoryLog(LogVO logVO) {
        HistoryLogE historyLog = HistoryLogE.fromVO(logVO);
        try {
            Optional<HistoryLogE> historyLog1 = historyLogRepository.findByParams(
                    historyLog.getUserId(),
                    historyLog.getPanelId(),
                    historyLog.getDateTime()
            );
            return historyLog1.get();
        }
        catch (Exception ex){
            historyLog.setId(UUID.randomUUID().toString());
            HistoryLogE saved = historyLogRepository.save(historyLog);
            return saved;
        }
    }

    private TodayLogE getTodayLog(LogVO logVO) {
        TodayLogE todayLog = TodayLogE.fromVO(logVO);
        try {
            Optional<TodayLogE> todayLog1 = todayLogRepository.findByParams(
                    todayLog.getUserId(),
                    todayLog.getPanelId(),
                    todayLog.getTime()
            );
            return todayLog1.get();
        }
        catch (Exception ex){
            todayLog.setId(UUID.randomUUID().toString());
            TodayLogE saved = todayLogRepository.save(todayLog);
            return saved;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/update/")
    public @ResponseBody
    void updateLogs(@RequestBody LogVO logVO) {

    }
}

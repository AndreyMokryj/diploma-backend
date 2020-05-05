package ControlService.Controllers;

import ControlService.Repositories.HistoryLogRepository;
import ControlService.Repositories.TodayLogRepository;
import ControlService.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/logs")
@Component
public class LogController {
    @Autowired
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private TodayLogRepository todayLogRepository;

    @CrossOrigin(origins = "*")
    @PostMapping(path="/update/")
    public @ResponseBody
    void updateById(@RequestBody LogVO logVO) {

    }
}

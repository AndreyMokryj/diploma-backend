package ControlService.Repositories;

import ControlService.Entities.TodayLogE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TodayLogRepository extends CrudRepository<TodayLogE, String> {
    @Query("SELECT tl FROM today_logs tl where tl.userId = :userId and tl.panelId = :panelId and tl.time = :time")
    @Transactional
    public Optional<TodayLogE> findByParams(String userId, String panelId, String time);

    @Query("DELETE FROM today_logs tl where tl.panelId = :panelId")
    @Transactional
    public void deleteByPanelId(String panelId);
}

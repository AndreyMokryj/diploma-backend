package ControlService.Repositories;

import ControlService.Entities.TodayProducedLogE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TodayProducedLogRepository extends CrudRepository<TodayProducedLogE, String> {
    @Query("SELECT tl FROM today_produced_logs tl where tl.userId = :userId and tl.panelId = :panelId and tl.time = :time")
    @Transactional
    public Optional<TodayProducedLogE> findByParams(String userId, String panelId, String time);

    @Query("DELETE FROM today_produced_logs tl where tl.panelId = :panelId")
    @Transactional
    public void deleteByPanelId(String panelId);
}

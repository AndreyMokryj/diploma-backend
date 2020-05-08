package ControlService.Repositories;

import ControlService.Entities.TodayGivenLogE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TodayGivenLogRepository extends CrudRepository<TodayGivenLogE, String> {
    @Query("SELECT tl FROM today_given_logs tl where tl.userId = :userId and tl.panelId = :panelId and tl.time = :time")
    @Transactional
    public Optional<TodayGivenLogE> findByParams(String userId, String panelId, String time);

    @Query("DELETE FROM today_given_logs tl where tl.panelId = :panelId")
    @Transactional
    public void deleteByPanelId(String panelId);
}

package ControlService.Repositories;

import ControlService.Entities.HistoryLogE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface HistoryLogRepository extends CrudRepository<HistoryLogE, String> {
    @Query("SELECT hl FROM history_logs hl where hl.userId = :userId and hl.panelId = :panelId and hl.dateTime = :dateTime")
    @Transactional
    public Optional<HistoryLogE> findByParams(String userId, String panelId, String dateTime);
}

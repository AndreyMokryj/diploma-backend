package ControlService.Repositories;

import ControlService.Entities.HistoryGivenLogE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface HistoryGivenLogRepository extends CrudRepository<HistoryGivenLogE, String> {
    @Query("SELECT hl FROM history_given_logs hl where hl.userId = :userId and hl.dateTime = :dateTime")
    @Transactional
    public Optional<HistoryGivenLogE> findByParams(String userId, String dateTime);
}

package ControlService.Repositories;

import ControlService.Entities.SessionE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

public interface SessionRepository extends CrudRepository<SessionE, Long> {
    @Query("SELECT s FROM sessions s where s.movieId = :idd")
    @Transactional
    public Iterable<SessionE> findByMID(@PathVariable Long idd);
}

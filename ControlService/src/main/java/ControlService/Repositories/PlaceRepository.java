package ControlService.Repositories;

import ControlService.Entities.PlaceE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

public interface PlaceRepository extends CrudRepository<PlaceE, Long> {
    @Query("SELECT p FROM places p where p.sessionId = :idd")
    @Transactional
    public Iterable<PlaceE> findBySID(@PathVariable Long idd);

    @Query("SELECT p FROM places p where p.username = :un and  p.status = 1")
    @Transactional
    public Iterable<PlaceE> findByUN(@PathVariable String un);
}

package ControlService.Repositories;

import ControlService.Entities.OrderE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrderRepository extends CrudRepository<OrderE, Long> {
    @Query("SELECT o FROM orders o where o.username = :un")
    @Transactional
    public Iterable<OrderE> findByUN(@PathVariable String un);
}

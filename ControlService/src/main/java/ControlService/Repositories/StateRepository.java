package ControlService.Repositories;

import ControlService.Entities.StateE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StateRepository extends CrudRepository<StateE, String> {
//    @Query("SELECT d FROM directions d where d.panelId = :panelId and d.power = :power and d.azimuth = :az and d.altitude = :alt")
//    @Transactional
//    public Optional<StateE> findByParams(String panelId, int power, int az, int alt);

    @Query("SELECT ps FROM panel_states ps where ps.panelId = :panelId and ps.azimuth = :az and ps.altitude = :alt")
    @Transactional
    public Optional<StateE> findByParams(String panelId, double az, double alt);
}

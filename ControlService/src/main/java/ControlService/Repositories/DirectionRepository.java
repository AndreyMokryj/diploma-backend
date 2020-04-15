package ControlService.Repositories;

import ControlService.Entities.DirectionE;
import ControlService.Entities.UserE;
import ControlService.vo.DirectionVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DirectionRepository extends CrudRepository<DirectionE, String> {
    @Query("SELECT d FROM directions d where d.panelId = :panelId and d.power = :power and d.azimuth = :az and d.altitude = :alt")
    @Transactional
    public Optional<DirectionE> findByParams(String panelId, int power, int az, int alt);
}

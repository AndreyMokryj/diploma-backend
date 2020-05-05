package ControlService.Repositories;

import ControlService.Entities.TodayLogE;
import org.springframework.data.repository.CrudRepository;

public interface TodayLogRepository extends CrudRepository<TodayLogE, String> { }

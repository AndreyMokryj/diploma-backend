package ControlService.Repositories;

import ControlService.Entities.MovieE;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieE, Long> { }

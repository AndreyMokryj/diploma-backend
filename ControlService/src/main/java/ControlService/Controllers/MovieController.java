package ControlService.Controllers;

import ControlService.Entities.MovieE;
import ControlService.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/movies")
@Component
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<MovieE> getAll() {
        return movieRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/{id}")
    public @ResponseBody
    MovieE getById(@PathVariable long id) {
        Optional<MovieE> movie = movieRepository.findById(id);
        return movie.get();
    }
}

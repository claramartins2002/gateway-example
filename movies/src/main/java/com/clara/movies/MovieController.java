package com.clara.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<Movie>> getAll(@PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Movie> moviePage = movieService.findAll(pageable);
        if(moviePage.isEmpty()){
            return new ResponseEntity<>(NOT_FOUND);
        }
        else {
            return new ResponseEntity<Page<Movie>>(moviePage, OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable(value="id") String id){
        Movie found= movieService.findById(id);

        return new ResponseEntity<Movie>(found, OK);
    }

    @PutMapping("/{id}")
    public Movie edit(@PathVariable(value="id") String id, @RequestBody Movie movie){
        Movie found= movieService.findById(id);

        return movieService.edit(movie, movie.getId().toString());

    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie){
        return new ResponseEntity<Movie>(movieService.save(movie), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> delete(@PathVariable(value="id") String id) {
        Movie movie= movieService.findById(id);

        return movieService.delete(movie);
    }
}

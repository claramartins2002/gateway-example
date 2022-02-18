package com.clara.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public Page<Movie> findAll(Pageable pageable) {

            return movieRepository.findAll(pageable);

    }

    public Movie findById(String id) {
        Optional<Movie> optional= movieRepository.findById(Long.valueOf(id));
        return optional.orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public Movie save (Movie movie) { return  movieRepository.save(movie);}

    public ResponseEntity<Movie> delete (Movie movie) { movieRepository.delete(movie);
        return null;
    }
    public Movie edit (Movie live, String id) { Movie instancia = this.findById(id);
        return movieRepository.save(live);}
}

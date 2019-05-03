package com.projectpitang.contenthub.controllers;

import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.models.Program;
import com.projectpitang.contenthub.models.TV;
import com.projectpitang.contenthub.services.MovieService;
import com.projectpitang.contenthub.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
@CrossOrigin("http://localhost:4200")
public class ProgramController {

    @Autowired
    private MovieService movieService;


    public MovieService getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies(Pageable pageable){

        Page<Movie> movies = this.movieService.getAll(pageable);
        return new ResponseEntity<>(movies, HttpStatus.OK);

    }

    @GetMapping("/movies/title/{title}")
    public ResponseEntity<?> findMovieByTitle(Pageable pageable, @PathVariable String title){

        Page<Movie> movies = this.movieService.findMovieByTitle(pageable, title);

        if(movies != null){
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/movies/language/{language}")
    public ResponseEntity<?> findMovieByLanguage(Pageable pageable, @PathVariable String language){

        Page<Movie> movies = this.movieService.findMovieByLanguage(pageable, language);

        if(movies != null){
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/movies/date/{date}")
    public ResponseEntity<?> findMovieByDate(Pageable pageable, @PathVariable String date){

        Page<Movie> movies = this.movieService.findMovieByDate(pageable, date);

        if(movies != null){
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @PutMapping("/movie")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateMovie(@RequestParam Long id, @RequestBody Movie movie){

        Movie movieUpdated = this.movieService.updateMovie(id,movie);

        if(movieUpdated != null){
            return new ResponseEntity<>(movieUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /*@PutMapping("/tv")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateTv(@RequestParam Long id, @RequestBody TV tv){

        TV tvUpdated = this.programService.updateTv(id,tv);

        if(tvUpdated != null){
            return new ResponseEntity<>(tvUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteProgram(@RequestBody Long id){

        boolean programExists = this.programService.deleteProgram(id);
        if(programExists){
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }*/
}

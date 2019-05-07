package com.projectpitang.contenthub.controllers;

import com.projectpitang.contenthub.dto.MovieDTO;
import com.projectpitang.contenthub.dto.TvDTO;
import com.projectpitang.contenthub.error.ResourceNotFoundException;
import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.models.TV;
import com.projectpitang.contenthub.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/program")
@CrossOrigin("http://localhost:4200")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    public ProgramService getProgramService() {
        return programService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies(Pageable pageable){

        Page<Movie> movies = this.programService.getAllMovies(pageable);
        return new ResponseEntity<>(movies, HttpStatus.OK);

    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id){

        Movie movie = this.programService.getMovieById(id);

        if(movie != null){
            return new ResponseEntity<>(movie.transformToMovieDTO(), HttpStatus.OK);
        }

        throw new ResourceNotFoundException("There is no movie with this id!");

    }

    @GetMapping("/tvs")
    public ResponseEntity<?> getAllTvs(Pageable pageable){

        Page<TV> tvs = this.programService.getAllTvs(pageable);
        return new ResponseEntity<>(tvs, HttpStatus.OK);

    }

    @GetMapping("/tvs/{id}")
    public ResponseEntity<?> getTvById(@PathVariable Long id){

        TV tv = this.programService.getTvById(id);

        if(tv != null){
            return new ResponseEntity<>(tv.transformToTvDTO(), HttpStatus.OK);
        }

        throw new ResourceNotFoundException("There is no movie with this id!");

    }

    @GetMapping("/movies/title/{title}")
    public ResponseEntity<?> findMovieByTitle(Pageable pageable, @PathVariable String title){

        Page<Movie> movies = this.programService.findMovieByTitle(pageable, title);

        if(movies != null){
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/tvs/title/{title}")
    public ResponseEntity<?> findTvByTitle(Pageable pageable, @PathVariable String title){

        Page<TV> tvs = this.programService.findTvByTitle(pageable, title);

        if(tvs != null){
            return new ResponseEntity<>(tvs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/movies/language/{language}")
    public ResponseEntity<?> findMovieByLanguage(Pageable pageable, @PathVariable String language){

        Page<Movie> movies = this.programService.findMovieByLanguage(pageable, language);

        if(movies != null){
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/tvs/language/{language}")
    public ResponseEntity<?> findTvByLanguage(Pageable pageable, @PathVariable String language){

        Page<TV> tvs = this.programService.findTvByLanguage(pageable, language);

        if(tvs != null){
            return new ResponseEntity<>(tvs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/movies/date/{date}")
    public ResponseEntity<?> findMovieByDate(Pageable pageable, @PathVariable String date){

        Page<Movie> movies = this.programService.findMovieByDate(pageable, date);

        if(movies != null){
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/tvs/date/{date}")
    public ResponseEntity<?> findTvByDate(Pageable pageable, @PathVariable String date){

        Page<TV> tvs = this.programService.findTvByDate(pageable, date);

        if(tvs != null){
            return new ResponseEntity<>(tvs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @PutMapping("update/movie")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateMovie(@RequestParam Long id, @RequestBody MovieDTO movieDTO){

        Movie movieUpdated = this.programService.updateMovie(id, movieDTO.transformToMovie());

        if(movieUpdated != null){
            return new ResponseEntity<>(movieUpdated.transformToMovieDTO(), HttpStatus.OK);
        }

        throw new ResourceNotFoundException("There is no movie with this id to be updated!");

    }

    @PutMapping("update/tv")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateTv(@RequestParam Long id, @RequestBody TvDTO tvDTO){

        TV tvUpdated = this.programService.updateTv(id, tvDTO.transformToTv());

        if(tvUpdated != null){
            return new ResponseEntity<>(tvUpdated.transformToTvDTO(), HttpStatus.OK);
        }

        throw new ResourceNotFoundException("There is no tv with this id to be updated!");

    }

    @DeleteMapping("delete/movie")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteMovie(@RequestBody Long id){

        boolean movieDeleted = this.programService.deleteMovie(id);

        if(movieDeleted){
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        throw new ResourceNotFoundException("There is no movie with this id to be deleted!");

    }

    @DeleteMapping("delete/tv")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteTv(@RequestBody Long id){

        boolean tvDeleted = this.programService.deleteTv(id);

        if(tvDeleted){
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        throw new ResourceNotFoundException("There is no tv with this id to be deleted!");

    }
}

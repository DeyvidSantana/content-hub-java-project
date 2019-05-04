package com.projectpitang.contenthub.controllers;

import com.projectpitang.contenthub.models.Genre;
import com.projectpitang.contenthub.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private IGenreRepository IGenreRepository;

    public IGenreRepository getIGenreRepository() {
        return IGenreRepository;
    }

    public void setIGenreRepository(IGenreRepository IGenreRepository) {
        this.IGenreRepository = IGenreRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){

        Page<Genre> genres = this.IGenreRepository.findAll(pageable);
        return new ResponseEntity<>(genres, HttpStatus.OK);

    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createGenre(@Valid @RequestBody Genre genre){

        this.IGenreRepository.save(genre);
        return new ResponseEntity<>(genre, HttpStatus.CREATED);

    }

    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateGenre(@RequestParam Long id, @RequestBody Genre genreUpdated){

        return null;

    }

    @DeleteMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteGenre(@RequestBody Long id){

        boolean genreExists = this.IGenreRepository.existsById(id);
        if(genreExists){
            this.IGenreRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return null;
        }

    }
}

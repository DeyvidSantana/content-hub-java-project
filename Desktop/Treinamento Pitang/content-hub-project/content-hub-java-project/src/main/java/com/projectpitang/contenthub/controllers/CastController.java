package com.projectpitang.contenthub.controllers;

import com.projectpitang.contenthub.models.Artist;
import com.projectpitang.contenthub.models.Cast;
import com.projectpitang.contenthub.models.Director;
import com.projectpitang.contenthub.models.Person;
import com.projectpitang.contenthub.repository.CastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cast")
public class CastController {

    @Autowired
    private CastRepository castRepository;

    public CastRepository getCastRepository() {
        return castRepository;
    }

    public void setCastRepository(CastRepository castRepository) {
        this.castRepository = castRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){

        Page<Cast> casts = this.castRepository.findAll(pageable);
        return new ResponseEntity<>(casts, HttpStatus.OK);

    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createCast(@Valid @RequestBody Cast cast){

        this.castRepository.save(cast);
        return new ResponseEntity<>(cast, HttpStatus.CREATED);

    }

    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateCast(@RequestParam Long id, @RequestBody Cast castUpdated){

        return null;

    }

    @DeleteMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteCast(@RequestBody Long id){

        boolean castExists = this.castRepository.existsById(id);
        if(castExists){
            this.castRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return null;
        }

    }
}

package com.projectpitang.contenthub.controllers;

import com.projectpitang.contenthub.models.Cast;
import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.models.Program;
import com.projectpitang.contenthub.models.TV;
import com.projectpitang.contenthub.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/program")
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;

    public ProgramRepository getProgramRepository() {
        return programRepository;
    }

    public void setProgramRepository(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){

        Page<Program> programs = this.programRepository.findAll(pageable);
        return new ResponseEntity<>(programs, HttpStatus.OK);

    }

    @PostMapping("movie")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createMovie(@Valid @RequestBody Movie movie){

        this.programRepository.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);

    }

    @PostMapping("tv")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createTv(@Valid @RequestBody TV tv){

        this.programRepository.save(tv);
        return new ResponseEntity<>(tv, HttpStatus.CREATED);

    }

    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateProgram(@RequestParam Long id, @RequestBody Cast programUpdated){

        return null;

    }

    @DeleteMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteProgram(@RequestBody Long id){

        boolean programExists = this.programRepository.existsById(id);
        if(programExists){
            this.programRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return null;
        }

    }
}

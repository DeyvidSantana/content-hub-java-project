package com.projectpitang.contenthub.controllers;

import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.models.Program;
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
public class ProgramController {

    @Autowired
    private ProgramService programService;

    public ProgramService getProgramService() {
        return programService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){

        Page<Program> programs = this.programService.getAll(pageable);
        return new ResponseEntity<>(programs, HttpStatus.OK);

    }

    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateMovie(@RequestParam Long id, @RequestBody Movie movie){

        Movie movieUpdated = this.programService.updateMovie(id,movie);

        if(movieUpdated != null){
            return new ResponseEntity<>(movieUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping
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

    }
}

package com.projectpitang.contenthub.controllers;

import com.projectpitang.contenthub.models.Artist;
import com.projectpitang.contenthub.models.Author;
import com.projectpitang.contenthub.models.Director;
import com.projectpitang.contenthub.models.Person;
import com.projectpitang.contenthub.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/people")
@CrossOrigin("http://localhost:4200")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){

        Page<Person> people = this.personRepository.findAll(pageable);
        return new ResponseEntity<>(people, HttpStatus.OK);

    }

    @PostMapping("/artist")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createArtist(@Valid @RequestBody Artist artist){

        this.personRepository.save(artist);
        return new ResponseEntity<>(artist, HttpStatus.CREATED);

    }

    @PostMapping("/author")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createAuthor(@Valid @RequestBody Author author){

        this.personRepository.save(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);

    }

    @PostMapping("director")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createDirector(@Valid @RequestBody Director director){

        this.personRepository.save(director);
        return new ResponseEntity<>(director, HttpStatus.CREATED);

    }

    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updatePerson(@RequestParam Long id, @RequestBody Director directorUpdated){

        return null;

    }

    @DeleteMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deletePerson(@RequestBody Long id){

        boolean personExists = this.personRepository.existsById(id);
        if(personExists){
            this.personRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return null;
        }

    }
}

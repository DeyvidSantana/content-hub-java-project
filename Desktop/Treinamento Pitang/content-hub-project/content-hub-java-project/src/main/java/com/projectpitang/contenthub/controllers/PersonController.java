package com.projectpitang.contenthub.controllers;

import com.projectpitang.contenthub.dto.PersonDTO;
import com.projectpitang.contenthub.models.Person;
import com.projectpitang.contenthub.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
@CrossOrigin("http://localhost:4200")
public class PersonController {

    @Autowired
    private PersonService personService;

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){

        Page<Person> people = this.personService.getAll(pageable);
        return new ResponseEntity<>(people, HttpStatus.OK);

    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> findPersonByName(Pageable pageable, @PathVariable String name){

        Page<Person> people = this.personService.findPersonByName(pageable, name);

        if(people != null){
            return new ResponseEntity<>(people, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updatePerson(@RequestParam Long id, @RequestBody PersonDTO personDTO){

        Person personUpdated = this.personService.updatePerson(id,personDTO.transformToPerson());

        if(personUpdated != null){
            return new ResponseEntity<>(personUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deletePerson(@RequestBody Long id){

        boolean personExists = this.personService.deletePerson(id);
        if(personExists){
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return null;
        }

    }
}

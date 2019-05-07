package com.projectpitang.contenthub.services;

import com.projectpitang.contenthub.models.Person;
import com.projectpitang.contenthub.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private IPersonRepository iPersonRepository;

    public IPersonRepository getiPersonRepository() {
        return iPersonRepository;
    }

    public void setiPersonRepository(IPersonRepository iPersonRepository) {
        this.iPersonRepository = iPersonRepository;
    }

    public Page<Person> getAll(Pageable pageable){
        return this.iPersonRepository.findAll(pageable);
    }

    public Person getPersonById(Long id){
        return this.iPersonRepository.findById(id).get();
    }

    public Page<Person> findPersonByName(Pageable pageable, String name){

        Page<Person> people = this.iPersonRepository.findByNameContainingIgnoreCase(pageable, name);

        if(people != null){
            return people;
        } else {
            return null;
        }

    }

    public Person updatePerson(Long id, Person person){

        Optional<Person> personToBeUpdated = this.iPersonRepository.findById(id);

        if(personToBeUpdated != null){
            personToBeUpdated.get().setId(id);
            personToBeUpdated.get().setName(person.getName());
            personToBeUpdated.get().setHeight(person.getHeight());
            personToBeUpdated.get().setHometown(person.getHometown());
            personToBeUpdated.get().setHomeCountry(person.getHomeCountry());
            personToBeUpdated.get().setGender(person.getGender());

            this.iPersonRepository.save(personToBeUpdated.get());

            return personToBeUpdated.get();
        } else {
            return null;
        }
    }

    public boolean deletePerson(Long id){

        boolean personExists = this.iPersonRepository.existsById(id);

        if(personExists){
            this.iPersonRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

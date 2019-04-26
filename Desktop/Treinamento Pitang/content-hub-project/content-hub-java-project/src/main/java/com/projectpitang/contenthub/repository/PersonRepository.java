package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {

    public abstract List<Person> findAll();

    public abstract List<Person> findByNameLike(String name);

    public abstract Optional<Person> findById(Long id);

    public abstract void deleteById(Long id);

}

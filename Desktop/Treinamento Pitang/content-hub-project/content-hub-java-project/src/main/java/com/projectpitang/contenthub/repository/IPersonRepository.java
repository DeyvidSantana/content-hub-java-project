package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Long> {

    public abstract List<Person> findAll();

    public abstract Page<Person> findByNameContainingIgnoreCase(Pageable pageable, String name);

    public abstract Optional<Person> findById(Long id);

    public abstract void deleteById(Long id);

}

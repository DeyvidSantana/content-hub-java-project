package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.Genre;
import com.projectpitang.contenthub.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    public abstract List<Genre> findAll();

    public abstract List<Genre> findByNameLike(String name);

    public abstract Optional<Genre> findById(Long id);

    public abstract void deleteById(Long id);

}

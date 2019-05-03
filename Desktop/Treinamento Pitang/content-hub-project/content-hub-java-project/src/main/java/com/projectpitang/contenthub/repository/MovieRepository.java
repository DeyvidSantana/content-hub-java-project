package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    public abstract List<Movie> findAll();

    public abstract List<Movie> findByTitleLike(String title);

    public abstract Optional<Movie> findById(Long id);

    public abstract void deleteById(Long id);

}

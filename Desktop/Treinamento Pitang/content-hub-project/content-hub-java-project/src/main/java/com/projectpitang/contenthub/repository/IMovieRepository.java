package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMovieRepository extends JpaRepository<Movie,Long> {

    public abstract List<Movie> findAll();

    public abstract Page<Movie> findByTitleContainingIgnoreCase(Pageable pageable, String title);

    public abstract Page<Movie> findByLanguageLike(Pageable pageable, String language);

    public abstract Page<Movie> findByReleaseDateContainingIgnoreCase(Pageable pageable, String releaseYear);

    public abstract Optional<Movie> findById(Long id);

    public abstract void deleteById(Long id);

}

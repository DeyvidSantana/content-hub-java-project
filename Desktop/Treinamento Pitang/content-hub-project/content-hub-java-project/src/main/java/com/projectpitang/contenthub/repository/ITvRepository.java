package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.TV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITvRepository extends JpaRepository<TV,Long> {

    public abstract List<TV> findAll();

    public abstract Page<TV> findByTitleContainingIgnoreCase(Pageable pageable, String title);

    public abstract Page<TV> findByLanguageLike(Pageable pageable, String language);

    public abstract Page<TV> findByReleaseDateContainingIgnoreCase(Pageable pageable, String releaseYear);

    public abstract Optional<TV> findById(Long id);

    public abstract void deleteById(Long id);

}

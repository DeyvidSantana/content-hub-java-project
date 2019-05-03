package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.TV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TvRepository extends JpaRepository<TV,Long> {

    public abstract List<TV> findAll();

    public abstract List<TV> findByTitleLike(String title);

    public abstract Optional<TV> findById(Long id);

    public abstract void deleteById(Long id);

}

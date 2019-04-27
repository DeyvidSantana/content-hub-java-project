package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CastRepository extends JpaRepository<Cast,Long> {

    public abstract List<Cast> findAll();

    public abstract List<Cast> findByNameLike(String name);

    public abstract Optional<Cast> findById(Long id);

    public abstract void deleteById(Long id);

}

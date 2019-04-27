package com.projectpitang.contenthub.repository;

import com.projectpitang.contenthub.models.Person;
import com.projectpitang.contenthub.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {

    public abstract List<Program> findAll();

    public abstract List<Program> findByTitleLike(String title);

    public abstract Optional<Program> findById(Long id);

    public abstract void deleteById(Long id);

}

package com.projectpitang.contenthub.services;

import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.models.Program;
import com.projectpitang.contenthub.models.TV;
import com.projectpitang.contenthub.repository.MovieRepository;
import com.projectpitang.contenthub.repository.ProgramRepository;
import com.projectpitang.contenthub.repository.TvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TvRepository tvRepository;

    public ProgramRepository getProgramRepository() {
        return programRepository;
    }

    public void setProgramRepository(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public TvRepository getTvRepository() {
        return tvRepository;
    }

    public void setTvRepository(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    public Page<Program> getAll(Pageable pageable){
        return this.programRepository.findAll(pageable);
    }

    public List<Program> findProgramByTitle(String title){

        List<Program> programs = this.findProgramByTitle(title);

        if(programs.size() != 0){
            return programs;
        } else {
            return null;
        }

    }

    public List<Program> findProgramByLanguage(String language){

        List<Program> programs = this.findProgramByLanguage(language);

        if(programs.size() != 0){
            return programs;
        } else {
            return null;
        }

    }

    public List<Program> findProgramByDate(String date){

        List<Program> programs = this.programRepository.findByReleaseYearLike(date);

        if(programs.size() != 0){
            return programs;
        } else {
            return null;
        }

    }

    public Movie updateMovie(Long id, Movie movie){

        Optional<Movie> movieToBeUpdated = this.movieRepository.findById(id);

        if(movieToBeUpdated != null){
            Movie movieUpdated = new Movie();
            movieUpdated.setId(movieToBeUpdated.get().getId());
            movieUpdated.setIdApi(movieToBeUpdated.get().getIdApi());
            movieUpdated.setTitle(movieToBeUpdated.get().getTitle());
            movieUpdated.setOverview(movieToBeUpdated.get().getOverview());
            movieUpdated.setOriginCountry(movieToBeUpdated.get().getOriginCountry());
            movieUpdated.setLanguage(movieToBeUpdated.get().getLanguage());
            movieUpdated.setReleaseYear(movieToBeUpdated.get().getReleaseYear());
            movieUpdated.setRuntime(movieToBeUpdated.get().getRuntime());

            this.movieRepository.save(movieUpdated);

            return movieUpdated;
        } else {
            return null;
        }

    }

    public TV updateTv(Long id, TV tv){

        Optional<TV> tvToBeUpdated = this.tvRepository.findById(id);

        if(tvToBeUpdated != null){
            TV tvUpdated = new TV();
            tvUpdated.setId(tvToBeUpdated.get().getId());
            tvUpdated.setIdApi(tvToBeUpdated.get().getIdApi());
            tvUpdated.setTitle(tvToBeUpdated.get().getTitle());
            tvUpdated.setOverview(tvToBeUpdated.get().getOverview());
            tvUpdated.setOriginCountry(tvToBeUpdated.get().getOriginCountry());
            tvUpdated.setLanguage(tvToBeUpdated.get().getLanguage());
            tvUpdated.setReleaseYear(tvToBeUpdated.get().getReleaseYear());
            tvUpdated.setRuntime(tvToBeUpdated.get().getRuntime());

            this.tvRepository.save(tvUpdated);

            return tvUpdated;
        } else {
            return null;
        }

    }

    public boolean deleteProgram(Long id){

        boolean programExists = this.programRepository.existsById(id);
        if(programExists){
            this.programRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }
}

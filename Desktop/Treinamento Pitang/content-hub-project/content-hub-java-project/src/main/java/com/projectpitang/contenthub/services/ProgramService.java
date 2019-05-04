package com.projectpitang.contenthub.services;

import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.models.TV;
import com.projectpitang.contenthub.repository.IMovieRepository;
import com.projectpitang.contenthub.repository.ITvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private IMovieRepository iMovieRepository;

    @Autowired
    private ITvRepository iTvRepository;

    public IMovieRepository getiMovieRepository() {
        return iMovieRepository;
    }

    public void setiMovieRepository(IMovieRepository iMovieRepository) {
        this.iMovieRepository = iMovieRepository;
    }

    public ITvRepository getiTvRepository() {
        return iTvRepository;
    }

    public void setiTvRepository(ITvRepository iTvRepository) {
        this.iTvRepository = iTvRepository;
    }

    // Methods related to film.

    public Page<Movie> getAllMovies(Pageable pageable){
        return this.iMovieRepository.findAll(pageable);
    }

    public Page<Movie> findMovieByTitle(Pageable pageable, String title){

        Page<Movie> movies = this.iMovieRepository.findByTitleContainingIgnoreCase(pageable, title);

        if(movies != null){
            return movies;
        } else {
            return null;
        }

    }

    public Page<Movie> findMovieByLanguage(Pageable pageable, String language){

        Page<Movie> movies = this.iMovieRepository.findByLanguageLike(pageable, language);

        if(movies != null){
            return movies;
        } else {
            return null;
        }

    }

    public Page<Movie> findMovieByDate(Pageable pageable, String date){

        Page<Movie> movies = this.iMovieRepository.findByReleaseYearLike(pageable, date);

        if(movies != null){
            return movies;
        } else {
            return null;
        }

    }

    public Movie updateMovie(Long id, Movie movie){

        Optional<Movie> movieToBeUpdated = this.iMovieRepository.findById(id);

        if(movieToBeUpdated != null){
            movieToBeUpdated.get().setId(id);
            movieToBeUpdated.get().setTitle(movie.getTitle());
            movieToBeUpdated.get().setOverview(movie.getOverview());
            movieToBeUpdated.get().setOriginCountry(movie.getOriginCountry());
            movieToBeUpdated.get().setLanguage(movie.getLanguage());
            movieToBeUpdated.get().setReleaseYear(movie.getReleaseYear());
            movieToBeUpdated.get().setRuntime(movie.getRuntime());

            this.iMovieRepository.save(movieToBeUpdated.get());

            return movieToBeUpdated.get();
        } else {
            return null;
        }

    }

    public boolean deleteMovie(Long id){

        boolean movieExists = this.iMovieRepository.existsById(id);
        if(movieExists){
            this.iMovieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    // Methods related to tv.

    public Page<TV> getAllTvs(Pageable pageable){
        return this.iTvRepository.findAll(pageable);
    }

    public Page<TV> findTvByTitle(Pageable pageable, String title){

        Page<TV> tvs = this.iTvRepository.findByTitleContainingIgnoreCase(pageable, title);

        if(tvs != null){
            return tvs;
        } else {
            return null;
        }

    }

    public Page<TV> findTvByLanguage(Pageable pageable, String language){

        Page<TV> tvs = this.iTvRepository.findByLanguageLike(pageable, language);

        if(tvs != null){
            return tvs;
        } else {
            return null;
        }

    }

    public Page<TV> findTvByDate(Pageable pageable, String date){

        Page<TV> tvs = this.iTvRepository.findByReleaseYearLike(pageable, date);

        if(tvs != null){
            return tvs;
        } else {
            return null;
        }

    }

    public TV updateTv(Long id, TV tv){

        Optional<TV> tvToBeUpdated = this.iTvRepository.findById(id);

        if(tvToBeUpdated != null){

            tvToBeUpdated.get().setId(id);
            tvToBeUpdated.get().setTitle(tv.getTitle());
            tvToBeUpdated.get().setOverview(tv.getOverview());
            tvToBeUpdated.get().setOriginCountry(tv.getOriginCountry());
            tvToBeUpdated.get().setLanguage(tv.getLanguage());
            tvToBeUpdated.get().setReleaseYear(tv.getReleaseYear());
            tvToBeUpdated.get().setRuntime(tv.getRuntime());

            this.iTvRepository.save(tvToBeUpdated.get());

            return tvToBeUpdated.get();
        } else {
            return null;
        }

    }

    public boolean deleteTv(Long id){

        boolean tvExists = this.iTvRepository.existsById(id);
        if(tvExists){
            this.iTvRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }
}

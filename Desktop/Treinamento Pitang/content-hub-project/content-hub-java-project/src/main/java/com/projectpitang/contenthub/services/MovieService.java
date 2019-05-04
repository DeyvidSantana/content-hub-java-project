package com.projectpitang.contenthub.services;

import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private IMovieRepository iMovieRepository;

    public IMovieRepository getiMovieRepository() {
        return iMovieRepository;
    }

    public void setiMovieRepository(IMovieRepository iMovieRepository) {
        this.iMovieRepository = iMovieRepository;
    }

    public Page<Movie> getAll(Pageable pageable){
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
}

package com.projectpitang.contenthub.services;

import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> getAll(Pageable pageable){
        return this.movieRepository.findAll(pageable);
    }

    public Page<Movie> findMovieByTitle(Pageable pageable, String title){

        Page<Movie> movies = this.movieRepository.findByTitleContainingIgnoreCase(pageable, title);

        if(movies != null){
            return movies;
        } else {
            return null;
        }

    }

    public Page<Movie> findMovieByLanguage(Pageable pageable, String language){

        Page<Movie> movies = this.movieRepository.findByLanguageLike(pageable, language);

        if(movies != null){
            return movies;
        } else {
            return null;
        }

    }

    public Page<Movie> findMovieByDate(Pageable pageable, String date){

        Page<Movie> movies = this.movieRepository.findByReleaseYearLike(pageable, date);

        if(movies != null){
            return movies;
        } else {
            return null;
        }

    }

    public Movie updateMovie(Long id, Movie movie){

        Optional<Movie> movieToBeUpdated = this.movieRepository.findById(id);

        if(movieToBeUpdated != null){
            movieToBeUpdated.get().setId(movie.getId());
            movieToBeUpdated.get().setIdApi(movie.getIdApi());
            movieToBeUpdated.get().setTitle(movie.getTitle());
            movieToBeUpdated.get().setOverview(movie.getOverview());
            movieToBeUpdated.get().setOriginCountry(movie.getOriginCountry());
            movieToBeUpdated.get().setLanguage(movie.getLanguage());
            movieToBeUpdated.get().setReleaseYear(movie.getReleaseYear());
            movieToBeUpdated.get().setRuntime(movie.getRuntime());

            this.movieRepository.save(movieToBeUpdated.get());

            return movieToBeUpdated.get();
        } else {
            return null;
        }

    }

    public boolean deleteMovie(Long id){

        boolean movieExists = this.movieRepository.existsById(id);
        if(movieExists){
            this.movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }
}

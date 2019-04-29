package com.projectpitang.contenthub.services.objectspersistence;

import com.projectpitang.contenthub.models.Genre;
import com.projectpitang.contenthub.models.Movie;
import com.projectpitang.contenthub.models.TV;
import com.projectpitang.contenthub.repository.GenreRepository;
import com.projectpitang.contenthub.repository.ProgramRepository;
import com.projectpitang.contenthub.services.apiconsumption.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ObjectsPersistence implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

    }

    public void persistMovieObjects(ConvertedMovieList convertedMovieList){
        // Persisting Movie objects
        for (ConvertedMovie convertedMovie: convertedMovieList.getResults()) {
            Movie movie = new Movie();
            movie.setIdApi(convertedMovie.getId());
            movie.setTitle(convertedMovie.getTitle());
            movie.setOverview(convertedMovie.getOverview());
            movie.setLanguage(convertedMovie.getOriginal_language());
            movie.setReleaseYear(convertedMovie.getRelease_date());

            Set<Genre> genres = new HashSet<Genre>();
            for (int id: convertedMovie.getGenre_ids())
            {

                boolean existsByIdApi = false;
                for (Genre genre: genreRepository.findAll()) {
                    if(genre.getIdApi() == id){
                        existsByIdApi = true;
                    }
                }

                if(!existsByIdApi){
                    Genre genre = new Genre();
                    genre.setIdApi((long) id);
                    genres.add(genre);
                }
            }
            movie.setGenres(genres);

            programRepository.save(movie);
        }
    }

    public void persistTvObjects(ConvertedTvList convertedTvList){
        // Persisting Tv objects
        for (ConvertedTv convertedTv: convertedTvList.getResults()) {
            TV tv = new TV();
            tv.setIdApi(convertedTv.getId());
            tv.setTitle(convertedTv.getOriginal_name());
            tv.setOverview(convertedTv.getOverview());
            tv.setLanguage(convertedTv.getOriginal_language());
            tv.setReleaseYear(convertedTv.getFirst_air_date());

            Set<Genre> genres = new HashSet<Genre>();
            for (int id: convertedTv.getGenre_ids())
            {

                boolean existsByIdApi = false;
                for (Genre genre: genreRepository.findAll()) {
                    if(genre.getIdApi() == id){
                        existsByIdApi = true;
                    }
                }

                if(!existsByIdApi){
                    Genre genre = new Genre();
                    genre.setIdApi((long) id);
                    genres.add(genre);
                }
            }
            tv.setGenres(genres);

            programRepository.save(tv);
        }
    }

    public void persistGenresObjects(ConvertedGenresList convertedMovieGenresList,
                                     ConvertedGenresList convertedTvGenresList){

        List<ConvertedGenres> convertedGenresList = new ArrayList<ConvertedGenres>();
        convertedGenresList.addAll(convertedMovieGenresList.getGenres());
        convertedGenresList.addAll(convertedTvGenresList.getGenres());

        // Persisting Genre objects
        for (ConvertedGenres convertedGenre: convertedGenresList) {

            boolean existsByIdApi = false;
            Genre genreToUpdate = new Genre();
            Genre genreUpdated = new Genre();

            for (Genre genre: genreRepository.findAll()) {
                if(genre.getIdApi() == convertedGenre.getId()){
                    existsByIdApi = true;
                    genreToUpdate = genre;
                }
            }

            if(existsByIdApi){
                genreUpdated.setId(genreToUpdate.getId());
                genreUpdated.setIdApi(genreToUpdate.getIdApi());
                genreUpdated.setName(convertedGenre.getName());
                genreRepository.save(genreUpdated);
            } else {
                genreUpdated.setIdApi(convertedGenre.getId());
                genreUpdated.setName(convertedGenre.getName());
                genreRepository.save(genreUpdated);
            }
        }

    }

}

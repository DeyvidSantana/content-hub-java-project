package com.projectpitang.contenthub.services.objectspersistence;

import com.projectpitang.contenthub.models.*;
import com.projectpitang.contenthub.repository.GenreRepository;
import com.projectpitang.contenthub.repository.ProgramRepository;
import com.projectpitang.contenthub.services.apiconsumption.APIConsumption;
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

    @Autowired
    private APIConsumption apiConsumption;

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

            // Builds a cast object to set it into a movie
            ConvertedMovieTvCastCrewList convertedMovieCastCrewList =
                    apiConsumption.getMovieCastCrewListFromApi(convertedMovie.getId());

            Cast castMovie = new Cast();
            castMovie.setIdCreditApi(convertedMovieCastCrewList.getId());

            List<Person> people = this.getPeopleFromMovieTvCastCrew(convertedMovieCastCrewList);

            castMovie.setCast(people);

            movie.setCast(castMovie);

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

            // Builds a cast object to set it into a tv
            ConvertedMovieTvCastCrewList convertedTvCastCrewList =
                    apiConsumption.getTvCastCrewListFromApi(convertedTv.getId());

            Cast castTv = new Cast();
            castTv.setIdCreditApi(convertedTvCastCrewList.getId());

            tv.setCast(castTv);

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

    public List<Person> getPeopleFromMovieTvCastCrew(ConvertedMovieTvCastCrewList convertedMovieCastCrewList){

        List<Person> people = new ArrayList<>();

        for (ConvertedCast person : convertedMovieCastCrewList.getCast()) {
            Person newArtist = new Artist();
            newArtist.setName(person.getName());
            newArtist.setIdApi(person.getId());
            newArtist.setGender(person.getGender());
            //newArtist.setType("AR");
            people.add(newArtist);
        }

        for (ConvertedCrew person : convertedMovieCastCrewList.getCrew()) {


            Person newArtist = null;
            if(person.getJob().contains("Director")){
                newArtist = new Director();
                newArtist.setName(person.getName());
                newArtist.setIdApi(person.getId());
                newArtist.setType("DI");
            } else if(person.getJob().contains("Producer")){
                newArtist = new Author();
                newArtist.setName(person.getName());
                newArtist.setIdApi(person.getId());
                newArtist.setType("AU");
            }

            people.add(newArtist);
        }

        return people;
    }

}

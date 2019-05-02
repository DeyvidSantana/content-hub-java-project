package com.projectpitang.contenthub.services.objectspersistence;

import com.projectpitang.contenthub.models.*;
import com.projectpitang.contenthub.repository.GenreRepository;
import com.projectpitang.contenthub.repository.PersonRepository;
import com.projectpitang.contenthub.repository.ProgramRepository;
import com.projectpitang.contenthub.services.apiconsumption.ApiConsumption;
import com.projectpitang.contenthub.services.apiconsumption.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ObjectsPersistence {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApiConsumption apiConsumption;

    public void persistMovieObjects(ApiMovieList apiMovieList) throws InterruptedException {
        System.out.println("\nStarting the persistence of the movies...\n");

        // Persisting Movie objects
        for (ApiMovie apiMovie : apiMovieList.getResults()) {
            Movie movie = new Movie();
            movie.setIdApi(apiMovie.getId());
            movie.setTitle(apiMovie.getTitle());
            movie.setOverview(apiMovie.getOverview());
            movie.setLanguage(apiMovie.getOriginal_language());
            movie.setReleaseYear(apiMovie.getRelease_date());

            List<Genre> genres = this.getGenresFromMovieTvGenresList(apiMovie);
            movie.setGenres(genres);

            // Builds a cast object to set it into a movie
            ApiMovieTvCastCrewList convertedMovieCastCrewList =
                    apiConsumption.getMovieCastCrewListFromApi(apiMovie.getId());

            Cast castMovie = new Cast();
            castMovie.setIdCreditApi(convertedMovieCastCrewList.getId());

            List<Person> people = this.getPeopleFromMovieTvCastCrew(convertedMovieCastCrewList);

            castMovie.setCast(people);

            movie.setCast(castMovie);

            programRepository.save(movie);
        }

        System.out.println("\nAll the movies were persisted, including their castings and people!");

    }

    public void persistTvObjects(ApiTvList convertedTvList) throws InterruptedException {
        System.out.println("\nStarting the persistence of the tvs...\n");

        // Persisting Tv objects
        for (ApiTv apiTv : convertedTvList.getResults()) {
            TV tv = new TV();
            tv.setIdApi(apiTv.getId());
            tv.setTitle(apiTv.getOriginal_name());
            tv.setOverview(apiTv.getOverview());
            tv.setLanguage(apiTv.getOriginal_language());
            tv.setReleaseYear(apiTv.getFirst_air_date());
            tv.setOriginCountry((apiTv.getOrigin_country()) != null ? apiTv.getOrigin_country()[0] : null);
            List<Genre> genres = this.getGenresFromMovieTvGenresList(apiTv);
            tv.setGenres(genres);

            // Builds a cast object to set it into a tv
            ApiMovieTvCastCrewList convertedTvCastCrewList =
                    apiConsumption.getTvCastCrewListFromApi(apiTv.getId());

            Cast castTv = new Cast();
            castTv.setIdCreditApi(convertedTvCastCrewList.getId());

            List<Person> people = this.getPeopleFromMovieTvCastCrew(convertedTvCastCrewList);

            castTv.setCast(people);

            tv.setCast(castTv);

            programRepository.save(tv);

        }

        System.out.println("\nAll the tvs were persisted, including their castings and people!");

    }

    public void persistGenresObjects(ApiGenresList convertedMovieGenresList,
                                     ApiGenresList convertedTvGenresList){

        System.out.println("\nStarting the update of the movie and tv genres...\n");

        List<ApiGenres> apiGenresList = new ArrayList<ApiGenres>();
        apiGenresList.addAll(convertedMovieGenresList.getGenres());
        apiGenresList.addAll(convertedTvGenresList.getGenres());

        // Persisting Genre objects
        for (ApiGenres convertedGenre: apiGenresList) {

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

        System.out.println("\nAll the genres were updated!");

    }

    public List<Person> getPeopleFromMovieTvCastCrew(ApiMovieTvCastCrewList apiMovieTvCastCrewList) throws InterruptedException {

        List<Person> people = new ArrayList<>();

        int index = 0; // Limits a list to 3 objects
        for (ApiCastCrew person : apiMovieTvCastCrewList.getCast()) {

            // Used to not exceed the number of requests allowed
            Thread.sleep(300);

            if(index <= 2){

                Person completedPerson = getCompletePerson(person, "AR");
                people.add(completedPerson);
                index++;

            } else {
                break;
            }
        }

        index = 0; // Limits a list to 3 objects
        for (ApiCastCrew person : apiMovieTvCastCrewList.getCrew()) {

            Person completedPerson = null;
            if (index <= 2){

                // Used to not exceed the number of requests allowed
                Thread.sleep(300);

                if(person.getJob().contains("Director")){
                    completedPerson = this.getCompletePerson(person,"DI");
                    people.add(completedPerson);
                    index++;
                } else if(person.getJob().contains("Producer")){
                    completedPerson = this.getCompletePerson(person,"AU");
                    people.add(completedPerson);
                    index++;
                }

            } else {
                break;
            }

        }

        return people;
    }

    public Person buildCompletedPersonBasedOnSavedPerson(ApiCastCrew incompletePerson, ApiPerson apiPerson, String type){
        Person completedPerson = null;

        switch (type){
            case "AR":
                completedPerson = new Artist();
                break;
            case "DI":
                completedPerson = new Director();
                break;
            case "AU":
                completedPerson = new Author();
                break;
        }

        completedPerson.setName(incompletePerson.getName());
        completedPerson.setIdApi(incompletePerson.getId());
        completedPerson.setGender(incompletePerson.getGender());
        completedPerson.setCityBirth(apiPerson.getPlace_of_birth());
        completedPerson.setCountryBirth(apiPerson.getPlace_of_birth());
        completedPerson.setHeight(1.7);

        Person savedPerson = personRepository.save(completedPerson);

        Optional<Person> savedPersonWitId = personRepository.findById(savedPerson.getId());

        completedPerson.setId(savedPersonWitId.get().getId());

        return completedPerson;

    }

    public Person getCompletePerson(ApiCastCrew person, String type){

        boolean existsByIdApi = false;
        Person existingPerson = new Artist();
        Person completedPerson = null;

        for (Person personRepository: personRepository.findAll()) {
            if(personRepository.getIdApi().equals(person.getId())){
                existsByIdApi = true;
                existingPerson = personRepository;
            }
        }


        if(!existsByIdApi){
            ApiPerson apiPerson = apiConsumption.getPersonFromApi(person.getId());
            completedPerson = this.buildCompletedPersonBasedOnSavedPerson(person, apiPerson, type);
        } else {

            switch (type){
                case "AR":
                    completedPerson = new Artist();
                    break;
                case "DI":
                    completedPerson = new Director();
                    break;
                case "AU":
                    completedPerson = new Author();
                    break;
            }

            completedPerson.setId(existingPerson.getId());
        }

        return completedPerson;
    }

    public List<Genre> getGenresFromMovieTvGenresList(ApiProgram apiProgram){

        List<Genre> genres = new ArrayList<>();

        for (int id: apiProgram.getGenre_ids())
        {

            boolean existsByIdApi = false;
            Genre existingGenre = null;
            for (Genre genre: genreRepository.findAll()) {
                if(genre.getIdApi() == id){
                    existsByIdApi = true;
                    existingGenre = genre;
                }
            }

            if(!existsByIdApi){
                Genre genre = new Genre();
                genre.setIdApi((long) id);

                Genre savedGenre = genreRepository.save(genre);
                Optional<Genre> savedGenreWitId = genreRepository.findById(savedGenre.getId());
                genre.setId(savedGenreWitId.get().getId());

                genres.add(genre);
            }else{
                Genre genre = new Genre();
                genre.setId(existingGenre.getId());
                genres.add(genre);
            }
        }

        return genres;
    }
}

package com.projectpitang.contenthub.services.objects.persistence;

import com.projectpitang.contenthub.models.*;
import com.projectpitang.contenthub.repository.*;
import com.projectpitang.contenthub.services.api.consumption.ApiConsumptionService;
import com.projectpitang.contenthub.services.api.consumption.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ObjectsPersistenceService {

    @Autowired
    private IMovieRepository iMovieRepository;

    @Autowired
    private ITvRepository iTvRepository;

    @Autowired
    private IGenreRepository iGenreRepository;

    @Autowired
    private IPersonRepository iPersonRepository;

    @Autowired
    private ApiConsumptionService apiConsumptionService;

    public void persistMovieObjects(ApiMovieList apiMovieList) throws InterruptedException {
        System.out.println("\nPersisting the movies. Please wait...\n");

        // Persisting Movie objects
        for (ApiMovie apiMovie : apiMovieList.getResults()) {
            Movie movie = new Movie();
            movie.setIdApi(apiMovie.getId());
            movie.setTitle(apiMovie.getTitle());
            movie.setOverview(apiMovie.getOverview());
            movie.setLanguage(apiMovie.getOriginal_language());
            movie.setReleaseDate(apiMovie.getRelease_date());
            movie.setBackdropPath(apiMovie.getBackdrop_path());

            // Used to pick up the original country information and movie runtime.
            ApiMoreDetailsMovieTv apiMoreDetailsMovieTv = apiConsumptionService.getMoreDetailsMovie(apiMovie.getId());

            movie.setRuntime(apiMoreDetailsMovieTv.getRuntime());
            movie.setOriginCountry(apiMoreDetailsMovieTv.getProduction_countries().size() != 0 ?
                    apiMoreDetailsMovieTv.getProduction_countries().get(0).getName() : null);

            List<Genre> genres = this.getGenresFromMovieTvGenresList(apiMovie);
            movie.setGenres(genres);

            // Builds a cast object to set it into a movie
            ApiMovieTvCastCrewList convertedMovieCastCrewList =
                    apiConsumptionService.getMovieCastCrewListFromApi(apiMovie.getId());

            Cast castMovie = new Cast();
            castMovie.setIdCreditApi(convertedMovieCastCrewList.getId());

            List<Person> people = this.getPeopleFromMovieTvCastCrew(convertedMovieCastCrewList);

            castMovie.setCast(people);

            movie.setCast(castMovie);

            iMovieRepository.save(movie);

        }

        System.out.println("\nAll the movies were persisted, including their castings and people!");

    }

    public void persistTvObjects(ApiTvList convertedTvList) throws InterruptedException {
        System.out.println("\nPersisting the tvs. Please wait...\n");

        // Persisting Tv objects
        for (ApiTv apiTv : convertedTvList.getResults()) {
            TV tv = new TV();
            tv.setIdApi(apiTv.getId());
            tv.setTitle(apiTv.getOriginal_name());
            tv.setOverview(apiTv.getOverview());
            tv.setLanguage(apiTv.getOriginal_language());
            tv.setReleaseDate(apiTv.getFirst_air_date());
            tv.setBackdropPath(apiTv.getBackdrop_path());
            tv.setOriginCountry(apiTv.getOrigin_country().size() != 0 ? apiTv.getOrigin_country().get(0) : null);
            List<Genre> genres = this.getGenresFromMovieTvGenresList(apiTv);
            tv.setGenres(genres);

            // Used to pick up the original country information and movie runtime.
            ApiMoreDetailsMovieTv apiMoreDetailsMovieTv = apiConsumptionService.getMoreDetailsTv(apiTv.getId());

            tv.setSeansons(apiMoreDetailsMovieTv.getNumber_of_seasons());
            tv.setRuntime(apiMoreDetailsMovieTv.getEpisode_run_time().size() != 0 ?
                    apiMoreDetailsMovieTv.getEpisode_run_time().get(0) : 0);
            // Builds a cast object to set it into a tv
            ApiMovieTvCastCrewList convertedTvCastCrewList =
                    apiConsumptionService.getTvCastCrewListFromApi(apiTv.getId());

            Cast castTv = new Cast();
            castTv.setIdCreditApi(convertedTvCastCrewList.getId());

            List<Person> people = this.getPeopleFromMovieTvCastCrew(convertedTvCastCrewList);

            castTv.setCast(people);

            tv.setCast(castTv);

            iTvRepository.save(tv);

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

            for (Genre genre: iGenreRepository.findAll()) {
                if(genre.getIdApi() == convertedGenre.getId()){
                    existsByIdApi = true;
                    genreToUpdate = genre;
                }
            }

            if(existsByIdApi){
                genreUpdated.setId(genreToUpdate.getId());
                genreUpdated.setIdApi(genreToUpdate.getIdApi());
                genreUpdated.setName(convertedGenre.getName());
                iGenreRepository.save(genreUpdated);
            } else {
                genreUpdated.setIdApi(convertedGenre.getId());
                genreUpdated.setName(convertedGenre.getName());
                iGenreRepository.save(genreUpdated);
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
                    completedPerson = this.getCompletePerson(person,"PR");
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
            case "PR":
                completedPerson = new Producer();
                break;
        }

        completedPerson.setName(incompletePerson.getName());
        completedPerson.setIdApi(incompletePerson.getId());
        completedPerson.setGender(incompletePerson.getGender());
        completedPerson.setHometown(apiPerson.getPlace_of_birth());
        completedPerson.setHomeCountry(apiPerson.getPlace_of_birth());
        completedPerson.setHeight(1.7);
        completedPerson.setProfilePath(apiPerson.getProfile_path());

        Person savedPerson = iPersonRepository.save(completedPerson);

        Optional<Person> savedPersonWitId = iPersonRepository.findById(savedPerson.getId());

        completedPerson.setId(savedPersonWitId.get().getId());

        return completedPerson;

    }

    public Person getCompletePerson(ApiCastCrew person, String type){

        boolean existsByIdApi = false;
        Person existingPerson = new Artist();
        Person completedPerson = null;

        for (Person personRepository: iPersonRepository.findAll()) {
            if(personRepository.getIdApi().equals(person.getId())){
                existsByIdApi = true;
                existingPerson = personRepository;
            }
        }


        if(!existsByIdApi){
            ApiPerson apiPerson = apiConsumptionService.getPersonFromApi(person.getId());
            completedPerson = this.buildCompletedPersonBasedOnSavedPerson(person, apiPerson, type);
        } else {

            switch (type){
                case "AR":
                    completedPerson = new Artist();
                    break;
                case "DI":
                    completedPerson = new Director();
                    break;
                case "PR":
                    completedPerson = new Producer();
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
            for (Genre genre: iGenreRepository.findAll()) {
                if(genre.getIdApi() == id){
                    existsByIdApi = true;
                    existingGenre = genre;
                }
            }

            if(!existsByIdApi){
                Genre genre = new Genre();
                genre.setIdApi((long) id);

                Genre savedGenre = iGenreRepository.save(genre);
                Optional<Genre> savedGenreWitId = iGenreRepository.findById(savedGenre.getId());
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

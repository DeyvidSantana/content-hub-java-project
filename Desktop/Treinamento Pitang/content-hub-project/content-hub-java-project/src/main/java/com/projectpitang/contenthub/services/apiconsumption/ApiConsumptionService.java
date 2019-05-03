package com.projectpitang.contenthub.services.apiconsumption;

import com.projectpitang.contenthub.services.apiconsumption.models.*;
import com.projectpitang.contenthub.services.objectspersistence.ObjectsPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiConsumptionService {

    private static final String API_KEY = "1b8c410632bbe9b59e1c774aa90c6694";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";
    private static final String URL_GENRE_MOVIE = "https://api.themoviedb.org/3/genre/movie/list?api_key=" +
            API_KEY + "&language=" + LANGUAGE;
    private static final String URL_GENRE_TV = "https://api.themoviedb.org/3/genre/tv/list?api_key=" +
            API_KEY + "&language=" + LANGUAGE;
    private static final String URL_MOVIES = "https://api.themoviedb.org/3/discover/movie?api_key=" +
            API_KEY + "&language=" + LANGUAGE;
    private static final String URL_TVS = "https://api.themoviedb.org/3/discover/tv?api_key=" +
            API_KEY + "&language=" + LANGUAGE;
    // Credits
    private static final String URL_CREDITS = "/credits";

    private ApiMovieList apiMovieList;
    private ApiTvList convertedTvList;
    private ApiGenresList convertedMovieGenresList;
    private ApiGenresList convertedTvGenresList;

    @Autowired
    private ObjectsPersistenceService objectsPersistenceService;

    RestTemplate restTemplate = new RestTemplate();

    private ApiMovieList getMoviesListFromApi(){

        String x = restTemplate.getForObject(URL_MOVIES, String.class);

        return restTemplate.getForObject(URL_MOVIES, ApiMovieList.class);
    }

    private ApiTvList getTvsListFromApi(){

        String x = restTemplate.getForObject(URL_TVS, String.class);

        return restTemplate.getForObject(URL_TVS, ApiTvList.class);
    }

    private ApiGenresList getMovieGenresListFromApi(){
        return restTemplate.getForObject(URL_GENRE_MOVIE, ApiGenresList.class);
    }

    private ApiGenresList getTvGenresListFromApi(){
        return restTemplate.getForObject(URL_GENRE_TV, ApiGenresList.class);
    }

    public ApiMoreDetailsMovieTv getMoreDetailsMovie(Long idMovie){
        return restTemplate.getForObject(BASE_URL + "movie/" + idMovie + "?api_key=" + API_KEY,
                ApiMoreDetailsMovieTv.class);
    }

    public ApiMoreDetailsMovieTv getMoreDetailsTv(Long idTv){
        return restTemplate.getForObject(BASE_URL + "tv/" + idTv + "?api_key=" + API_KEY,
                ApiMoreDetailsMovieTv.class);
    }

    public ApiMovieTvCastCrewList getMovieCastCrewListFromApi(Long idMovie){
        return restTemplate.getForObject(BASE_URL + "movie/" + idMovie +
                URL_CREDITS + "?api_key=" + API_KEY, ApiMovieTvCastCrewList.class);
    }

    public ApiMovieTvCastCrewList getTvCastCrewListFromApi(Long idTv){
        return restTemplate.getForObject(BASE_URL + "tv/" + idTv +
                URL_CREDITS + "?api_key=" + API_KEY, ApiMovieTvCastCrewList.class);
    }

    public ApiPerson getPersonFromApi(Long idPerson){
        return restTemplate.getForObject(BASE_URL + "person/" + idPerson + "?api_key=" +
                API_KEY, ApiPerson.class);
    }

    private void buildObjectsFromApi(){

        System.out.println("\nStarting consumption of The Movie DB API...");

        apiMovieList = this.getMoviesListFromApi();
        convertedTvList = this.getTvsListFromApi();
        convertedMovieGenresList = this.getMovieGenresListFromApi();
        convertedTvGenresList = this.getTvGenresListFromApi();
    }

    public void persistObjects() throws InterruptedException {

        this.buildObjectsFromApi();

        System.out.println("\nStarting persistence of data...");

        this.objectsPersistenceService.persistMovieObjects(apiMovieList);
        this.objectsPersistenceService.persistTvObjects(convertedTvList);
        this.objectsPersistenceService.persistGenresObjects(convertedMovieGenresList, convertedTvGenresList);

        System.out.println("\nAll objects persisted!\n");
    }

}

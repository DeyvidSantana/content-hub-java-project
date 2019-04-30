package com.projectpitang.contenthub.services.apiconsumption;

import com.projectpitang.contenthub.services.apiconsumption.models.*;
import com.projectpitang.contenthub.services.objectspersistence.ObjectsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class APIConsumption {

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

    private static Logger logger = Logger.getLogger( APIConsumption.class.getName() );

    private ConvertedMovieList convertedMovieList;
    private ConvertedTvList convertedTvList;
    private ConvertedGenresList convertedMovieGenresList;
    private ConvertedGenresList convertedTvGenresList;

    @Autowired
    private ObjectsPersistence objectsPersistence;

    RestTemplate restTemplate = new RestTemplate();

    private ConvertedMovieList getMoviesListFromApi(){
        return restTemplate.getForObject(URL_MOVIES, ConvertedMovieList.class);
    }

    private ConvertedTvList getTvsListFromApi(){
        return restTemplate.getForObject(URL_TVS, ConvertedTvList.class);
    }

    private ConvertedGenresList getMovieGenresListFromApi(){
        return restTemplate.getForObject(URL_GENRE_MOVIE, ConvertedGenresList.class);
    }

    private ConvertedGenresList getTvGenresListFromApi(){
        return restTemplate.getForObject(URL_GENRE_TV, ConvertedGenresList.class);
    }

    public ConvertedMovieTvCastCrewList getMovieCastCrewListFromApi(Long idMovie){
        return restTemplate.getForObject(BASE_URL + "movie/" + idMovie +
                URL_CREDITS + "?api_key=" + API_KEY, ConvertedMovieTvCastCrewList.class);
    }

    public ConvertedMovieTvCastCrewList getTvCastCrewListFromApi(Long idTv){
        return restTemplate.getForObject(BASE_URL + "tv/" + idTv +
                URL_CREDITS + "?api_key=" + API_KEY, ConvertedMovieTvCastCrewList.class);
    }

    public ApiPerson getPersonFromApi(Long idPerson){
        return restTemplate.getForObject(BASE_URL + "person/" + idPerson + "?api_key=" +
                API_KEY, ApiPerson.class);
    }

    private void buildObjectsFromApi(){
        convertedMovieList = this.getMoviesListFromApi();
        convertedTvList = this.getTvsListFromApi();
        convertedMovieGenresList = this.getMovieGenresListFromApi();
        convertedTvGenresList = this.getTvGenresListFromApi();
    }

    public void persistObjects() throws InterruptedException {

        this.buildObjectsFromApi();

        this.objectsPersistence.persistMovieObjects(convertedMovieList);
        this.objectsPersistence.persistTvObjects(convertedTvList);
        this.objectsPersistence.persistGenresObjects(convertedMovieGenresList, convertedTvGenresList);

        //logger.log(Level.FINE, "All objects persisted!");
    }

}

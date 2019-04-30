package com.projectpitang.contenthub.services.apiconsumption;

import com.projectpitang.contenthub.services.apiconsumption.models.ConvertedGenresList;
import com.projectpitang.contenthub.services.apiconsumption.models.ConvertedMovieTvCastCrewList;
import com.projectpitang.contenthub.services.apiconsumption.models.ConvertedMovieList;
import com.projectpitang.contenthub.services.apiconsumption.models.ConvertedTvList;
import com.projectpitang.contenthub.services.objectspersistence.ObjectsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    private void buildObjectsFromApi(){
        convertedMovieList = this.getMoviesListFromApi();
        convertedTvList = this.getTvsListFromApi();
        convertedMovieGenresList = this.getMovieGenresListFromApi();
        convertedTvGenresList = this.getTvGenresListFromApi();
    }

    public void persistObjects(){

        this.buildObjectsFromApi();

        this.objectsPersistence.persistMovieObjects(convertedMovieList);
        this.objectsPersistence.persistTvObjects(convertedTvList);
        this.objectsPersistence.persistGenresObjects(convertedMovieGenresList, convertedTvGenresList);
    }

}

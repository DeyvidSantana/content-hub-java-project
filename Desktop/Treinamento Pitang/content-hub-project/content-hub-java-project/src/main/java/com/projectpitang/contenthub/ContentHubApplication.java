package com.projectpitang.contenthub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectpitang.contenthub.models.*;
import com.projectpitang.contenthub.repository.CastRepository;
import com.projectpitang.contenthub.repository.GenreRepository;
import com.projectpitang.contenthub.repository.ProgramRepository;
import com.projectpitang.contenthub.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootApplication
@EnableJpaRepositories
public class ContentHubApplication implements CommandLineRunner {

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


	@Autowired
	private CastRepository castRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private ProgramRepository programRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContentHubApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception{

		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();

		ConvertedMovieCastList convertedMovieCastList = restTemplate.getForObject(BASE_URL + "/movie/299534" + URL_CREDITS + "?api_key=" +
				API_KEY + "&language=" + LANGUAGE, ConvertedMovieCastList.class);

		ConvertedMovieList convertedMovieList = restTemplate.getForObject(URL_MOVIES, ConvertedMovieList.class);
		ConvertedTvList convertedTvList = restTemplate.getForObject(URL_TVS, ConvertedTvList.class);
		ConvertedGenresList convertedMovieGenresList = restTemplate.getForObject(URL_GENRE_MOVIE,
				ConvertedGenresList.class);
		ConvertedGenresList convertedTvGenresList = restTemplate.getForObject(URL_GENRE_TV, ConvertedGenresList.class);

		List<ConvertedGenres> convertedGenresList = new ArrayList<ConvertedGenres>();
		convertedGenresList.addAll(convertedMovieGenresList.getGenres());
		convertedGenresList.addAll(convertedTvGenresList.getGenres());



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

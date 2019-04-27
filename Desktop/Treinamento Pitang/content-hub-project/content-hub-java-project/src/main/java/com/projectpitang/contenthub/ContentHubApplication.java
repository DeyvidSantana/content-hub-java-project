package com.projectpitang.contenthub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectpitang.contenthub.models.Cast;
import com.projectpitang.contenthub.models.Person;
import com.projectpitang.contenthub.services.CastDAO;
import com.projectpitang.contenthub.services.ConvertedGenresList;
import com.projectpitang.contenthub.services.ConvertedMovieList;
import com.projectpitang.contenthub.services.ConvertedTvList;
import com.projectpitang.contenthub.utils.PersonGender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories
public class ContentHubApplication {

	private static final String API_KEY = "1b8c410632bbe9b59e1c774aa90c6694";
	private static final String LANGUAGE = "en-US";
	private static final String URL_GENRE_MOVIE = "https://api.themoviedb.org/3/genre/movie/list?api_key=" +
			API_KEY + "&language=" + LANGUAGE;
	private static final String URL_MOVIES = "https://api.themoviedb.org/3/discover/movie?api_key=" +
			API_KEY + "&language=" + LANGUAGE;
	private static final String URL_TVS = "https://api.themoviedb.org/3/discover/tv?api_key=" +
			API_KEY + "&language=" + LANGUAGE;
	//private static final String URL_CREDITS = "https://api.themoviedb.org/3/credit/299537?api_key=" +
			//API_KEY;

	public static void main(String[] args) {
		SpringApplication.run(ContentHubApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();

		ConvertedGenresList convertedGenresList = restTemplate.getForObject(URL_GENRE_MOVIE, ConvertedGenresList.class);
		ConvertedMovieList convertedMovieList = restTemplate.getForObject(URL_MOVIES, ConvertedMovieList.class);
		ConvertedTvList convertedTvList = restTemplate.getForObject(URL_TVS, ConvertedTvList.class);
		//String credits = restTemplate.getForObject(URL_CREDITS, String.class);

		/*CastDAO castDAO = new CastDAO();

		Cast cast1 = new Cast();
		cast1.setName("Cast1");

		Set<Person> people = new HashSet<Person>();
		Person brad = new Person();
		brad.setName("Brad");
		brad.setCityBirth("Oklaoma");
		brad.setCountryBirth("USA");
		brad.setGender(PersonGender.M);
		brad.setHeight(1.7);
		brad.setType("AR");
		people.add(brad);

		cast1.setCast(people);


		castDAO.insert(cast1);*/


	}

}

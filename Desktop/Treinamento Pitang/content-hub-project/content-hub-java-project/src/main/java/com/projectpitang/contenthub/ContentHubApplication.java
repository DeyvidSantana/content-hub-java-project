package com.projectpitang.contenthub;

import com.projectpitang.contenthub.services.apiconsumption.ApiConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ContentHubApplication implements CommandLineRunner {

	@Autowired
	private ApiConsumptionService apiConsumptionService;

	public static void main(String[] args) {

		SpringApplication.run(ContentHubApplication.class, args);

	}

	@Override
	public void run(String...args) throws Exception{

		//this.apiConsumptionService.persistObjects();

	}

}

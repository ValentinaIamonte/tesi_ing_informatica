package it.ElectricalColumnManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import it.ElectricalColumnManager.service.ElectricalColumnService;

@SpringBootApplication
public class ElectricalColumnManagerApplication {

	 @Autowired
	 private ElectricalColumnService electricalColumnService;

	public static void main(String[] args) {
		SpringApplication.run(ElectricalColumnManagerApplication.class, args);
	}

	@Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            System.out.println("Populate db");
			electricalColumnService.populateDbWithChargingModeType();

            
        };
    }

}

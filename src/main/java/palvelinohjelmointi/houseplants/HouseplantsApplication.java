package palvelinohjelmointi.houseplants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelinohjelmointi.houseplants.domain.Houseplant;
import palvelinohjelmointi.houseplants.domain.HouseplantRepository;
import palvelinohjelmointi.houseplants.domain.Type;
import palvelinohjelmointi.houseplants.domain.TypeRepository;
import palvelinohjelmointi.houseplants.domain.User;
import palvelinohjelmointi.houseplants.domain.UserRepository;

@SpringBootApplication
public class HouseplantsApplication {
	private static final Logger log = LoggerFactory.getLogger(HouseplantsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(HouseplantsApplication.class, args);
	}

	@Bean
	public CommandLineRunner houseplant(HouseplantRepository repository, TypeRepository trepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of houseplants");
			
			trepository.save(new Type("woody"));
			trepository.save(new Type("climber"));
			trepository.save(new Type("hanging"));
			trepository.save(new Type("succulent"));
			
			repository.save(new Houseplant("Peikonlehti", "Monstera deliciosa", "2018", 35, trepository.findByName("climber").get(0)));
			repository.save(new Houseplant("Pikkuposliinikukka", "Hoya bella", "2019", 12, trepository.findByName("hanging").get(0)));
			repository.save(new Houseplant("Kiinanruusu", "Hisbiscus rosa-sinensis", "2011", 40, trepository.findByName("woody").get(0)));
	
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$76aG7c6qiIYagzciJY5k3.oLYlDNH.GVKLUhkWpD9hbryfxLyA4/2", "USER");
			User user2 = new User("admin", "$2a$10$rKL8Oqr9U7h3cCm4n.knsOMbVnXJ1dpbPBbp0Bf5fdjsfEwGIXMLi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
		
			log.info("fetch all houseplants");
			for (Houseplant houseplant : repository.findAll()) {
				log.info(houseplant.toString());
			}
		};
	
	}
}

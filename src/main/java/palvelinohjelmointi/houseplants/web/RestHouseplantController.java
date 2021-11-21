package palvelinohjelmointi.houseplants.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import palvelinohjelmointi.houseplants.domain.Houseplant;
import palvelinohjelmointi.houseplants.domain.HouseplantRepository;
import palvelinohjelmointi.houseplants.domain.TypeRepository;

@RestController
public class RestHouseplantController {
	
	@Autowired
	HouseplantRepository houseplantRepository;
	
	@Autowired
	TypeRepository typeRepository;
	
	//read houseplants from db
	@GetMapping(value="/houseplants")
	public List<Houseplant> houseplantListRest(){
		System.out.println("RestHouseplantController:/houseplants");
		return (List<Houseplant>) houseplantRepository.findAll();
	}
	
	//insert new houseplant to db
		@RequestMapping(value="/houseplants", method = RequestMethod.POST)
		public List<Houseplant> saveHouseplant(@RequestBody Houseplant houseplant) {
		houseplantRepository.save(houseplant);
		return (List<Houseplant>) houseplantRepository.findAll();
		}

}

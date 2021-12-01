package palvelinohjelmointi.houseplants.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import palvelinohjelmointi.houseplants.domain.Houseplant;
import palvelinohjelmointi.houseplants.domain.HouseplantRepository;
import palvelinohjelmointi.houseplants.domain.TypeRepository;

@Controller
public class HouseplantController {
	
	@Autowired
	private HouseplantRepository repository;
	
	@Autowired
	private TypeRepository trepository;

	@RequestMapping( "/houseplantlist")
	public String houseplantList(Model model) {
		model.addAttribute("houseplants", repository.findAll());
		return "houseplantlist";
	}
	
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addHouseplant( Model model){
	    	model.addAttribute("houseplant", new Houseplant());
	    	model.addAttribute("types", trepository.findAll());
	    	return "addhouseplant";
	    }  
	    
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(@Valid @ModelAttribute("houseplant") Houseplant houseplant, BindingResult bindingResult, Model model){
	    	
	    	if (bindingResult.hasErrors()) {
				return "addhouseplant";
	    	}
	    	repository.save(houseplant);
	        return "redirect:houseplantlist";
	    }  
	    
	    @PreAuthorize("hasAuthority('ADMIN')")
	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteHouseplant(@PathVariable("id") Long houseplantId, Model model) {
	    	repository.deleteById(houseplantId);
	        return "redirect:../houseplantlist";
	    }     
	    
	    @PreAuthorize("hasAuthority('ADMIN')")
	    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
	    public String editHouseplant(@PathVariable("id") Long houseplantId, Model model){
	    model.addAttribute("houseplant", repository.findById(houseplantId));
	    model.addAttribute("types", trepository.findAll());
	    return "edithouseplant";
	    }
}

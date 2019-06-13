package bank.hr.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bank.hr.model.City;
import bank.hr.repo.CityRepository;



@Controller
@RequestMapping(value="/cities")
public class CityController {

	@Autowired
	private CityRepository cityRepo;
	
	/*READ
	 * */
	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<City> opt = cityRepo.findById(id);
		
		if(opt.isPresent()) {
		
			City city = opt.get();
			model.addAttribute("city", city);
			
		}
		return "city";
	}
	
	@GetMapping ()
	public String displayAll ( Model model ) {
		List<City> cities = cityRepo.findAll();
		model.addAttribute("cities", cities);
		return "cities";
	}
}



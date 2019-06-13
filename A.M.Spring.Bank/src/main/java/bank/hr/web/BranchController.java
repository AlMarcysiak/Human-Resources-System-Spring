package bank.hr.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bank.hr.exception.NoDataFoundException;
import bank.hr.model.Branch;
import bank.hr.model.City;
import bank.hr.repo.BranchRepository;
import bank.hr.repo.CityRepository;

@Controller
@RequestMapping(value="/branches")
public class BranchController {

	@Autowired
	private BranchRepository braRepo;
	@Autowired
	private CityRepository cityRepo;

	/* READ */
	@GetMapping()
	public String displayAll(Model model) {
		List<Branch> branches = braRepo.findAll();
		model.addAttribute("branches", branches);
		return "branches";
	}

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Branch> optBr = braRepo.findById(id);
		if (optBr.isPresent()) {
			Branch branch = optBr.get();
			model.addAttribute("branch", branch);

		}
		return "branch";
	}

	/* CREATE */
	@GetMapping(value = "/form")
	public String CreatBr(Model model) {
		Branch branch = new Branch();
		model.addAttribute("branch", branch);

		List<City>cities = cityRepo.findAll();
		model.addAttribute("cities", cities);

		return "branch-form";
	}

	/* UPDATE */
	@GetMapping("/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Branch> optBr = braRepo.findById(id);

		if (optBr.isPresent()) {
			Branch branch = optBr.get();
			model.addAttribute("branch", branch);
			List<City>cities = cityRepo.findAll();
			model.addAttribute("cities", cities);
		} else
			throw new NoDataFoundException("No branch found for id = " + id);

		return "branch-form";
	}

	@PostMapping("/save")
	public String createOne(@Valid Branch branch, Errors errors) {
		if (errors.hasErrors())
			return "employee-form";

		Long id = branch.getId();
		if (id == null) {
			if (branch.getCity() != null) {
				Long cityId = branch.getCity().getId();
				City city = cityRepo.findById(cityId).get();
				branch.setCity(city);
			}
		}

		braRepo.save(branch);

		return "redirect:/employees";
	}
	/* DELETE */

	@GetMapping("/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (braRepo.existsById(id))
			braRepo.deleteById(id);
		else
			throw new NoDataFoundException("No branch found for id = " + id);

		// Powrót na stron branches
		return "redirect:/branches";
	}
}

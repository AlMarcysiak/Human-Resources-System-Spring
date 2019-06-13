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

import bank.hr.model.Employee;
import bank.hr.model.Position;
import bank.hr.repo.EmployeeRepository;
import bank.hr.repo.PositionRepository;
import bank.hr.exception.NoDataFoundException;

@Controller
@RequestMapping(value = "/positions")
public class PositionController {

	@Autowired
	private PositionRepository posRepo;
	@Autowired
	private EmployeeRepository empRepo;

	/* READ */

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Position> opt = posRepo.findById(id);
		if (opt.isPresent()) {
			Position position = opt.get();
			model.addAttribute("position", position);

			List<Employee> employees = empRepo.findAllByPosition(position);
			model.addAttribute("employees", employees);
		}
		return "position";
	}

	@GetMapping()
	public String displayAll(Model model) {
		List<Position> positions = posRepo.findAll();
		model.addAttribute("positions", positions);
		return "positions";
	}
	/* CREATE */

	@GetMapping("/form")
	public String displayForm(Model model) {
		Position position = new Position();
		model.addAttribute("position", position);
		return "position-form";
	}

	/* UPDATE */

	@GetMapping("/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Position> opt = posRepo.findById(id);

		if (opt.isPresent()) {
			Position position = opt.get();
			model.addAttribute("position", position);
		} else
			throw new NoDataFoundException("No position found for id = " + id);

		return "position-form";
	}

	/* SAVE FORM (CREATE or UPDATE) */

	@PostMapping("/save")
	public String createOne(@Valid Position position, Errors errors) {
		if (errors.hasErrors())
			return "position-form";

		posRepo.save(position);
		return "redirect:/positions";
	}

	/* DELETE */

	@GetMapping("/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (posRepo.existsById(id))
			posRepo.deleteById(id);
		else
			throw new NoDataFoundException("No position found for id = " + id);

		return "redirect:/positions";
	}

}

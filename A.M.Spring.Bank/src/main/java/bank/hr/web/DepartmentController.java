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

import bank.hr.exception.NoDataFoundException;
import bank.hr.model.Department;
import bank.hr.model.Employee;
import bank.hr.repo.DepartmentRepository;
import bank.hr.repo.EmployeeRepository;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentRepository depRepo;
	@Autowired
	private EmployeeRepository empRepo;

	/* READ */

	@GetMapping(value = "/departments/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Department> opt = depRepo.findById(id);

		if (opt.isPresent()) {
			Department department = opt.get();
			model.addAttribute("department", department);

			List<Employee> employees = empRepo.findAllByDepartment(department);
			model.addAttribute("employees", employees);
		} else
			throw new NoDataFoundException("No department found for id = " + id);

		return "department";
	}

	@GetMapping(value = "/departments")
	public String displayAll(Model model) {
		List<Department> departments = depRepo.findAll();
		model.addAttribute("departments", departments);
		return "departments";
	}

	/* CREATE */

	@GetMapping("/departments/form")
	public String displayForm(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "department-form";
	}

	/* UPDATE */

	@GetMapping("/departments/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Department> opt = depRepo.findById(id);

		if (opt.isPresent()) {
			Department department = opt.get();
			model.addAttribute("department", department);
		} else
			throw new NoDataFoundException("No department found for id = " + id);

		return "department-form";
	}

	/* SAVE FORM (CREATE or UPDATE) */

	@PostMapping("/departments/save")
	public String createOne(@Valid Department department, Errors errors) {
		if (errors.hasErrors())
			return "department-form";

		Long id = department.getId();
		if (id == null) {
			depRepo.save(department);
		} else {
			Optional<Department> opt = depRepo.findById(department.getId());

			if (opt.isPresent()) {
				Department oneToUpdate = opt.get();
				oneToUpdate.setName(department.getName());

				depRepo.save(oneToUpdate);
			} else
				throw new NoDataFoundException("No department found for id = " + id);
		}

		return "redirect:/departments";
	}

	/* DELETE */

	@GetMapping("/departments/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (depRepo.existsById(id))
			depRepo.deleteById(id);
		else
			throw new NoDataFoundException("No department found for id = " + id);

		return "redirect:/departments";
	}

}

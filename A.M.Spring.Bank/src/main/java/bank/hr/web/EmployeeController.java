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
import bank.hr.repo.BranchRepository;
import bank.hr.repo.DepartmentRepository;
import bank.hr.repo.EmployeeRepository;
import bank.hr.repo.JobGradeRepository;
import bank.hr.repo.PositionRepository;
import bank.hr.exception.NoDataFoundException;
import bank.hr.model.Branch;
import bank.hr.model.Department;
import bank.hr.model.JobGrade;
import bank.hr.model.Position;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private DepartmentRepository depRepo;
	@Autowired
	private JobGradeRepository jobRepo;
	@Autowired
	private PositionRepository posRepo;
	@Autowired
	private BranchRepository brRepo;

	/* READ */

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Employee> opt = empRepo.findById(id);
		if (opt.isPresent()) {
			Employee employee = opt.get();
			model.addAttribute("employee", employee);
		}
		return "employee";
	}

	@GetMapping()
	public String displayAll(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees";
	}

	/* CREATE */

	@GetMapping("/form")
	public String displayForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		addEmployeeAttributesToModel(model);

		return "employee-form";
	}

	private void addEmployeeAttributesToModel(Model model) {
		List<Department> departments = depRepo.findAll();
		List<JobGrade> jobGrades = jobRepo.findAll();
		List<Position> positions = posRepo.findAll();
		List<Branch> branches = brRepo.findAll();

		model.addAttribute("departments", departments);
		model.addAttribute("jobGrades", jobGrades);
		model.addAttribute("positions", positions);
		model.addAttribute("branches", branches);
	}

	@GetMapping("/form/{id}")
	private String dispplayForm(@PathVariable Long id, Model model) {
		Optional<Employee> opt = empRepo.findById(id);
		if (opt.isPresent()) {
			Employee employee = opt.get();
			model.addAttribute("employee", employee);

			addEmployeeAttributesToModel(model);

		} else
			throw new NoDataFoundException("No employee found for id = " + id);

		return "employee-form";

	}
	/* SAVE FORM (CREATE or UPDATE) */

	@PostMapping("/save")
	public String createOne(@Valid Employee employee, Errors errors) {
		if (errors.hasErrors())
			return "employee-form";

		Long id = employee.getId();
		if (id == null) {
			if (employee.getDepartment() != null) {
				Long depId = employee.getDepartment().getId();
				Department department = depRepo.findById(depId).get();
				employee.setDepartment(department);
			}
			if (employee.getJobGrade() != null) {
				Long jgId = employee.getJobGrade().getId();
				JobGrade jobGrade = jobRepo.findById(jgId).get();
				employee.setJobGrade(jobGrade);
			}
			if (employee.getPosition() != null) {
				Long posId = employee.getPosition().getId();
				Position position = posRepo.findById(posId).get();
				employee.setPosition(position);
			}
			if (employee.getBranch() != null) {
				Long braId = employee.getBranch().getId();
				Branch branch = brRepo.findById(braId).get();
				employee.setBranch(branch);

			}
		}

		empRepo.save(employee);

		return "redirect:/employees";
	}

	/* DELETE */

	@GetMapping("/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (empRepo.existsById(id))
			empRepo.deleteById(id);
		else
			throw new NoDataFoundException("No employee found for id = " + id);

		return "redirect:/employees";

	}
}

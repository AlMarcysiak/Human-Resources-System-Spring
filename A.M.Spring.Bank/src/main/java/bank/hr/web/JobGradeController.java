package bank.hr.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bank.hr.model.Employee;
import bank.hr.model.JobGrade;
import bank.hr.repo.EmployeeRepository;
import bank.hr.repo.JobGradeRepository;

@Controller
@RequestMapping(value = "/jobGrades")
public class JobGradeController {

	@Autowired
	private JobGradeRepository jobRepo;
	@Autowired
	private EmployeeRepository empRepo;

	/* READ */

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<JobGrade> opt = jobRepo.findById(id);
		if (opt.isPresent()) {
			JobGrade jobGrade = opt.get();
			model.addAttribute("jobGrade", jobGrade);

			List<Employee> employees = empRepo.findAllByJobGrade(jobGrade);
			model.addAttribute("employees", employees);
		}
		return "jobGrade";
	}

	@GetMapping()
	public String displayAll(Model model) {
		List<JobGrade> jobGrades = jobRepo.findAll();
		model.addAttribute("jobGrades", jobGrades);
		return "jobGrades";
	}
}

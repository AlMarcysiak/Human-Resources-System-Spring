package bank.hr.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.hr.model.*;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
	public List<Employee> findAllByDepartment(Department department);
	public List<Employee> findAllByBranch(Branch branch);
	public List<Employee> findAllByJobGrade(JobGrade jobGrade);
	public List<Employee> findAllByPosition(Position position);
}
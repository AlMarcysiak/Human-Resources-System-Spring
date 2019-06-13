package bank.hr.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.hr.model.*;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	public Optional<Department> findByName(String name);
	
}
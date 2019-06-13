package bank.hr.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import bank.hr.model.*;

public interface BranchRepository extends JpaRepository<Branch, Long>{

	
}

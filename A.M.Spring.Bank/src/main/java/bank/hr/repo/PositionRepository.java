package bank.hr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.hr.model.*;

public interface PositionRepository extends JpaRepository<Position, Long> {
	
}
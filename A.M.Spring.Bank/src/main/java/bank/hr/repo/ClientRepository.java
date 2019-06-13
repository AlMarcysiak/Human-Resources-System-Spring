package bank.hr.repo;




import org.springframework.data.jpa.repository.JpaRepository;


import bank.hr.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	
}

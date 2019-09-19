package bank.hr.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.hr.model.Account;
import bank.hr.model.Client;

public interface AccountRepository extends JpaRepository<Account, Long> {
	public List<Client> findAllByClient(Account account );
}



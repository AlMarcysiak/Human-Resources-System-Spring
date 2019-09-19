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

import bank.hr.exception.NoDataFoundException;
import bank.hr.model.Account;
import bank.hr.model.Client;
import bank.hr.repo.AccountRepository;
import bank.hr.repo.ClientRepository;

@Controller
@RequestMapping(value="/accounts")
public class AccountController {

	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private ClientRepository clRepo;

	/* READ */
	
	@GetMapping()
	public String displayAll(Model model) {
		List<Account> accounts = accRepo.findAll();
		model.addAttribute("accounts", accounts);

		return "accounts";
	}

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Account> opt = accRepo.findById(id);
		if (opt.isPresent()) {
			Account account = opt.get();
			model.addAttribute("account", account);


		} else
			throw new NoDataFoundException("No account found for id = " + id);

		return "account";
	}

	/* CREATE */

	@GetMapping("/form")
	public String displayForm(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		
		List <Client>clients= clRepo.findAll();
		model.addAttribute("clients", clients);
		
		return "account-form";
	}

	
	/* UPDATE */

	@GetMapping("/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Account> opt = accRepo.findById(id);

		if (opt.isPresent()) {
			Account account = opt.get();
			model.addAttribute("account", account);
			
			List <Client>clients= clRepo.findAll();
			model.addAttribute("clients", clients);
		} else
			throw new NoDataFoundException("No account found for id = " + id);

		return "account-form";
	}

	
	/* SAVE FORM (CREATE or UPDATE) */

	@PostMapping("/save")
	public String createOne(@Valid Account account, Errors errors) {
		if (errors.hasErrors())
			return "account-form";

		Long id = account.getId();
		if (id == null) {
			if (account.getClient() != null) {
				Long clientId = account.getClient().getId();
				 
				Client client = clRepo.findById(clientId).get();
				account.setClient(client);
			}
		}

		accRepo.save(account);

		return "redirect:/accounts";
	}

	/* DELETE */

	@GetMapping("/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (accRepo.existsById(id))
			accRepo.deleteById(id);
		else
			throw new NoDataFoundException("No account found for id = " + id);

		return "redirect:/accounts";
	}
}

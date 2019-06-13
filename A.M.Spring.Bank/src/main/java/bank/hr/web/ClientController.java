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
import bank.hr.model.Client;

import bank.hr.repo.ClientRepository;

@Controller
public class ClientController {

	
	@Autowired
	private ClientRepository clRepo;
	
	@GetMapping(value="/clients")
	public String displayAll(Model model) {
		List<Client> clients = clRepo.findAll();
		model.addAttribute("clients", clients);
		return "clients";
	}
	
	@GetMapping(value="/clients/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Client> opt =clRepo.findById(id);
		if(opt.isPresent()) {
			Client client = opt.get();
			model.addAttribute("client", client);
		}
		return "client";
	}
/* CREATE */
	
	@GetMapping("/clients/form")
	public String displayForm(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "client-form";
	}


	/* --------------------------------------- */
	/* UPDATE */
	
	@GetMapping("/clients/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Client> opt = clRepo.findById(id);
		
		if (opt.isPresent()) {
			Client client = opt.get();
			model.addAttribute("client", client);
		}
		else
			throw new NoDataFoundException("No client found for id = " + id);

		return "client-form";
	}


	
	/* SAVE FORM (CREATE or UPDATE) */

	@PostMapping("/clients/save")
	public String createOne(@Valid Client client, Errors errors) {
		if (errors.hasErrors())
			return "client-form";
			
		Long id = client.getId();
		if (id == null) {
			clRepo.save(client);
		}
		else {
			Optional<Client> opt = clRepo.findById(client.getId());
			
			if (opt.isPresent()) {
				Client oneToUpdate = opt.get();
				oneToUpdate.setFirstName(client.getFirstName());
				oneToUpdate.setLastName(client.getLastName());
				oneToUpdate.setDate(client.getDate());
				clRepo.save(oneToUpdate);
			}
			else
				throw new NoDataFoundException("No client found for id = " + id);
		}
		
		return "redirect:/clients";
	}


	
	/* DELETE */
	
	@GetMapping("/clients/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (clRepo.existsById(id))
			clRepo.deleteById(id);
		else
			throw new NoDataFoundException("No client found for id = " + id);

		return "redirect:/clients";
	}
}

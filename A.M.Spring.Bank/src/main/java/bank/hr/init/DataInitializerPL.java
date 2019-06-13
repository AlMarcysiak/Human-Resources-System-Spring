package bank.hr.init;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bank.hr.model.*;
import bank.hr.repo.*;

@Repository
public class DataInitializerPL implements DataInitializer {

	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private BranchRepository braRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private DepartmentRepository depRepo;
	@Autowired
	private JobGradeRepository jgRepo;
	@Autowired
	private PositionRepository posRepo;
	

	@Override
	@Transactional
	public void insertData() {

		/* client */
		Client cl1 = createClient("Adam", "Kowalski", "2017-05-02");
		Client cl2 = createClient("Jan", "Lastkowski", "2018-05-08");
		Client cl3 = createClient("Wiktor", "Pity", "2018-09-23");
		Client cl4 = createClient("Przemyslaw", "Budzik", "2017-10-03");
		Client cl5 = createClient("Mikolaj", "Kalat", "2019-03-23");
		Client cl6 = createClient("Aleksander", "Kaszowski", "2017-12-08");
		Client cl7 = createClient("Adam", "Niborak", "2019-05-23");
		Client cl8 = createClient("Andrzej", "Kucharczyk", "2017-03-23");
		Client cl9 = createClient("Piotr", "Fila", "2018-03-11");
		Client cl10 = createClient("Kacper", "Adamicki", "2018-08-23");
		Client cl11 = createClient("Ryszard", "Kowalski", "2019-06-14");
		Client cl12 = createClient("Wiktor", "Kwiatkowski", "2017-04-11");
		Client cl13 = createClient("Andrzej", "Balski", "2017-03-23");

		clientRepo.saveAll(Arrays.asList(cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl8, cl9, cl10, cl11, cl12, cl13));

		/* account */
		Account konto = createAccount(7823618, cl1);
		Account konto1 = createAccount(8372398, cl2);
		Account konto2 = createAccount(2893112, cl3);
		Account konto3 = createAccount(2836721, cl4);
		Account konto4 = createAccount(88239182, cl5);
		Account konto5 = createAccount(9238917, cl6);
		Account konto6 = createAccount(2301971, cl7);
		Account konto7 = createAccount(78621901, cl8);
		Account konto8 = createAccount(8210983, cl9);
		Account konto9 = createAccount(80927312, cl1);
		Account konto10 = createAccount(87348692, cl10);
		Account konto11 = createAccount(2973198, cl11);
		Account konto12 = createAccount(64298192, cl12);

		accRepo.saveAll(Arrays.asList(konto, konto1, konto2, konto3, konto4, konto5, konto6, konto7, konto8, konto9,
				konto10, konto11, konto12));

		/* city */
		City poznan = createCity("Poznan", "Wielkopolska");
		City warszawa = createCity("Warszawa", "Mazowsze");
		City bydgoszcz = createCity("Bydgoscz", "Kuj-Pom");
		City gdansk = createCity("Gdansk", "Pomorze");
		City gdynia = createCity("Gdynia", "Pomorze");
		City gniezno = createCity("Gniezno", "Wielkopolska");

		cityRepo.save(poznan);
		cityRepo.save(warszawa);
		cityRepo.save(bydgoszcz);
		cityRepo.save(gdansk);
		cityRepo.save(gdynia);
		cityRepo.save(gniezno);
		/* branch */
		Branch oddzial = createBranch("Odzial I", poznan);
		Branch oddzial1 = createBranch("Odzial II", warszawa);
		Branch oddzial2 = createBranch("Odzial III", bydgoszcz);
		Branch oddzial3 = createBranch("Odzial IV", gniezno);
		Branch oddzial4 = createBranch("Odzial V", gdansk);
		Branch oddzial5 = createBranch("Odzial VI", gdynia);
		Branch oddzial6 = createBranch("Odzial VII", warszawa);
		Branch oddzial7 = createBranch("Odzial VIII", poznan);

		braRepo.save(oddzial);
		braRepo.save(oddzial7);
		braRepo.save(oddzial6);
		braRepo.save(oddzial5);
		braRepo.save(oddzial4);
		braRepo.save(oddzial3);
		braRepo.save(oddzial2);
		braRepo.save(oddzial1);

		/* departments */
		Department management = createDepartment("Zarządzanie");
		Department finance  = createDepartment("Finanse");
		Department accounting = createDepartment("Księgowość");
        Department consulting  = createDepartment("Consulting");
		
		depRepo.save(management);
		depRepo.save(finance );
		depRepo.save(accounting);
		depRepo.save(consulting);

		/* positions */
		Position manager = createPosition("Szef Działu");
		Position analyst = createPosition("Analityk Finansowy");
		Position adviser = createPosition("Doradzca Klienta");
		Position accountant = createPosition("Księgowy");

		posRepo.save(manager);
		posRepo.save(analyst);
		posRepo.save(adviser);
		posRepo.save(accountant);

		/* job grades */
		JobGrade i = createJobGrade("I", 0, 1999);
		JobGrade ii = createJobGrade("II", 2000, 3999);
		JobGrade iii = createJobGrade("III", 4000, 6999);
		JobGrade iv = createJobGrade("IV", 7000, 19999);

		jgRepo.saveAll(Arrays.asList(i, ii, iii, iv));

		/* employees */
		Employee emp1 = createEmployee("Adam", "Kowalski", "2017-05-02", 6900, consulting, adviser, iii,oddzial4);
		Employee emp2 = createEmployee("Andrzej", "Rokita", "2019-04-11", 14900, management, manager, iv,oddzial6);
		Employee emp3 = createEmployee("Piotr", "Kwiatek", "2017-06-21", 5900, finance , analyst, iii,oddzial2);
		Employee emp4 = createEmployee("Patryk", "Nowak", "2017-01-01", 3600, consulting, adviser, ii,oddzial2);
		Employee emp5 = createEmployee("Ignacy", "Rastacki", "2018-12-21", 10900, finance , analyst, iii,oddzial7);
		Employee emp6 = createEmployee("Jan", "Liktucki", "2017-04-12", 5400, management, manager, iii,oddzial);
		Employee emp7 = createEmployee("Rafał", "Zarka", "2018-02-05", 1200, consulting, adviser, i,oddzial2);
		Employee emp8 = createEmployee("Borys", "Golden", "2019-04-09", 12000, management, manager, iv,oddzial6);
		Employee emp9 = createEmployee("Aleksander", "Dominiak", "2018-11-01", 7900, accounting, accountant, iv,oddzial1);
		Employee emp10 = createEmployee("Jakub", "Pietrzak", "2017-01-19", 1900, consulting, adviser, i,oddzial7);
		Employee emp11 = createEmployee("Igor", "Kaniewski", "2017-12-01", 9000, finance , analyst, iv,oddzial6);
		Employee emp12 = createEmployee("Mikołaj", "Pietrzak", "2017-01-29", 6500, accounting, accountant, iii,oddzial5);
		Employee emp13 = createEmployee("Zofia", "Kowalska", "2019-02-01", 16000, management, manager, iv,oddzial4);
		Employee emp14 = createEmployee("Jan", "Nowak", "2009-07-15", 4500, finance , analyst, iii,oddzial3);
		Employee emp15 = createEmployee("Wojciech", "Trzaska", "2012-10-01", 13500, finance , analyst, iv,oddzial2);
		Employee emp16 = createEmployee("Ewa", "Mucha", "1999-01-01", 7200, accounting, accountant, iv,oddzial);
		Employee emp17 = createEmployee("Marek", "Kania", "2015-05-01", 2900, accounting, accountant, ii,oddzial1);

		empRepo.saveAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9, emp10, emp11, emp12, emp13,
				emp14, emp15, emp16, emp17));

	}

	private Account createAccount(long number, Client client) {
		Account account = new Account();
		account.setNumber(number);
		account.setClient(client);

		return account;
	}

	private Branch createBranch(String name, City city) {
		Branch branch = new Branch();
		branch.setName(name);
		branch.setCity(city);
		return branch;
	}

	private City createCity(String name, String voivodeship) {
		City city = new City();
		city.setName(name);
		city.setVoivodeship(voivodeship);
		return city;
	}

	private Client createClient(String name, String lastName, String date) {
		Client client = new Client();
		client.setFirstName(name);
		client.setLastName(lastName);
		client.setDate(parseDate(date));
		return client;
	}

	private Department createDepartment(String name) {
		Department one = new Department();
		one.setName(name);
		return one;
	}

	private Position createPosition(String name) {
		Position one = new Position();
		one.setName(name);
		return one;
	}

	private JobGrade createJobGrade(String name, double minSalary, double maxSalary) {
		JobGrade one = new JobGrade();
		one.setName(name);
		one.setMinSalary(minSalary);
		one.setMaxSalary(maxSalary);
		return one;
	}


	private Employee createEmployee(String firstName, String lastName, String hireDate, double salary,
			Department department, Position position, JobGrade jobGrade, Branch branch) {
		Employee one = new Employee();
		one.setFirstName(firstName);
		one.setLastName(lastName);
		one.setHireDate(parseDate(hireDate));
		one.setSalary(salary);
		one.setDepartment(department);
		one.setPosition(position);
		one.setJobGrade(jobGrade);
		one.setBranch(branch);

		return one;
	}

	private LocalDate parseDate(String dateText) {
		if (dateText == null)
			return null;
		LocalDate date = LocalDate.parse(dateText);
		return date;
	}

}

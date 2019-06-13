package bank.hr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.hr.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}

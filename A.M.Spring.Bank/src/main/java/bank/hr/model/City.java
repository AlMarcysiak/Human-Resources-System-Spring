package bank.hr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="city")
@NoArgsConstructor @Getter @Setter
public class City {

	@Id
	@Column(name="city_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@NotBlank 
	@Size(min = 1, max = 20)
	@Column(name="city_name")
	private String name;
	
	@NotBlank 
	@Size(min = 1, max = 20)
	@Column(name="voivodeship")
	private String voivodeship;
}

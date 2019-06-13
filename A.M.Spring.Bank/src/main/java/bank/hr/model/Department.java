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
@Table(name = "departments")
@NoArgsConstructor @Getter @Setter
public class Department {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dep_id")
	private Long id;
	
	@NotBlank 
	@Size(min = 1, max = 50)
	private String name;
	
	
}

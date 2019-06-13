package bank.hr.model;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "clients")
@NoArgsConstructor @Getter @Setter
public class Client {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cl_id")
	private Long id; 
	
	@NotBlank
	@Size(min=1)
	@Column(name="first_name")
	private String firstName; 
	
	@NotBlank
	@Size(min=1)
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_of_attachment")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date; 
	

	

}

package bank.hr.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@NoArgsConstructor @Getter @Setter
public class Employee {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long id;
		
	@NotBlank 
	@Size(min = 1, max = 20)
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank 
	@Size(min = 1, max = 25)
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "hire_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hireDate;
	
	@Column(name="salary")
	private double salary;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="dep_id")
	private Department department;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="pos_id")
	private Position position;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="jg_id")
	private JobGrade jobGrade;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="br_id")
	private Branch branch;
	
}

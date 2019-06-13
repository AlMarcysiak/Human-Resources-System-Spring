package bank.hr.model;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="branch")
@NoArgsConstructor @Getter @Setter
public class Branch {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="br_id")
	private Long id;

	@NotBlank
	@Size(min=1, max=15)
	@Column(name="branch_name")
	private String name;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="city_id")
	private City city;
}

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
@Table(name = "positions")
@NoArgsConstructor @Getter @Setter
public class Position {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pos_id")
	private Long id;
	
	@NotBlank 
	@Size(min = 1, max = 50)
	@Column(name = "position_name")
	private String name;

}

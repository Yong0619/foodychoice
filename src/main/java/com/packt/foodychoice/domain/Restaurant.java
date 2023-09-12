package com.packt.foodychoice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="`restaurant`")
@NoArgsConstructor
public class Restaurant {
	@Id
	@GeneratedValue
	@Column(name="`res_id`")
	private String res_id;
	@Column
	private String k_name, j_name, k_addr, j_addr, k_type, j_type, k_sig_menu, j_sig_menu, tel;
	
}

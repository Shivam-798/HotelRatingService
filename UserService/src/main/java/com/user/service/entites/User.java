package com.user.service.entites;

import jakarta.persistence.Entity;
import java.util.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="micro_users")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

	@Id
	private String userId;
	
	private String name;
	
	private String email;
	
	private String about;
	
	@Transient //isse hoga ye ki ye field data base mein nahi hogi store
	private List<Rating> ratings=new ArrayList<>();
}

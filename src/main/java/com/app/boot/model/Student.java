package com.app.boot.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String gender;

	@ManyToOne
	@JoinColumn(name = "idEstablishment")
	private Establishment establishment;

	@ManyToMany(mappedBy = "students")
	private Set<Class> classes;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Post> posts;
}

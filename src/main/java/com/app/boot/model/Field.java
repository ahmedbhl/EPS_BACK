package com.app.boot.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Field implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String fieldName;

	private String description;

	@ManyToOne
	@JoinColumn(name = "idEstablishment")
	private Establishment establishment;

	@ManyToOne
	@JoinColumn(name = "idLevel")
	private Level level;

	@JsonIgnore
	@OneToMany(mappedBy = "field", cascade = { CascadeType.ALL })
	private Set<Class> classes;

}

package com.app.boot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Establishment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String establishmentName;

	private String description;

	private Date yearOfFoundation;

	private String location;

	private String photos;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Administration administration;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "establishment_professors", joinColumns = {
			@JoinColumn(name = "establishment_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "professors_id", referencedColumnName = "id") })
	private Set<Professor> professors;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "establishment_students", joinColumns = {
			@JoinColumn(name = "establishment_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "students_id", referencedColumnName = "id") })
	private Set<Student> students;

	@OneToMany(mappedBy = "establishment", cascade = { CascadeType.ALL })
	private Set<Class> classes;

	@OneToMany(mappedBy = "establishment", cascade = { CascadeType.ALL })
	private Set<Field> fields;

	@JsonIgnore
	@OneToMany(mappedBy = "establishment", cascade = { CascadeType.ALL })
	private Set<Level> levels;
}

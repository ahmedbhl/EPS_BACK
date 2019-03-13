package com.app.boot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToOne;

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Administration administratin;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "establishment_professors", joinColumns = {
			@JoinColumn(name = "establishment_id") }, inverseJoinColumns = { @JoinColumn(name = "professors_id") })
	private Set<Professor> professors = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "establishment_students", joinColumns = {
			@JoinColumn(name = "establishment_id") }, inverseJoinColumns = { @JoinColumn(name = "students_id") })
	private Set<Professor> students = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstablishmentName() {
		return establishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getYearOfFoundation() {
		return yearOfFoundation;
	}

	public void setYearOfFoundation(Date yearOfFoundation) {
		this.yearOfFoundation = yearOfFoundation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public Administration getAdministratin() {
		return administratin;
	}

	public void setAdministratin(Administration administratin) {
		this.administratin = administratin;
	}

	public Set<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(Set<Professor> professors) {
		this.professors = professors;
	}

	public Set<Professor> getStudents() {
		return students;
	}

	public void setStudents(Set<Professor> students) {
		this.students = students;
	}

}

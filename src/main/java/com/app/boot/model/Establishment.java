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

	public Administration getAdministration() {
		return administration;
	}

	public void setAdministration(Administration administration) {
		this.administration = administration;
	}

	public Set<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(Set<Professor> professors) {
		this.professors = professors;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public Set<Field> getFields() {
		return fields;
	}

	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}

	public Set<Level> getLevels() {
		return levels;
	}

	public void setLevels(Set<Level> levels) {
		this.levels = levels;
	}

}

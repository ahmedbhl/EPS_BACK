package com.app.boot.model;

import java.io.Serializable;
import java.util.List;
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
public class Class implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String className;

	private String description;

	private String invitationCode;

	@JsonIgnore
	@OneToMany(mappedBy = "classe", cascade = { CascadeType.ALL })
	private List<Post> posts;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "class_professors", joinColumns = {
			@JoinColumn(name = "class_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "professors_id", referencedColumnName = "id") })
	private Set<Professor> professors;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "class_students", joinColumns = {
			@JoinColumn(name = "class_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "students_id", referencedColumnName = "id") })
	private Set<Student> students;

	@JsonIgnore
	@OneToMany(mappedBy = "classe", cascade = { CascadeType.ALL })
	private Set<Course> courses;

	@ManyToOne
	@JoinColumn(name = "idField")
	private Field field;

}

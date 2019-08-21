package com.app.boot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String courseName;

	private String description;

	@ManyToOne
	@JoinColumn(name = "idClass")
	private Class classe;

	/*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "course_professors", joinColumns = {
			@JoinColumn(name = "course_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "professors_id", referencedColumnName = "id") })*/
	@ManyToOne
	@JoinColumn(name = "idProfessor")
	private Professor professor;

}

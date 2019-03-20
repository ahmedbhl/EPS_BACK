package com.app.boot.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Professor extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String gender;

	@ManyToMany(mappedBy = "professors")
	private Set<Establishment> establishments;

	@ManyToMany(mappedBy = "professors")
	private Set<Class> classes;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Post> posts;

	@ManyToMany(mappedBy = "professors")
	private Set<Course> courses;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<Establishment> getEstablishments() {
		return establishments;
	}

	public void setEstablishments(Set<Establishment> establishments) {
		this.establishments = establishments;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}


	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

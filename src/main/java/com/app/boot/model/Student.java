package com.app.boot.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String gender;

	@ManyToMany(mappedBy = "students")
	private Set<Establishment> establishments;

	@ManyToMany(mappedBy = "students")
	private Set<Class> classes;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Post> posts;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

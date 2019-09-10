/**
 * 
 */
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

/**
 * @author Hp
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Level implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String levelName;

	private String description;

	// @JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "idEstablishment")
	private Establishment establishment;

	@JsonIgnore
	@OneToMany(mappedBy = "level", cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	private Set<Field> fields;
}

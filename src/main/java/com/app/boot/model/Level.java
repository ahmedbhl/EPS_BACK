/**
 * 
 */
package com.app.boot.model;

import javax.persistence.*;

/**
 * @author Hp
 *
 */
@Entity
public class Level {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long levelId;
	private String levelName;
	private String description;

	public Level(long levelId, String levelName, String description) {
		super();
		this.levelId = levelId;
		this.levelName = levelName;
		this.description = description;
	}

	public long getLevelId() {
		return levelId;
	}

	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

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
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;
	private String type;
	private String description;

	public Post(long postId, String type, String description) {
		super();
		this.postId = postId;
		this.type = type;
		this.description = description;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

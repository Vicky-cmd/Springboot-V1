package com.infotrends.in.SpringbootV1.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Comments")
public class Comments {

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private int id;
	
	@ManyToOne
	@JoinColumn( name = "user_id", nullable = false)
	private Users user;

	@NotNull
	@Column(name ="comment", columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "comment_date")
	private Date comment_date = new Date();
	
	@ManyToOne
	@JoinColumn( name = "article_id", nullable = false)
	private Articles tagged_article;
	
	@ManyToOne
	@JoinColumn( name = "id")
	private Comments parent;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private List<Comments> childsLst;

	public Comments() {
		super();
	}

	public Comments(Users user, @NotNull String content, Articles tagged_article,
			Comments parent) {
		super();
		this.user = user;
		this.content = content;
		this.comment_date = comment_date;
		this.tagged_article = tagged_article;
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getComment_date() {
		return comment_date;
	}

	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}

	public Articles getTagged_article() {
		return tagged_article;
	}

	public void setTagged_article(Articles tagged_article) {
		this.tagged_article = tagged_article;
	}

	public Comments getParent() {
		return parent;
	}

	public void setParent(Comments parent) {
		this.parent = parent;
	}

	public List<Comments> getChildsLst() {
		return childsLst;
	}

	public void setChildsLst(List<Comments> childsLst) {
		this.childsLst = childsLst;
	}

	
	
}

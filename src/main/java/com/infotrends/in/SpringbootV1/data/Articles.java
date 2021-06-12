package com.infotrends.in.SpringbootV1.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Articles")
public class Articles {

	
	@Id
	@GeneratedValue
	@Column(name = "article_id")
	private int article_id;
	
	@Column(name = "article_name")
	private String articleName;
	
	@Column(name = "previewInfo")
	private String previewInfo;

	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@Column(name = "submissionTime")
	private Date submissionTime = new Date();

	@ManyToOne
	@JoinColumn( name = "user_id", nullable = false)
	private Users authorInfo;
	
	
	@OneToMany(mappedBy = "tagged_article")
	private List<Comments> tagged_comments;
	
	public Articles() {
		super();
	}

	public Articles(String article_name, String content, Date submissionTime, Users authorInfo) {
		super();
		this.articleName = article_name;
		this.content = content;
		this.submissionTime = submissionTime;
		this.authorInfo = authorInfo;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String article_name) {
		this.articleName = article_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}

	public Users getAuthorInfo() {
		return authorInfo;
	}

	public void setAuthorInfo(Users authorInfo) {
		this.authorInfo = authorInfo;
	}
	
	public List<Comments> getTagged_comments() {
		return tagged_comments;
	}

	public void setTagged_comments(List<Comments> tagged_comments) {
		this.tagged_comments = tagged_comments;
	}
	
	public String getPreviewInfo() {
		return previewInfo;
	}

	public void setPreviewInfo(String previewInfo) {
		this.previewInfo = previewInfo;
	}

	@Override
	public String toString() {
		return "Articles [article_id=" + article_id + ", article_name=" + articleName + ", submissionTime="
				+ submissionTime + "]";
	}
	
	
}

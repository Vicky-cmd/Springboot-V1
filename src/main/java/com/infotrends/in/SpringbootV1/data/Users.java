package com.infotrends.in.SpringbootV1.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.infotrends.in.SpringbootV1.model.UsersAuthModel;

@Entity
@Table(name="users")
public class Users {
	
	public Users() {}
	
	public Users(UsersAuthModel userAuthModel) {
		fullname = userAuthModel.getFullname();
		username = userAuthModel.getUsername();
		password = userAuthModel.getPassword();
	}

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int id;
	
	@NotNull
	@Column(name = "Fullname", columnDefinition = "TEXT")
	private String fullname;
	
	@NotNull
	@Pattern(regexp = "^(.+)@(.+).(.+)$", message = "Please Enter a Valid Email Id")
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;

	@NotNull
	@Column(name = "isAdminUser", columnDefinition = "Varchar2(2)")
	private String isAdmin = "N";
	
	@NotNull
	@Column(name = "Timestamp")
	private Date timestamp = new Date();
	
	@NotNull
	@Column(name = "isOauthAccount")
	private String isOauthAccount = "N";
	
	@OneToOne(cascade = CascadeType.ALL)
	private OauthUserData oauthUsrData;
	
	@OneToMany(mappedBy = "authorInfo", cascade = CascadeType.REMOVE)
	private List<Articles> subArticles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Comments> commentsHistory;
	
	@Column(name = "isAccountActive")
	private String isAccountActive = "Y";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getIsOauthAccount() {
		return isOauthAccount;
	}
	public void setIsOauthAccount(String isOauthAccount) {
		this.isOauthAccount = isOauthAccount;
	}
	public OauthUserData getOauthUsrData() {
		return oauthUsrData;
	}

	public void setOauthUsrData(OauthUserData oauthUsrData) {
		this.oauthUsrData = oauthUsrData;
	}
	
	public List<Articles> getSubArticles() {
		return subArticles;
	}

	public void setSubArticles(List<Articles> subArticles) {
		this.subArticles = subArticles;
	}
	
	public List<Comments> getCommentsHistory() {
		return commentsHistory;
	}

	public void setCommentsHistory(List<Comments> commentsHistory) {
		this.commentsHistory = commentsHistory;
	}
	
	public String getIsAccountActive() {
		return isAccountActive;
	}

	public void setIsAccountActive(String isAccountActive) {
		this.isAccountActive = isAccountActive;
	}

	@Override
	public String toString() 
	{
		if(isAdmin!=null && isAdmin.equalsIgnoreCase("Y")) {
			return "User [id=" + id + ", uname=" + username + ", Admin User]";
		} else {
			return "User [id=" + id + ", uname=" + username + "]";
		}
	}
	
}

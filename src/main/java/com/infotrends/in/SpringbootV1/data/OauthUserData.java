package com.infotrends.in.SpringbootV1.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "OauthUserData")
public class OauthUserData {

	@Id
	@GeneratedValue
	private int Id;
	
	@Column(name = "profilePicUrl")
	private String profilePicUrl;
	
	@Column(name = "isEmailVerified")
	private String isEmailVerified;
	
	@Column(name = "locale")
	private String locale;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(String isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	
	
}

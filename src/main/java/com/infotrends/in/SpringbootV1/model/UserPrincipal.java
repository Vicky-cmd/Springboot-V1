package com.infotrends.in.SpringbootV1.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.infotrends.in.SpringbootV1.data.Users;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Users user;
	public UserPrincipal(Users user) {
		super();
		this.user = user;
	}
	
	public Integer getUserId() {
		return user.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if(user.getIsAdmin()!=null && user.getIsAdmin().equalsIgnoreCase("Y")) {
			return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
		}
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "UserPrincipal [user=" + user + "]";
	}
}

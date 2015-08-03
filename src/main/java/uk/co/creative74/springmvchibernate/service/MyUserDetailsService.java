package uk.co.creative74.springmvchibernate.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.creative74.springmvchibernate.dao.UserDao;
import uk.co.creative74.springmvchibernate.model.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	static final Logger appLog = LoggerFactory.getLogger("application-log");

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	
		appLog.debug("loadUserByUsername ...");
		
		uk.co.creative74.springmvchibernate.model.User user = userDao.findByUsername(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);
		
	}

	// Converts uk.co.creative74.springmvchibernate.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(uk.co.creative74.springmvchibernate.model.User user, List<GrantedAuthority> authorities) {
		
		appLog.debug("buildUserForAuthentication ...");
		
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		
		appLog.debug("buildUserAuthority ...");

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}
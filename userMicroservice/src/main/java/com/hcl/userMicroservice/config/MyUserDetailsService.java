//package com.hcl.userMicroservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.hcl.userMicroservice.model.Security;
//import com.hcl.userMicroservice.repository.SecurityRepository;
//
//import java.util.Optional;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private SecurityRepository securityRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
//		final Optional<Security> optionalUser = securityRepository.findByUsername(username);
//		if (optionalUser.isEmpty()) {
//			throw new UsernameNotFoundException(username);
//		}
//		final Security security = optionalUser.get();
//
//		MyUserDetails myUserDetails = new MyUserDetails(security.getUsername(), security.getPassword(),
//				security.getActive(), security.getRole());
//		return myUserDetails;
//	}
//}

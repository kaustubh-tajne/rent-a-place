//package com.hcl.userMicroservice.dao.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.stereotype.Service;
//
//import com.hcl.userMicroservice.model.Security;
//import com.hcl.userMicroservice.repository.SecurityRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class SecurityDaoService {
//
//	private final SecurityRepository securityRepository;
//
//	@Autowired
//	public SecurityDaoService(final SecurityRepository userRepository) {
//		this.securityRepository = userRepository;
//	}
//
//	public List<Security> getAll() {
//		return securityRepository.findAll();
//	}
//
//	public Optional<Security> getOneById(long id) {
//		return securityRepository.findById((int) id);
//	}
//
//	public Security create(Security security) {
//		return securityRepository.save(security);
//	}
//
//	public Security update(Security security) {
//		return securityRepository.save(security);
//	}
//
//	public boolean delete(long id) {
//		securityRepository.deleteById((int) id);
//		return true;
//	}
//}

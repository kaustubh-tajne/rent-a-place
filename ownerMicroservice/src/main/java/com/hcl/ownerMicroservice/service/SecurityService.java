//package com.hcl.ownerMicroservice.service;
//
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hcl.ownerMicroservice.dao.service.SecurityDaoService;
//import com.hcl.ownerMicroservice.dto.SecurityDto;
//import com.hcl.ownerMicroservice.exceptions.UserIdNotFoundException;
//import com.hcl.ownerMicroservice.mapper.SecurityMapper;
//import com.hcl.ownerMicroservice.model.Security;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class SecurityService {
//
//	private final SecurityDaoService securityDaoService;
//
//	@Autowired
//	public SecurityService(final SecurityDaoService securityDaoService) {
//		this.securityDaoService = securityDaoService;
//	}
//
//	public List<SecurityDto> getAllUsers() {
//		final List<Security> user = securityDaoService.getAll();
//		return SecurityMapper.toDto(user);
//	}
//
//	public SecurityDto getOneById(long id) {
//		final Optional<Security> optionalUser = securityDaoService.getOneById(id);
//		if (optionalUser.isEmpty()) {
//			throw new UserIdNotFoundException(id);
//		}
//		return SecurityMapper.toDto(optionalUser.get());
//	}
//
//	public SecurityDto create(SecurityDto userDto) {
//		final Security user = SecurityMapper.toEntity(userDto);
//		final Security savedUser = securityDaoService.create(user);
//		return SecurityMapper.toDto(savedUser);
//	}
//
//	public SecurityDto update(SecurityDto userDto) {
//		final Security user = SecurityMapper.toEntity(userDto);
//		final Security updatedUser = securityDaoService.update(user);
//		return SecurityMapper.toDto(updatedUser);
//	}
//
//	public boolean delete(long id) {
//
//		return securityDaoService.delete(id);
//	}
//
//}

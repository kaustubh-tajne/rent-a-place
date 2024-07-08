//package com.hcl.ownerMicroservice.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import com.hcl.ownerMicroservice.dto.SecurityDto;
//import com.hcl.ownerMicroservice.service.SecurityService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/rentAPLace/v1/security")
//public class SecurityController {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class.getName());
//
//	private final SecurityService securityService;
//
//	@Autowired
//	public SecurityController(SecurityService securityService) {
//		this.securityService = securityService;
//	}
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@GetMapping
//	public ResponseEntity<List<SecurityDto>> getAllUsers() {
//		List<SecurityDto> users = securityService.getAllUsers();
//		LOGGER.info("Retrieved all users");
//		return ResponseEntity.ok(users);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<SecurityDto> getUserById(@PathVariable long id) {
//
//		LOGGER.info("getOneById - {}", id);
//		final SecurityDto result = securityService.getOneById(id);
//		if (result == null) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(result);
//	}
//
//	@PostMapping
//	public ResponseEntity<SecurityDto> creat(@RequestBody SecurityDto securityDto) {
//		LOGGER.info("{} - create", securityDto);
//		securityDto.setPassword(passwordEncoder.encode(securityDto.getPassword()));
//		final SecurityDto result = securityService.create(securityDto);
//		if (result == null) {
//			return ResponseEntity.unprocessableEntity().build();
//		}
//		LOGGER.info("User created: {}", securityDto.getUsername());
//		return new ResponseEntity<>(result, HttpStatus.CREATED);
//	}
//
//	@PutMapping
//	public ResponseEntity<SecurityDto> update(@RequestBody SecurityDto userDto) {
//		LOGGER.info("update - {}", userDto);
//		final SecurityDto result = securityService.update(userDto);
//		if (result == null) {
//			return ResponseEntity.unprocessableEntity().build();
//		}
//		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
//	}
//
//	@DeleteMapping("{id}")
//	public ResponseEntity<Void> delete(@PathVariable long id) {
//		LOGGER.info("delete - {}", id);
//		final boolean isDeleted = securityService.delete(id);
//		if (!isDeleted) {
//			return ResponseEntity.badRequest().build();
//		}
//		return ResponseEntity.noContent().build();
//	}
//
//}

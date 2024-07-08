//package com.hcl.ownerMicroservice.mapper;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import com.hcl.ownerMicroservice.dto.SecurityDto;
//import com.hcl.ownerMicroservice.model.Security;
//
//public class SecurityMapper {
//
//	public static SecurityDto toDto(Security security) {
//		SecurityDto securityDto = new SecurityDto();
//		securityDto.setId(security.getId());
//		securityDto.setUsername(security.getUsername());
//		securityDto.setPassword(security.getPassword());
//		securityDto.setActive(security.getActive());
//		securityDto.setRole(security.getRole());
//		return securityDto;
//	}
//
//	public static Set<SecurityDto> toDto(Set<Security> userSet) {
//		return userSet.stream().map(user -> toDto(user)).collect(Collectors.toSet());
//	}
//
//	public static List<SecurityDto> toDto(List<Security> userSet) {
//		return userSet.stream().map(user -> toDto(user)).collect(Collectors.toList());
//	}
//
//	public static Set<Security> toEntity(Set<SecurityDto> userDtos) {
//		return userDtos.stream().map(userDto -> toEntity(userDto)).collect(Collectors.toSet());
//	}
//
//	public static Security toEntity(SecurityDto userDto) {
//		Security security = new Security();
//		security.setId(userDto.getId());
//		security.setUsername(userDto.getUsername());
//		security.setPassword(userDto.getPassword());
//		security.setActive(userDto.getActive());
//		security.setRole(userDto.getRole());
//		return security;
//	}
//}

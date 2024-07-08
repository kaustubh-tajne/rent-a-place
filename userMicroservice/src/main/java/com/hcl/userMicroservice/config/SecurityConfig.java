//package com.hcl.userMicroservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//// @EnableMethodSecurity
//public class SecurityConfig {
//
//    private static final String[] AUTHENTICATION_WHITE_LISTED = { "/hello", "/api/rentAPLace/v1/reservations/*",
//            "/api/rentAPLace/v1/users/*",
//            "/api/rentAPLace/v1/security**", "/h2-console/**",
//            "/swagger-ui/**", "/swagger-ui/index.html",
//            "/swagger-ui/css/**", "/swagger-resources/**",
//            "/v2/api-docs", "/swagger-resources/**",
//            "/configuration/security", "/swagger-ui.html",
//            "/webjars/**", "/api/v1/auth/**", "/v3/api-docs/**",
//            "/swagger-ui/**" };
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//
//        return myUserDetailsService;
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf()
//                .disable();
//        httpSecurity.headers()
//                .frameOptions()
//                .disable();
//        return httpSecurity.authorizeHttpRequests()
//                .requestMatchers(AUTHENTICATION_WHITE_LISTED)
//                .permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/rentAPLace/v1/reservations/**","/api/rentAPLace/v1/users/**")
//                .authenticated()
//                .and()
//                //.formLogin()
//                .httpBasic()
//                .and()
//                .build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//        // return new BCryptPasswordEncoder();
//    }
//}

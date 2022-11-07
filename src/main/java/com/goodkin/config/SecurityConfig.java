package com.goodkin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // 비밀번호 암호화
    @Bean
    BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
 
       
    // 권한설정 하기
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
            .csrf().disable()
            .httpBasic().disable()
            .authorizeRequests()
                .antMatchers("/admin/utils/**").hasRole("ADMIN")
                .antMatchers("/admin/mypage/**").hasAnyRole("ADMIN")
                .antMatchers("/admin/store/**").hasRole("ADMIN")
                .antMatchers("/admin/inquire/**").hasRole("ADMIN")
                .antMatchers("/admin/customerInquiry/**").hasRole("ADMIN")
                .antMatchers("/admin/menu/**").hasRole("ADMIN")
                .antMatchers("/admin/site/**").hasRole("ADMIN")
                .antMatchers("/admin/review/**").hasRole("ADMIN")
                .anyRequest().permitAll()
            
            .and()
                .formLogin()
                .loginPage("/admin")
                .loginProcessingUrl("/admin/login")
                .usernameParameter("id")
				.passwordParameter("password")
                .defaultSuccessUrl("/admin")
                .failureUrl("/admin/login/failure")
            .and()
                .logout()
                    .logoutUrl("/admin/logout")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/admin");


        // // 중복 로그인 체크하기
        // http.sessionManagement()
        //     .maximumSessions(1) // session 최대 허용 수      
        //     .maxSessionsPreventsLogin(false);   // false : 중복 로그인하면 이전 로그인이 풀린다.
        return http.build();
    }
}

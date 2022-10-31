package com.goodkin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
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
                .anyRequest().permitAll();
            // .and()
            //     .formLogin()
            //     .loginPage("/admin")
            //     .loginProcessingUrl("/admin/adminLoginProc")
            //     .usernameParameter("adminId")
			// 	.passwordParameter("adminPwd")
            //     .defaultSuccessUrl("/admin")
            //     .failureUrl("/admin/login/failure")

            // .and()
            //     .logout()
            //         .logoutUrl("/admin/myadmin/logout")
            //         .invalidateHttpSession(true)
            //         .logoutSuccessUrl("/admin");


        // // 중복 로그인 체크하기
        // http.sessionManagement()
        //     .maximumSessions(1) // session 최대 허용 수      
        //     .maxSessionsPreventsLogin(false);   // false : 중복 로그인하면 이전 로그인이 풀린다.
        return http.build();
    }
}

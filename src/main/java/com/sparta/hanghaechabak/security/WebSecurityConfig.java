package com.sparta.hanghaechabak.security;

import com.sparta.hanghaechabak.config.JwtAuthenticationFilter;
import com.sparta.hanghaechabak.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources", "/configuration/security",
                "/swagger-ui.html", "/webjars/**","/swagger/**");
//                .antMatchers("/swagger-ui.html");
    }
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        http.csrf().disable();
//                .ignoringAntMatchers("/user/**")
//                .ignoringAntMatchers("/api/board")
//                .addFilterBefore(jwtAuthenticationFilter(), new UsernamePasswordAuthenticationFilter())
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/api/board").permitAll()
                // image 폴더를 login 없이 허용
                .antMatchers("/img/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/style/**").permitAll()
                // 회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // JS 폴더를
                .antMatchers("/js/**").permitAll()
                // api
//                    .antMatchers("/api/**").permitAll()
                //Swagger
                .antMatchers("/swagger-ui.html","/swagger/**","/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui",
                        "/swagger-resources", "/configuration/security",
                        "/swagger-ui.html", "/webjars/**","/swagger/**").permitAll()
                // 게시판 글 모두 조회 가능
                .antMatchers("/detail/**").permitAll()
                // 그 외 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                // [로그인 기능]
                .formLogin()
                // 로그인 View 제공 (GET /user/login)
                .loginPage("/user/loginView")
                // 로그인 처리 (POST /user/login)
                .loginProcessingUrl("/user/loginView")
                // 로그인 처리 후 성공 시 URL
                .defaultSuccessUrl("/")
                // 로그인 처리 후 실패 시 URL
                .failureUrl("/user/loginView?error")
                .permitAll()
                .and()
                // [로그아웃 기능]
                .logout()
                // 로그아웃 처리 URL
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll();
    }
}

package com.sparta.hanghaechabak.security;

import com.sparta.hanghaechabak.config.CorsConfig;
import com.sparta.hanghaechabak.jwt.JwtAuthenticationFilter;
import com.sparta.hanghaechabak.jwt.JwtTokenProvider;
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

    private final CorsConfig corsConfig;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web     .ignoring()
                .antMatchers("/api/board")
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
        http.csrf().disable()
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .addFilter(corsConfig.corsFilter())
//                .ignoringAntMatchers("/user/**")
//                .ignoringAntMatchers("/api/board")
//                .addFilterBefore(jwtAuthenticationFilter(), new UsernamePasswordAuthenticationFilter())
        .headers().frameOptions().disable();

        http.authorizeRequests()
                // 테스트
                .antMatchers("/").permitAll()
                // 게시글 메인 페이지
                .antMatchers("/api/board").permitAll()
                // 게시글 페이징
                .antMatchers("/api/board/**").permitAll()
                // image 폴더를 login 없이 허용
                .antMatchers("/img/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/style/**").permitAll()
                // 회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // JS 폴더를
                .antMatchers("/js/**").permitAll()
                // api
                .antMatchers("/oauth/callback/kakao").permitAll()
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
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}

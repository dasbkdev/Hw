package homework.jobsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl manager = new JdbcDaoImpl();
        manager.setJdbcTemplate(jdbcTemplate);

        manager.setUsersByUsernameQuery("""
                select email, password, enabled
                from users
                where email = ?
                """);

        manager.setAuthoritiesByUsernameQuery("""
                select u.email, r.role
                from users u
                join roles r on u.role_id = r.id
                where u.email = ?
                """);

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/vacancies",
                                "/vacancies/*",
                                "/resumes",
                                "/resumes/*",
                                "/register",
                                "/login",
                                "/css/**",
                                "/h2-console/**"
                        ).permitAll()
                        .requestMatchers("/vacancies/create", "/vacancies/*/edit", "/my-vacancies", "/responses/**")
                        .hasAuthority("EMPLOYER")
                        .requestMatchers("/resumes/create", "/resumes/*/edit", "/my-resumes")
                        .hasAuthority("APPLICANT")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/profile", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
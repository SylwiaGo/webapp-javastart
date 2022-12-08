package pl.uz.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
class CustomSecurityConfig {
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

//    tylko uzytkownicy z rolą admin mają dostep do zasobow znajdujacych sie w /admin/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizate) -> authorizate
                //aby wyświetlić strony /admin/** trzeba mić uprawnienia admina
                        .mvcMatchers("/admin/**").hasAnyRole(ADMIN_ROLE)
                //aby wyświetlić strony /cart oraz dodadac cos do koszyka trzeba być zalogowanym w przeciwnym razie ladujemy na formularzu logowania
                        .mvcMatchers("/cart").authenticated()
                        .mvcMatchers("/cart/add/{pid}").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login.loginPage("/login")
                        .permitAll())
//                https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#servlet-considerations-csrf-logout
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());
        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"));
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().mvcMatchers(
                "/img/**",
                "/scripts/**",
                "/styles/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


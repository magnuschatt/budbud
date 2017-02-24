package chatt.budbud

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.bind.annotation.ModelAttribute

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @ModelAttribute
    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
                .antMatchers("/", "/home", "/register").permitAll()
                .anyRequest().authenticated()

        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .permitAll()
    }

}
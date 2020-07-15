package com.Tuequipo.Tuequipo.Seguridad;

import com.Tuequipo.Tuequipo.servicios.EquipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public EquipoServicio equipoServicio;
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(equipoServicio)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.headers().frameOptions().sameOrigin().and()
                .authorizeRequests()
                        .antMatchers("/css/*", "/js/*", "/img/*", "/")
                        .permitAll()
                .and().formLogin()
                        .loginPage("/login")
                                .loginProcessingUrl("/logincheck")
                                .usernameParameter("username")
                                .passwordParameter("pasword")
                                .defaultSuccessUrl("/inicio")
                                .permitAll()
                        .and().logout()
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .permitAll();
            
    }
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/css/*", "/js/*", "/img/*").permitAll()
//                .and().formLogin()
//                .loginPage("/")
//                .loginProcessingUrl("/logincheck")
//                .usernameParameter("email")
//                .passwordParameter("clave")
//                .defaultSuccessUrl("/loginsuccess")
//                .failureUrl("/")
//                .permitAll()
//                .and().logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/buscador")
//                .permitAll()
//                .and().csrf()
//                .disable();
//    }
}

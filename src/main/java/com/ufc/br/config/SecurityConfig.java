package com.ufc.br.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufc.br.security.UserDetailsServiceImplementacao;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImplementacao userDetailsImplementacao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/").permitAll() // Permito todo mundo acessar /inicio
		//.antMatchers("/pessoa/formulario").hasRole("USER") //Somente pessoa com papel "USER" acessa /pessoa/formulario
		.antMatchers("/usuario/cadastrar").permitAll()
		.antMatchers("/prato/cadastrar").hasAnyRole("ADMIN") 
		.antMatchers("/prato/listar").hasAnyRole("ADMIN") // Pessoa com papel "USER" ou "ADMIN" acessa /pessoa/salvar
		.antMatchers("/prato/deletar/**").hasAnyRole("ADMIN") // Pessoa com papel "USER" ou "ADMIN" acessa /pessoa/salvar
		.antMatchers("/carrinho").hasAnyRole("USER")
		.anyRequest().authenticated() // o resto precisa está autenticado
		
		.and()
		.formLogin()
		.loginPage("/usuario/logar") // Esse é o controller que chama nosso formulario
		.permitAll() //permitir acesso para essa url "entrar"
		
		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		.and()
		.logout()
		.logoutSuccessUrl("/usuario/logar?logout") // logout sucesso
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**","/images/**"); // ignora e permite uri's com esses arquivos
	}

}

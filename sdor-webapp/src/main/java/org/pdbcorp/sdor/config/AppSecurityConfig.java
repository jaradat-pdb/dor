/*
 * Copyright 2019 PDB Corp.
 *
 * Proprietary Software built off of open-source software?
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pdbcorp.sdor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jaradat-pdb
 *
 */
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/index", "/prds/**", "/srvs/**").permitAll()
			.antMatchers("/admin/**", "/actuator/**").access("hasRole('ROOT')")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout().permitAll();
		http.exceptionHandling().accessDeniedPage("/403");
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * Spring Security's default PasswordEncoder is built as a DelegatingPasswordEncoder which delegates
		 * instantiation of the type of PasswordEncoder to use to occur at runtime, when storing users/passwords
		 * in memory, the passwords are being provided in plain text and when trying to retrieve the encoder from
		 * the DelegatingPasswordEncoder to validate the password Spring Security can't find one that matches the
		 * way in which these passwords were stored.
		 * 
		 * Prefix {noop} to your passwords in order for Spring Security's DelegatingPasswordEncoder to use the
		 * NoOpPasswordEncoder to validate the password, for development-use only.
		 */
		auth.inMemoryAuthentication()
				.withUser("user").password("{noop}user").roles("USER")
				.and()
				.withUser("admin").password("{noop}admin").roles("USER", "ADMIN")
				.and()
				.withUser("root").password("{noop}root").roles("USER", "ADMIN", "ROOT");
	}
}

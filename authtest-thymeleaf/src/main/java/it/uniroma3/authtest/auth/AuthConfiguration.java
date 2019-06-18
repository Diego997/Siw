package it.uniroma3.authtest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

/**
 * The AuthConfiguration is a Spring Security Configuration.
 * It extends WebSecurityConfigurerAdapter, meaning that it provides the settings for Web security.
 */
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * A reference to the environment of properties defined through the application.properties file.
	 * It is automatically wired before at launch.
	 */
	@Autowired
	private Environment environment;

	/**
	 * The datasource where to find users in our application.
	 * It is a bean (meaning that it is only initialized once)
	 */
	private DataSource dataSource;

	/**
	 * The configure method is the main method in the AuthConfiguration.
	 * it
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		// authorization paragraph: we are going to define here WHO can access WHAT pages
		.authorizeRequests()
		
		// everyone (authenticated or not) can access the home page
		.antMatchers(HttpMethod.GET, "/", "/index").permitAll()
		// only admin can access the admin page
		.antMatchers(HttpMethod.GET, "/admin").hasAnyAuthority("ADMIN")

		// all authenticated users can access all the other pages (that is, welcome)
		.antMatchers(HttpMethod.GET, "/logout", "/welcome").authenticated()

		// login paragraph: we are going to define here how to login
		// use formlogin protocol to perform login
		.and().formLogin()
		.loginPage("/login")
		.permitAll()
		// after login is successful, redirect to /welcome page
		.defaultSuccessUrl("/admin")
		//NOTE: we are using the default configuration for login,
		// meaning that the /login url is automatically mapped to auto-generated page.
		// for our own page, we would need to use loginPage()
		// and write a method for accessing it with GET method (but Spring would still handle the POST automatically)
		// logout paragraph: we are going to define here how to logout
		.and().logout().permitAll();

		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.buildDatasource())
		.authoritiesByUsernameQuery("SELECT email, role FROM funzionario WHERE email=?")
		.usersByUsernameQuery("SELECT email, pwd, 1 as enabled FROM funzionario WHERE email=?");
	}

	@Bean
	DataSource buildDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}
}

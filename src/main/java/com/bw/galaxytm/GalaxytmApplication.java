package com.bw.galaxytm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.bw.galaxytm.entity.ERole;
import com.bw.galaxytm.entity.Role;
import com.bw.galaxytm.entity.Task;
import com.bw.galaxytm.entity.User;

import com.bw.galaxytm.service.impl.RoleServiceImpl;
import com.bw.galaxytm.service.impl.TaskServiceImpl;
import com.bw.galaxytm.service.impl.UserServiceImpl;

@SpringBootApplication
//@RestController
public class GalaxytmApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	private User user;
	
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	private ERole erole;
	
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	private Task task;
	
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(GalaxytmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role role1 = new Role(erole.ROLE_ADMIN);
		role1 = roleServiceImpl.saveRole(role1);
		
		Role role2 = new Role(erole.ROLE_USER);
		role2 = roleServiceImpl.saveRole(role2);
		
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(role1);
		roles.add(role2);
		user = new User("admin", "admin@admin.com", encoder.encode("password"), roles);
		user = userServiceImpl.saveUser(user);
		
		roles = new HashSet<Role>();
		roles.add(role2);
		
		user = new User("user", "user@admin.com", encoder.encode("password"), roles);
		user = userServiceImpl.saveUser(user);
		
		
		task = new Task("", "short description for task 1", false, 2L);
		task.setCreated_on(String.valueOf(new Date().getTime()));
		taskServiceImpl.saveTask(task);
		
		task = new Task("lorem ipsum donor lamaysiya this is a verbouse text for the task and its optional", "short description for task 2", false, 2L);
		task.setCreated_on(String.valueOf(new Date().getTime()));
		taskServiceImpl.saveTask(task);
				
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("*");
//			}
//		};
//	}
	

	

}

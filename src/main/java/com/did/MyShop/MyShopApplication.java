package com.did.MyShop;


import com.did.MyShop.entities.User.Role;
import com.did.MyShop.repositories.users.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			RoleRepository roleRepository
	) {
		return args -> {
			Role role = new Role();
			role.setId(1L);
			role.setLibelle("Admin");
			roleRepository.save(role);

			Role role1 = new Role();
			role.setId(2L);
			role1.setLibelle("Caissier");
			roleRepository.save(role1);

			Role role2 = new Role();
			role.setId(3L);
			role2.setLibelle("Gestionnaire");
			roleRepository.save(role2);

		};
	}

}

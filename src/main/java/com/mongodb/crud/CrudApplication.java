package com.mongodb.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void run(String... args) throws Exception {

//		this.usuarioService.save("Thales", "thales@gmail.com", "94950001");
//		this.usuarioService.save("Mika", "mika@gmail.com", "94950001");
//		this.usuarioService.save("Bennett", "bennett@gmail.com", "94950001");
//		this.usuarioService.save("Faith", "faith@gmail.com", "94950001");

		System.out.println(this.usuarioService.count());
		//System.out.println(this.usuarioService.findAll());
		//this.usuarioService.editUser("5", "Bennett", "bennett@gmail.com", "94950001");
		//this.usuarioService.deleteById("5");
		//System.out.println(this.usuarioService.findById("5"));



	}
}

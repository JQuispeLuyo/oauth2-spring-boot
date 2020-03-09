package com.jquispeluyo.resourceservertest.repositories;



import com.jquispeluyo.resourceservertest.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "user")
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
	Optional<Usuario> findByUsername(String username);
}

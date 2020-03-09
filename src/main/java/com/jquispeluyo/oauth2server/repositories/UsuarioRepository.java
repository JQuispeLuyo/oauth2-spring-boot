package com.jquispeluyo.oauth2server.repositories;


import com.jquispeluyo.oauth2server.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
	Optional<Usuario> findByUsername(String username);
}

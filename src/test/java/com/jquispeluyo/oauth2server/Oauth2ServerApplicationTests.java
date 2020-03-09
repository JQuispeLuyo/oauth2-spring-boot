package com.jquispeluyo.oauth2server;

import com.jquispeluyo.oauth2server.models.Usuario;
import com.jquispeluyo.oauth2server.repositories.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Oauth2ServerApplicationTests {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void crearUsuarioTest() {
        Usuario us = new Usuario();
        us.setFirstname("Jose");
        us.setLastname("Quispe");
        us.setUsername("admin");
        us.setEnabled(true);
        us.setPassword(encoder.encode("123"));

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        us.setRoles(roles);
        Usuario retorno = repo.save(us);

        Assert.assertTrue(retorno.getPassword().equalsIgnoreCase(us.getPassword()));
    }



}

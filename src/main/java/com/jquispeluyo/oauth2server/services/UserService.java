package com.jquispeluyo.oauth2server.services;

import com.jquispeluyo.oauth2server.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .map(usuario -> {
                    List<GrantedAuthority> roles = usuario.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role))
							.collect(Collectors.toList());

					return new User(usuario.getUsername(), usuario.getPassword(),usuario.getEnabled(),true, true, true, roles);
                })
                .orElseGet(() -> {
                    throw new UsernameNotFoundException("No existe el usuario: " + username);
                });
    }

}

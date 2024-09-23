package com.nnk.springboot.configuration;

import com.nnk.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Recherche utilisatueur: "+username);


        Optional<com.nnk.springboot.domain.User> userreturned = userService.getUserByUserName(username);
        com.nnk.springboot.domain.User userFound=null;
        if (userreturned.isPresent()) {
            userFound=userreturned.get();

        } else {
            logger.error("Utilisateur pas trouvé: "+username);
            throw new UsernameNotFoundException("Utilisateur avec le nom " + username + " non trouvé");
        }
        logger.debug(" utilisatueur trouvé: "+userFound.getUsername());


        return new User(userFound.getUsername(), userFound.getPassword(), getGrantedAuthorities(userFound.getRole()));


    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}

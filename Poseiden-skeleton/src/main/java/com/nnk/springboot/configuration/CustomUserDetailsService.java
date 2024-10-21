package com.nnk.springboot.configuration;

import com.nnk.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
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

/**
 * Provides the list of registered users from the database
 *
 * @author Poseidon Capital Solutions
 *
 */

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * Returns all the user's parameters required for security management for a specific user
     *
     * @param username Username of User
     * @throws UsernameNotFoundException if User not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Recherche utilisatueur: "+username);
        Optional<com.nnk.springboot.domain.User> userreturned = userService.getUserByUserName(username);
        com.nnk.springboot.domain.User userFound=null;
        if (userreturned.isPresent()) {
            userFound=userreturned.get();
        } else {
            log.error("Utilisateur pas trouvé: "+username);
            throw new UsernameNotFoundException("Utilisateur avec le nom " + username + " non trouvé");
        }
        log.debug(" utilisateur trouvé: "+userFound.getUsername());
        return new User(userFound.getUsername(), userFound.getPassword(), getGrantedAuthorities(userFound.getRole()));
    }

    /**
     * Builds the list of authorities allowed for a specific role defined for a user
     *
     * @param role definer role of user
     */
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}

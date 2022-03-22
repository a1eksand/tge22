package io.may4th.tge22.security.api;

public interface UserDetailsService {

    UserDetails loadUserByUsername(String username);

    UserDetails loadUserById(String id);
}

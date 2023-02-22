package io.huyvu.reboot.backend.config.auth;

import io.huyvu.reboot.backend.entity.UserAccount;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MyUserDetailRepository myUserDetailRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserAccount> userAccOpt = myUserDetailRepo.findOneByUsername(username);
        return userAccOpt.orElseThrow(()->new UsernameNotFoundException("Not found username: " + username));
    }


}
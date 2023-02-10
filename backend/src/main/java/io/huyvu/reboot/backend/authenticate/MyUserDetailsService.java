package io.huyvu.reboot.backend.authenticate;

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

    private final AuthenticateRepository authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserAccount> userAccOpt = authRepo.findOneByUsername(username);
        return userAccOpt.orElseThrow(()->new UsernameNotFoundException("Not found username: " + username));
    }

}
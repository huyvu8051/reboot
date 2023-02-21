package io.huyvu.reboot.backend.entity

import org.springframework.security.core.userdetails.UserDetails

public interface CustomUserDetails extends UserDetails {
    Long getId()
}
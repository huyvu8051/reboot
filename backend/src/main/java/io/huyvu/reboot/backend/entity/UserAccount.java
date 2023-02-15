package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor

public class UserAccount extends GenericEntity implements CustomUserDetails {
    
    
    private long id;

    /**
     * Email
     */
    private String username;

    private String fullName;

    private String password;

    private String pictureUrl;

    private List<BoardMember> boards = new ArrayList<>();

    private List<ChecklistItem> assigns = new ArrayList<>();


    /**
     * Normal account create
     *
     * @param username
     * @param fullName
     * @param password
     */
    public UserAccount(String username, String fullName, String password) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>() {{
            add((GrantedAuthority) () -> "USER");
        }};
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

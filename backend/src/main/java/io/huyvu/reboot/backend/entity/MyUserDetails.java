package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MyUserDetails extends GenericEntity implements CustomUserDetails {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;

    private String fullName;

    private String password;

    @OneToMany(mappedBy = "key.user")
    private List<BoardMember> boards = new ArrayList<>();

    @OneToMany(mappedBy = "assign")
    private List<ChecklistItem> assigns = new ArrayList<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
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

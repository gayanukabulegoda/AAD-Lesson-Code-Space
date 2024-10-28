package lk.ijse.SpringBootNoteTaker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails, SuperEntity {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NoteEntity> notes;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE" + role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // Not required to implement below methods (removing them is ok if you don't need them)
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

package kz.hotcat.hotcat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.hotcat.hotcat.enums.ROLES_ENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name="user_id")
    private Long id;
    @NonNull
    @Column(nullable = false, unique = true)
    private String username;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(nullable = false, unique = false)
    private String name;
    @NonNull
    @Column(nullable = false, unique = false)
    @JsonIgnore
    private String password;

    @NonNull
    @Column(nullable = false, unique = false)
    private String role = ROLES_ENUM.USER.label;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Payment> paymentList;

    public AppUser(@NonNull String username, @NonNull String email, @NonNull String name, @NonNull String password) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return EMPTY_LIST;
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

package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.AppUser;
import kz.hotcat.hotcat.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailsManager implements UserDetailsManager {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails user) {
        AppUser appUser = (AppUser) user;
        appUser.setPassword(passwordEncoder.encode(user.getPassword()));

        appUserRepository.save(appUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return appUserRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        return appUserRepository.findByUsernameOrEmail(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")) ;
    }
}

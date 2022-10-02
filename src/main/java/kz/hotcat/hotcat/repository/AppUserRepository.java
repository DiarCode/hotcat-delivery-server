package kz.hotcat.hotcat.repository;

import kz.hotcat.hotcat.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
//    Optional<AppUser> findByUsername(String username);

    @Query(value = "SELECT * FROM users u WHERE u.email = ?1 OR u.username = ?1", nativeQuery = true)
    Optional<AppUser> findByUsernameOrEmail(String identifier);
    boolean existsByUsername(String username);
}

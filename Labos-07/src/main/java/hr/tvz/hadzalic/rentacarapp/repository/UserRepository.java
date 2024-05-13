package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.security.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}

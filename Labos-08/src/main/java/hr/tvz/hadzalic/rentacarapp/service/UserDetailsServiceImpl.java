package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.repository.UserRepository;
import hr.tvz.hadzalic.rentacarapp.security.UserInfo;
import hr.tvz.hadzalic.rentacarapp.security.UserRole;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = this.userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Unkonwn user" + username);
        }

        List<UserRole> userRoleList = user.getRoles();

        String[] roles = new String[userRoleList.size()];

        for (int i = 0; i < userRoleList.size(); i++) {
            roles[i] = userRoleList.get(i).getName();
        }

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public void save(UserInfo userInfo) {
        log.info("PRIOVJERA 2 "+userInfo.getPassword());
        userRepository.save(userInfo);
    }
}

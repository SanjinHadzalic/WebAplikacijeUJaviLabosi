package hr.tvz.hadzalic.rentacarapp.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_authority",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "authority_id")}
    )
    private List<UserRole> roles = new ArrayList<>();
}

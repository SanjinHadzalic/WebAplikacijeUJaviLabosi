package hr.tvz.hadzalic.rentacarapp.security;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authority")
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

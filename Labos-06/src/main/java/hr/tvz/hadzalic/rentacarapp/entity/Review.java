package hr.tvz.hadzalic.rentacarapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name = "text")
    private String text;
    @Column(name = "grade")
    private Integer grade;
    @ManyToOne
    @JoinColumn(name = "vozilo_id")
    private Vozilo vozilo;
}

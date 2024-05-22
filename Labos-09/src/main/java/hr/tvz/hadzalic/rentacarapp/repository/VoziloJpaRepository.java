package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoziloJpaRepository extends JpaRepository<Vozilo, Long> {
    @Query("SELECT v FROM Vozilo v WHERE v.registration = :registration")
    Optional<Vozilo> findVoziloByRegistration(@Param("registration") String registration);

    boolean existsByRegistration(String registration);
    boolean existsByVin(String vin);
}

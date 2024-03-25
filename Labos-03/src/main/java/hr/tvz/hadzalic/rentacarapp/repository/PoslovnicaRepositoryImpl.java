package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.dto.PoslovnicaDTO;
import hr.tvz.hadzalic.rentacarapp.entity.Poslovnica;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PoslovnicaRepositoryImpl implements PoslovnicaRepository{

    private static List<Poslovnica> poslovnicaList = new ArrayList<>();

    static {
        poslovnicaList.add(
            new Poslovnica(1L, "Tokic", "Tokiceva 12", 12,
                    LocalDate.of(2026,2,2))
        );
        poslovnicaList.add(
            new Poslovnica(2L, "Autodijelovi", "Bosutska 27", 6,
                    LocalDate.of(2028,2,2))
        );
    }

    @Override
    public List<Poslovnica> findAll() {
        return poslovnicaList;
    }

    @Override
    public Optional<Poslovnica> findPoslovnicaByID(String id) {
        return findAll().stream()
                .filter(x -> x.getId().toString().equals(id))
                .findFirst();

    }

    @Override
    public Optional<Poslovnica> save(Poslovnica poslovnica) {
        poslovnica.setId(generateId(poslovnicaList));
        findAll().add(poslovnica);
        return Optional.of(poslovnica);

    }

    @Override
    public void delete(Long id) {
        poslovnicaList.removeIf(poslovnica -> Objects.equals(poslovnica.getId(), id));

    }

    public Long generateId(List<Poslovnica> poslovnicaList) {
        return poslovnicaList.stream()
                .map(Poslovnica::getId)
                .max(Long::compare).get() + 1;
    }

}

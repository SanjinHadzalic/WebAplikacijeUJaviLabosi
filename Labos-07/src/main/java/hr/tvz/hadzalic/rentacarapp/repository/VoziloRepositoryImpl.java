package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import hr.tvz.hadzalic.rentacarapp.mapper.VoziloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public class VoziloRepositoryImpl implements VoziloRepository{

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private List<Vozilo> voziloList;
    private final String SQL_GET_ALL = "select * from Vozilo";
    private final String SQL_GET_REGISTRATION = "SELECT * FROM VOZILO WHERE registration LIKE ?";
    private final String SQL_UPDATE_VOZILO = "UPDATE VOZILO SET registration=?, vin=?, maxNumberOfPassenger=?, " +
                                            "shifter=?, airConditioning=?, numberOfDoors=?, fuelType=?, " +
                                            "lastServiceDate=?, nextServiceDate=?, mileage=? WHERE id=?";
    private final String SQL_DELETE_VOZILO = "DELETE FROM VOZILO WHERE id = ?";
    private final String SQL_CREATE_VOZILO = "INSERT INTO VOZILO(id, registration, vin, maxNumberOfPassenger, shifter, airConditioning, numberOfDoors, fuelType, " +
            "                   lastServiceDate, nextServiceDate, mileage) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    @Autowired
    public VoziloRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("VOZILO")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Vozilo> findAll() {
        String sql = SQL_GET_ALL;
        voziloList = jdbcTemplate.query(sql, new VoziloMapper());
        return voziloList;
    }

    @Override
    public Optional<Vozilo> findVoziloByCode(String code) {
        return voziloList.stream()
                .filter(x -> x.getId().toString().equals(code))
                .findFirst();
    }

    @Override
    public Optional<Vozilo> findVoziloByRegistration(String registration) {
        List<Vozilo> result =  jdbcTemplate.query(SQL_GET_REGISTRATION, new Object[]{registration.toUpperCase() + '%'}, new VoziloMapper());
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public Optional<Vozilo> findVoziloByVin(String vin) {
        return voziloList.stream()
                .filter(v -> v.getVin().equals(vin))
                .findFirst();
    }

    @Override
    public Optional<Vozilo> update(Long code, Vozilo vozilo) {
        String sql = SQL_UPDATE_VOZILO;
        int rowsAffected = jdbcTemplate.update(sql, vozilo.getRegistration(), vozilo.getVin(), vozilo.getMaxNumberOfPassenger(),
                vozilo.getShifter(), vozilo.getAirConditioning(), vozilo.getNumberOfDoors(),
                vozilo.getFuelType(), vozilo.getLastServiceDate(), vozilo.getNextServiceDate(),
                vozilo.getMileage(), code);

        if (rowsAffected > 0) {
            return Optional.of(vozilo);
        } else {
            return Optional.empty();
        }    }

    @Override
    public Optional<Vozilo> save(Vozilo vozilo) {
        jdbcTemplate.update(SQL_CREATE_VOZILO,
                    generateId(voziloList), vozilo.getRegistration(), vozilo.getVin(), vozilo.getMaxNumberOfPassenger(),
                    vozilo.getShifter(), vozilo.getAirConditioning(), vozilo.getNumberOfDoors(),
                    vozilo.getFuelType(), vozilo.getLastServiceDate(), vozilo.getNextServiceDate(),
                    vozilo.getMileage()
                );

        return Optional.of(vozilo);
    }

    @Override
    public boolean existsByRegistration(String registration) {
        return findAll().stream()
                .filter(q -> q.getRegistration().equals(registration))
                .findFirst()
                .isPresent();
    }

    @Override
    public boolean existsByVin(String vin) {
        return findAll().stream()
                .filter(q -> q.getVin().equals(vin))
                .findFirst()
                .isPresent();
    }

    @Override
    public void delete(Long vehicleCode) {
        String sql = SQL_DELETE_VOZILO;
        jdbcTemplate.update(sql, vehicleCode);
    }

    public Long generateId(List<Vozilo> voziloList) {
        return findAll().stream()
                .map(Vozilo::getId)
                .max(Long::compare).get() + 1;
    }
}

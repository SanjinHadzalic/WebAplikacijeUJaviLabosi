package hr.tvz.hadzalic.rentacarapp.mapper;

import hr.tvz.hadzalic.rentacarapp.entity.Review;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VoziloMapper implements RowMapper<Vozilo> {
    @Override
    public Vozilo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String registration = rs.getString("registration");
        String vin = rs.getString("vin");
        Integer maxNumberOfPassenger = rs.getInt("maxNumberOfPassenger");
        String shifter = rs.getString("shifter");
        String airConditioning = rs.getString("airConditioning");
        Integer numberOfDoors = rs.getInt("numberOfDoors");
        String fuelType = rs.getString("fuelType");
        LocalDate lastServiceDate = rs.getDate("lastServiceDate").toLocalDate();
        LocalDate nextServiceDate = rs.getDate("nextServiceDate").toLocalDate();
        Double mileage = rs.getDouble("mileage");



        Vozilo vozilo = new Vozilo();

        return vozilo;
    }
}

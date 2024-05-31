package hr.tvz.hadzalic.rentacarapp.quartz;

import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import hr.tvz.hadzalic.rentacarapp.service.VoziloService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class VoziloJob extends QuartzJobBean {
    @Autowired
    VoziloService voziloService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Vozilo> vehicles = voziloService.fetchAll();

        System.out.println("Dostupna vozila: ");
        vehicles.forEach(vehicle -> {
            System.out.println("ID: " + vehicle.getId() + ", Registracija: " + vehicle.getRegistration() +
                    ", FuelType: " + vehicle.getFuelType());
        });
    }
}

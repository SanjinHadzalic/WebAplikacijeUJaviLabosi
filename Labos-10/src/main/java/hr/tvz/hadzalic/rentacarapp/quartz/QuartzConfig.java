package hr.tvz.hadzalic.rentacarapp.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    JobDetail jobDetail() {
        return JobBuilder.newJob(VoziloJob.class)
                .withIdentity("voziloJob")
                .storeDurably()
                .build();
    }

    @Bean
    Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("voziloJobTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
    }
}

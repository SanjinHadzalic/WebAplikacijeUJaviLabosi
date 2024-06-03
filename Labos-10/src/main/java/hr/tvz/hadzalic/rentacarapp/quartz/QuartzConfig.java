package hr.tvz.hadzalic.rentacarapp.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean(name = "voziloJob")
    JobDetail jobDetail() {
        return JobBuilder.newJob(VoziloJob.class)
                .withIdentity("voziloJob")
                .storeDurably()
                .build();
    }

    @Bean(name = "timeJob")
    JobDetail jobDetailTwo() {
        return JobBuilder.newJob(TimeJob.class)
                .withIdentity("timeJob")
                .storeDurably()
                .build();
    }

    @Bean
    Trigger trigger(@Qualifier("voziloJob") JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("voziloJobTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
    }

    @Bean
    CronTrigger cronTrigger(@Qualifier("timeJob") JobDetail job){
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("timeJob")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 10 0 ? 1/1 4 *"))
                .forJob("timeJob")
                .build();
    }

    @Bean
    Trigger triggerTwo(@Qualifier("voziloJob") JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("voziloJobTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();
    }}

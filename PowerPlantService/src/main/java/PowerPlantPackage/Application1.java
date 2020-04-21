package PowerPlantPackage;

import PowerPlantPackage.Workflow.WorkProcess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application1 {
    public static void main(String[] args) throws Exception {
        System.out.println("Before");
        SpringApplication.run(Application1.class, args);
        System.out.println("After");

//        WorkProcess workProcess = WorkProcess.getInstance();

        TimerTask repeatedTask = new TimerTask() {
            public void run() {
//                System.out.println("Task performed on " + new Date());
                WorkProcess.getInstance().execute();
            }
        };
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        //Before start
        long delay  = 1000L;

        //Period
        long period = 1000L;
        executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(delay + period * 3);
//        executor.shutdown();
    }

    public void givenUsingExecutorService_whenSchedulingRepeatedTask_thenCorrect()
            throws InterruptedException {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task performed on " + new Date());
            }
        };
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        long delay  = 1000L;
        long period = 1000L;
        executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(delay + period * 3);
        executor.shutdown();
    }
}


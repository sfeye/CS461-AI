package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = controller.MapController.class)
public class Project1Application {

    public static void main (String[] args) {

        SpringApplication.run(Project1Application.class, args);
    }
}

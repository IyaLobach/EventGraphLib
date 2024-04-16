package ru.vitasoft.statusrouteservicelibrary.services.edge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import ru.vitasoft.statusrouteservicelibrary.MySpringApp;

public final class EdgeWalkerFactory {

   private EdgeWalkerFactory() {

   }

    public static EdgeWalker createEdgeWalker() {
        SpringApplication application = new SpringApplication(MySpringApp.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run();
        return context.getBean(EdgeWalkerService.class);
    }
}

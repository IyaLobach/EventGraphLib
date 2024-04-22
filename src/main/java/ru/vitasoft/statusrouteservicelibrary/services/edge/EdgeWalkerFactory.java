package ru.vitasoft.statusrouteservicelibrary.services.edge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import ru.vitasoft.statusrouteservicelibrary.MySpringApp;
import ru.vitasoft.statusrouteservicelibrary.repository.enums.PermissionRepository;
import ru.vitasoft.statusrouteservicelibrary.services.permission.PermissionCheckerService;

public final class EdgeWalkerFactory {

    private static ConfigurableApplicationContext context;

   private EdgeWalkerFactory() {

   }

    public static EdgeWalker createEdgeWalker(PermissionCheckerService permissionCheckerService) {
        SpringApplication application = new SpringApplication(MySpringApp.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run(new String[0]);
        EdgeWalkerService edgeWalker = context.getBean(EdgeWalkerService.class);
        edgeWalker.setPermissionCheckerService(permissionCheckerService);
        permissionCheckerService.setPermissionCheckerRepository(context.getBean(PermissionRepository.class));
        return edgeWalker;
    }
}

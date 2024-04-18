package ru.vitasoft.statusrouteservicelibrary.services.edge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import ru.vitasoft.statusrouteservicelibrary.MySpringApp;
import ru.vitasoft.statusrouteservicelibrary.repository.enums.PermissionRepository;
import ru.vitasoft.statusrouteservicelibrary.services.permission.PermissionCheckerService;

public final class EdgeWalkerFactory {

   private EdgeWalkerFactory() {

   }

    public static EdgeWalker createEdgeWalker(PermissionCheckerService permissionCheckerService) {
        SpringApplication application = new SpringApplication(MySpringApp.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run();
        EdgeWalkerService edgeWalker = context.getBean(EdgeWalkerService.class);
        PermissionRepository permissionRepository = context.getBean(PermissionRepository.class);
        edgeWalker.setPermissionCheckerService(permissionCheckerService);
        permissionCheckerService.setPermissionCheckerRepository(permissionRepository);
        return edgeWalker;
    }
}

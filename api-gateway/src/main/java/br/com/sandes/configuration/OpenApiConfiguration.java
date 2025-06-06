package br.com.sandes.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(
            SwaggerUiConfigParameters config,
            RouteDefinitionLocator locator) {

        var definition = locator.getRouteDefinitions()
                .collectList().block();

        definition.stream().filter(
                        routeDefinition -> routeDefinition.getId()
                                .matches(".*-service"))
                                    .forEach(routeDefinition -> {
                                        String name = routeDefinition.getId();
                                        config.addGroup(name);
                                        GroupedOpenApi.builder()
                                                .pathsToMatch("/" + name + "/**")
                                                .group(name).build();
                                    }
                );

        return new ArrayList<>();
    }
}

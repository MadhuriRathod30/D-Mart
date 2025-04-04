package controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
@PropertySource("classpath:application.properties")
public class healthCheckController {

    private  final RestTemplate restTemplate;
    private final Environment environment;

    public healthCheckController(RestTemplate restTemplate, Environment environment ){
        this.environment=environment;
        this.restTemplate = restTemplate;
    }



    @GetMapping("/all")
    public Map<String , Object> CheckAllServiceHealth(){

        Map<String , Object> healthStatuses = new HashMap<>();

        Map<String, String > services = Map.of(
                "order-service", environment.getProperty("services.order-service.url")+"/actuator/health",
                "associate-service",environment.getProperty("services.associate-service.url")+"/actuator/health",
                "picking-service",environment.getProperty("services.picking-service.url")+"/actuator/health",
                "packing-service",environment.getProperty("services.packing-service.url")+"/actuator/health"
        );

        for(Map.Entry<String, String> entry:services.entrySet()){
            try{
                Map<String , Object> response = restTemplate.getForObject(entry.getValue(),Map.class);
                healthStatuses.put(entry.getKey() , response);
            }catch (Exception e){
                healthStatuses.put(entry.getKey(),Map.of("Status", "Down"));
            }
        }

        return healthStatuses;


    }



}

package pl.edu.pg.eti.category.event.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import java.util.UUID;

@Repository
public class RestEventCategoryRepository implements EventCategoryRepository {
    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    public RestEventCategoryRepository(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    public void deleteById(UUID id)
    {
        String uri = loadBalancerClient.choose("spring-app-instruments").getUri().toString();
        restTemplate.delete(uri + "/api/instruments/{id}", id);
    }

    @Override
    public void save(UUID id)
    {
        String uri = loadBalancerClient.choose("spring-app-instruments").getUri().toString();
        restTemplate.put(uri + "/api/instruments/{id}", id);
    }
}

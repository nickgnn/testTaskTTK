package my.test.task.service.impl;

import my.test.task.service.GetRocketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:application.properties")
public class GetRocketsServiceImpl implements GetRocketsService {
    private RestTemplate restTemplate;

    @Value("${rockets}")
    private String rockets;

    @Autowired
    public GetRocketsServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getAllRockets() {
        String URL = rockets;
        return restTemplate.getForObject(URL, String.class);
    }
}

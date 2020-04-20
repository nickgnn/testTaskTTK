package my.test.task.service.impl;

import my.test.task.service.GetLaunchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetLaunchesServiceImpl implements GetLaunchesService {
    private RestTemplate restTemplate;

    @Value("${launches}")
    private String launches;

    @Autowired
    public GetLaunchesServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getAllLaunches() {
        String URL = launches;
        return restTemplate.getForObject(URL, String.class);
    }
}

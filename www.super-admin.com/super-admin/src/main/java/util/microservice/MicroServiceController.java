package util.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class MicroServiceController {

    @Autowired
    protected RestTemplate restTemplate;

    @Value("${app.url}")
    protected String APP_URL;
}

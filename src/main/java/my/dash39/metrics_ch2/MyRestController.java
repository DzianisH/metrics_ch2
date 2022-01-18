package my.dash39.metrics_ch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

import static org.springframework.http.HttpHeaders.EXPIRES;
import static org.springframework.http.HttpHeaders.PRAGMA;

@RestController
public class MyRestController {
    private final TemperatureService temperatureService;
    private final MathService mathService;

    @Autowired
    public MyRestController(TemperatureService temperatureService, MathService mathService) {
        this.temperatureService = temperatureService;
        this.mathService = mathService;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<String> helloWorld() {
        return buildResponseEntity("Hello my dear world!");
    }

    @GetMapping("/factorial/{val}")
    public ResponseEntity<BigInteger> calcFactorial(@PathVariable BigInteger val) {
        return buildResponseEntity(mathService.calcFactorial(val));
    }

    @GetMapping("/temperature")
    public ResponseEntity<String> getCurrentLocalTemperature() {
        return buildResponseEntity(temperatureService.getCurrentLocalTemperature());
    }


    private <T> ResponseEntity<T> buildResponseEntity(T body) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore().mustRevalidate())
                .header(PRAGMA, "no-cache")
                .header(EXPIRES, "0")
                .body(body);
    }
}

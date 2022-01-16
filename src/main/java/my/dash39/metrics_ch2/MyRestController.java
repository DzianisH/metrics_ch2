package my.dash39.metrics_ch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

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
    public String helloWorld() {
        return "Hello my dear world!";
    }

    @GetMapping("/factorial/{val}")
    public BigInteger calcFactorial(@PathVariable BigInteger val) {
        return mathService.calcFactorial(val);
    }

    @GetMapping("/temperature")
    public String getCurrentLocalTemperature() {
        return temperatureService.getCurrentLocalTemperature();
    }
}

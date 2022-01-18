package my.dash39.metrics_ch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.regex.Pattern;

@Service
public class TemperatureService {
    private static final Pattern TEMPERATURE_PATTERN = Pattern.compile("<span class=\"wob_t q8U8x\" id=\"wob_tm\" style=\"display:inline\">([\\d]+?)</span>");
    private static final Pattern CITY_PATTERN = Pattern.compile("<div class=\"wob_loc q8U8x\" id=\"wob_loc\">(.+?)</div>");

    private static final URI TEMPERATURE_URL = URI.create("https://www.google.com/search?q=weather&hl=en");

    private final RestTemplate restTemplate;

    @Autowired
    public TemperatureService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCurrentLocalTemperature() {
        var html = restTemplate.getForObject(TEMPERATURE_URL, String.class);
        return String.format("It's %s° now in %s city.", extractTemperature(html), extractCity(html));
    }

    private String extractTemperature(String html) {
        return extractData(html, TEMPERATURE_PATTERN, "[unknown temperature]");
    }

    private String extractCity(String html) {
        return extractData(html, CITY_PATTERN, "[unknown city]");
    }

    private String extractData(String html, Pattern pattern, String fallbackMessage) {
        var matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return fallbackMessage;
    }
}

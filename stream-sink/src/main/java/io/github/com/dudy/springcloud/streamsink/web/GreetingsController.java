package io.github.com.dudy.springcloud.streamsink.web;

import io.github.com.dudy.springcloud.streamsink.model.Greetings;
import io.github.com.dudy.springcloud.streamsink.service.GreetingsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
    private final GreetingsService greetingsService;

    public GreetingsController(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    @GetMapping("/greetings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void greetings(@RequestParam("message") String message) {
        Greetings greetings = new Greetings();
        greetings.setMessage(message);
        greetings.setTimestamp(System.currentTimeMillis());

        greetingsService.sendGreeting(greetings);
    }
}

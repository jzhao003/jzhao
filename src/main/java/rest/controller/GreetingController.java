package rest.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.dao.Greeting;
import rest.dao.Shuangseqiu;
import ssq.GenerateMyNumber;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting1(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/greeting")
    public Shuangseqiu greeting() {
    	GenerateMyNumber g = new GenerateMyNumber();
        return new Shuangseqiu(g.blue(),g.red());
    }
}

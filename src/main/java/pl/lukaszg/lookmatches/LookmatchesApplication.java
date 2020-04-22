package pl.lukaszg.lookmatches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.lukaszg.lookmatches.model.Team;
import pl.lukaszg.lookmatches.model.TeamRepository;
import pl.lukaszg.lookmatches.model.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LookmatchesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LookmatchesApplication.class, args);

    }


}

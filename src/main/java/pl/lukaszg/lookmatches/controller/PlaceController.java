package pl.lukaszg.lookmatches.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lukaszg.lookmatches.model.Place;
import pl.lukaszg.lookmatches.model.User;
import pl.lukaszg.lookmatches.service.PlaceService;

import java.util.List;

@RequestMapping(value = "/api")
@RestController
@CrossOrigin
public class PlaceController {

    @Autowired
    PlaceService placeService;


    @GetMapping("/places")
    public List<Place> getPlaces() {
        return placeService.getPlaces();
    }

    @RequestMapping(value = "/addPlace",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Place basicRegister(@RequestBody Place place) {
        return placeService.addPlace(place);
    }

}

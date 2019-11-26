package pl.lukaszg.lookmatches.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lukaszg.lookmatches.model.Place;
import pl.lukaszg.lookmatches.model.PlaceRepository;

import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/places")
    public ResponseEntity getPlaces() throws JsonProcessingException {
        List<Place> places = (List<Place>) placeRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(places));
    }

    @PostMapping("/addPlace")
    public ResponseEntity savePlace(@RequestBody Place place) throws JsonProcessingException {
        placeRepository.save(place);
        return ResponseEntity.ok(objectMapper.writeValueAsString("Saved new place") + place);
    }

}

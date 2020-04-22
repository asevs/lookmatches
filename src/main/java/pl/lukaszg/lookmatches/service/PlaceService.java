package pl.lukaszg.lookmatches.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lukaszg.lookmatches.model.Place;
import pl.lukaszg.lookmatches.model.PlaceRepository;

import java.util.List;

@Service("placeService")
public class PlaceService {
    @Autowired
    PlaceRepository placeRepository;

    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    public Place addPlace(@RequestBody Place place) {
        return placeRepository.save(place);
    }
}

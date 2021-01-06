package ar.com.quasar.controllers;

import ar.com.quasar.exceptions.MessageException;
import ar.com.quasar.exceptions.NotFoundException;
import ar.com.quasar.models.Position;
import ar.com.quasar.models.Satellite;
import ar.com.quasar.models.SatelliteOnService;
import ar.com.quasar.services.UtilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    @Autowired
    UtilServiceImpl utilServiceImpl;

    private static final String TOP_SECRET = "/top_secret";

    private static final String TOP_SECRET_SPLIT_WITH_NAME = "/topsecret_split/{satelliteName}";

    private static final String TOP_SECRET_SPLIT = "/topsecret_split";

    private List<Satellite> satellites = new ArrayList<Satellite>();

    public ApiController(){
        Satellite kenobi = new Satellite("Kenobi", new Position(new double[]{-500.0, -200.0}));
        Satellite skywalker = new Satellite("Skywalker", new Position(new double[]{100, -100}));
        Satellite sato = new Satellite("Sato", new Position(new double[]{500, 100}));

        satellites.add(kenobi);
        satellites.add(skywalker);
        satellites.add(sato);
    }



    @PostMapping(path = TOP_SECRET , consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret(RequestEntity<SatelliteOnService> requestEntity) throws MessageException {
        try {
            SatelliteOnService satelliteOnService = (SatelliteOnService) requestEntity.getBody();
            return ResponseEntity.status(HttpStatus.OK).body(utilServiceImpl.getUnknownShipInformation(satelliteOnService));
        } catch(MessageException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping(path = TOP_SECRET_SPLIT_WITH_NAME , consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecretSplitWithSatelliteName(@PathVariable("satelliteName") String satelliteName,@RequestBody Satellite satellite) {
        try {
                satellites = utilServiceImpl.saveMessage(satelliteName, satellite, satellites);
                return new ResponseEntity(HttpStatus.OK);
        }catch(MessageException | NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping(path = TOP_SECRET_SPLIT , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecretSplitResponse(){
        try {
            SatelliteOnService satelliteOnService = new SatelliteOnService();
            satelliteOnService.setSatellites(satellites);
            return ResponseEntity.status(HttpStatus.OK).body(utilServiceImpl.getUnknownShipInformation(satelliteOnService));
        } catch(MessageException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}

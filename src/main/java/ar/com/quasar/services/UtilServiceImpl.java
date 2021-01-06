package ar.com.quasar.services;

import ar.com.quasar.controllers.ApiController;
import ar.com.quasar.exceptions.MessageException;
import ar.com.quasar.exceptions.NotFoundException;
import ar.com.quasar.models.UnknownShip;
import ar.com.quasar.models.Position;
import ar.com.quasar.models.Satellite;
import ar.com.quasar.models.SatelliteOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    MessageService messageService;

    @Autowired
    LocationService locationService;

    public static final double[][] SATELITE_POSITIONS = {{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};

    public static final int SATELITE_NUMBERS = 3;

    @Override
    public void deleteOffset(List<List<String>> messageList, int offSet){
        int s = 0;
        for(int i = 0; i < messageList.size(); i++){
            s = messageList.get(i).size();
            messageList.set(i, messageList.get(i).subList(s-offSet, s));
        }
    }

    @Override
    public UnknownShip getUnknownShipInformation(SatelliteOnService satelliteOnService) throws MessageException {

        if(satelliteOnService.getSatellites().size() < SATELITE_NUMBERS ){
            throw new MessageException("Se recibieron menos satelites de los esperados.");
        }

        List<List<String>> mensajes = new ArrayList<List<String>>();

        for(Satellite s : satelliteOnService.getSatellites()){
            if(s.getMessage() == null || s.getMessage().isEmpty()){
                throw new MessageException("El satelite " + s.getName() + " posee un mensaje nulo.");
            } else {
                mensajes.add(s.getMessage());
            }
        }

        for(int i = 1; i < satelliteOnService.getSatellites().size() ; i ++ ){
            if( satelliteOnService.getSatellites().get(0).getMessage().size() !=
                    satelliteOnService.getSatellites().get(i).getMessage().size()){
                throw new MessageException("No todos los satelites tienen el mismo tamaño de mensaje.");
            }
        }

        String messageResult = messageService.getMessage(mensajes);


        double[][] satellitePositions = SATELITE_POSITIONS;

        double [] distanceToSatellite = new double[satelliteOnService.getSatellites().size()];

        for(int i = 0; i < satelliteOnService.getSatellites().size(); i ++){
            distanceToSatellite[i] = satelliteOnService.getSatellites().get(i).getDistance();
        }

        double shipPosition[] = locationService.getLocation(satellitePositions, distanceToSatellite);

        Position positionUnknowShip = new Position(shipPosition);

        return new UnknownShip(positionUnknowShip, messageResult);

    }

    @Override
    public List<Satellite> saveMessage(String satelliteName, Satellite satellite, List<Satellite> satellites) throws MessageException, NotFoundException {

        if(satellite.getMessage() == null || satellite.getMessage().isEmpty()){
            throw new MessageException("El satelite " + satelliteName + " posee un mensaje nulo.");
        }

        boolean foundSatellite = false;

        for(int i = 0 ; i < satellites.size() ; i ++){
            if(satellites.get(i).getName().toUpperCase().equals(satelliteName.toUpperCase())){
                satellites.get(i).setMessage(satellite.getMessage());
                satellites.get(i).setDistance(satellite.getDistance());
                foundSatellite = true;
            }
        }

        if(!foundSatellite){
            throw new NotFoundException("No se encontró el satelite " + satelliteName);
        }

       return satellites;

    }

}

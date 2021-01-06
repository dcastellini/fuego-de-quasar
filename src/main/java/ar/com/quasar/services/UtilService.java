package ar.com.quasar.services;

import ar.com.quasar.exceptions.MessageException;
import ar.com.quasar.exceptions.NotFoundException;
import ar.com.quasar.models.Satellite;
import ar.com.quasar.models.SatelliteOnService;
import ar.com.quasar.models.UnknownShip;
import org.springframework.http.RequestEntity;


import java.util.List;


public interface UtilService {

    public void deleteOffset(List<List<String>> messageList, int offSet);

    public UnknownShip getUnknownShipInformation(SatelliteOnService satelliteOnService) throws MessageException;

    public List<Satellite> saveMessage(String satelliteName, Satellite satellite, List<Satellite> satellites) throws MessageException, NotFoundException;

}

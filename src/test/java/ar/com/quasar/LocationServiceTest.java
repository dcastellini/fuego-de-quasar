package ar.com.quasar;

import ar.com.quasar.services.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @Test
    public void getLocation() throws Exception {
        double[][] positions = new double[][]{{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
        double[] distances = new double[]{100.0, 115.5, 142.7};
        double[] expectedPosition = new double[]{-58.315252587138595, -69.55141837312165};
        double[] calculatedPosition = locationService.getLocation(positions, distances);
        for (int i = 0; i < calculatedPosition.length; i++) {
            assertEquals(expectedPosition[i], calculatedPosition[i]);
        }
    }

}

package reportGenerator;

import model.Direction;
import model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import reportGenerator.daywise.InterVehicularDistanceReportGeneratorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterVehicularDistanceReportGeneratorUtilTest {
    private InterVehicularDistanceReportGeneratorUtil util;
    private List<Vehicle> vehicles;

    @Before
    public void init(){
        util = new InterVehicularDistanceReportGeneratorUtil();
        vehicles = new ArrayList<>();
    }

    @Test
    public void shouldReturnAverageForGivenListWHenCalledGetAverage(){
        System.out.println(util.getAverage(Arrays.asList(1.0, 2.0, 2.0, 6.0)));
    }

    @Test
    public void shouldReturnTimeGapAmongVehicles(){
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5.00, 0));
        vehicles.add(new Vehicle(Direction.NORTH, 100000000, 5.00, 0));
        Assert.assertEquals(939.0,util.getTimeGapAmongVehiclesInSeconds(vehicles.get(0).getPassingTimeInMilliSeconds(), vehicles.get(1).getPassingTimeInMilliSeconds()), 0.0);
    }
}

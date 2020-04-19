package helper;

import model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import helper.InterVehicularDistanceReportGeneratorUtil;

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
}

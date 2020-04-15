package reportGenerator;

import org.junit.Before;
import org.junit.Test;
import reportGenerator.daywise.InterVehicularDistanceReportGeneratorUtil;

import java.util.Arrays;

public class InterVehicularDistanceReportGeneratorUtilTest {
    private InterVehicularDistanceReportGeneratorUtil util;

    @Before
    public void init(){
        util = new InterVehicularDistanceReportGeneratorUtil();
    }

    @Test
    public void shouldReturnAverageForGivenListWHenCalledGetAverage(){
        System.out.println(util.getAverage(Arrays.asList(1.0, 2.0, 2.0, 6.0)));
    }
}

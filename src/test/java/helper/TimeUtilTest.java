package helper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import reportGenerator.daywise.TimeUtil;

public class TimeUtilTest {
    TimeUtil timeUtil;

    @Before
    public void init() {
        timeUtil = new TimeUtil();
    }

    @Test
    public void shouldReturnHoursForGivenMilliseconds98186() {
        Assert.assertEquals(1, timeUtil.getHours(90060911));
    }

    @Test
    public void shouldReturnHours2ForGivenMilliseconds5060911() {
        Assert.assertEquals(2, timeUtil.getHours(180060911));
    }

    @Test
    public void shouldReturnHours11ForGivenMilliseconds499718() {
        Assert.assertEquals(11, timeUtil.getHours(646356956));
    }

    @Test
    public void shouldReturnMinutesForGivenMilliseconds98186() {
        Assert.assertEquals(1, timeUtil.getMinutes(90060911));
    }

    @Test
    public void shouldReturnMinutes2ForGivenMilliseconds5060911() {
        Assert.assertEquals(31, timeUtil.getMinutes(189066911));
    }

    @Test
    public void shouldReturnMinutes7ForGivenMilliseconds499718() {
        Assert.assertEquals(22, timeUtil.getMinutes(649356956));
    }
}

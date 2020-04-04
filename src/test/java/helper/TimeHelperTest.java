package helper;

import exceptions.InvalidTimeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeHelperTest {
    private TimeHelper timeHelper;

    @Before
    public void init() {
        timeHelper = new TimeHelper();
    }

    @Test
    public void shouldReturnDay1ForGivenMilliseconds98186() {
        Assert.assertEquals(1, timeHelper.calculateDay(98186));
    }

    @Test
    public void shouldReturnDay2ForGivenMilliseconds5060911() {
        Assert.assertEquals(2, timeHelper.calculateDay(5060911));
    }

    @Test
    public void shouldReturnDayForGivenMilliseconds499718() {
        Assert.assertEquals(18, timeHelper.calculateDay(64356956));
    }

    @Test
    public void shouldReturnTimeForGivenRecordA98186() throws InvalidTimeException {
        Assert.assertEquals(98186, timeHelper.getTime("A98186"));
    }

    @Test
    public void shouldReturnTimeForGivenRecordA5060911() throws InvalidTimeException {
        Assert.assertEquals(5060911, timeHelper.getTime("A5060911"));
    }

    @Test(expected = InvalidTimeException.class)
    public void shouldReturnInvalidTimeExceptionForGivenRecord() throws InvalidTimeException {
        timeHelper.getTime("A50.60911");
    }
}

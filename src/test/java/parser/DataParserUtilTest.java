package parser;

import exceptions.InvalidTimeException;
import helper.DataParserUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataParserUtilTest {
    private DataParserUtil dataParserUtil;

    @Before
    public void init(){
        dataParserUtil = new DataParserUtil();
    }

    @Test
    public void shouldReturnSpeedForGivenMilliSeconds(){
        Assert.assertEquals((0.03), (dataParserUtil.calculateSpeed(98186)), 0.0);
    }

    @Test
    public void shouldReturnSpeedForGivenMilliSeconds19401611(){
        Assert.assertEquals((0.0), (dataParserUtil.calculateSpeed(19401611)), 0.00);
    }

    @Test
    public void shouldReturnTimeForGivenRecordA98186() throws InvalidTimeException {
        Assert.assertEquals(98186, dataParserUtil.getExtractedTime("A98186"));
    }

    @Test
    public void shouldReturnTimeForGivenRecordA5060911() throws InvalidTimeException {
        Assert.assertEquals(5060911, dataParserUtil.getExtractedTime("A5060911"));
    }

    @Test(expected = InvalidTimeException.class)
    public void shouldReturnInvalidTimeExceptionForGivenRecord() throws InvalidTimeException {
        dataParserUtil.getExtractedTime("A50.60911");
    }
}

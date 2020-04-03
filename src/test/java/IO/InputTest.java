package IO;

import exceptions.NoSuchFileFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class InputTest {
    private Input input;

    @Before
    public void init() {
        input = new Input();
    }

    @Test
    public void shouldReturnListOfRecordsWhenCalledLoad() throws IOException, NoSuchFileFoundException {
        List<String> records = input.loadData();
        Assert.assertFalse(records.isEmpty());
    }
}

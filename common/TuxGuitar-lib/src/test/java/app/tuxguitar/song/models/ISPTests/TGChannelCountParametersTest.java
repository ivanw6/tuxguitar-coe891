package app.tuxguitar.song.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TGChannelCountParametersTest {

    private static class TestTGChannel extends TGChannel { }

    private static class TestTGChannelParameter extends TGChannelParameter { }

    private TestTGChannelParameter makeParameter(String key, String value) {
        TestTGChannelParameter param = new TestTGChannelParameter();
        param.setKey(key);
        param.setValue(value);
        return param;
    }

    @Test
    public void testEmptyListReturnsZero() {
        TestTGChannel channel = new TestTGChannel();

        assertEquals(0, channel.countParameters());
    }

    @Test
    public void testOneParameterReturnsOne() {
        TestTGChannel channel = new TestTGChannel();
        channel.addParameter(makeParameter("k1", "v1"));

        assertEquals(1, channel.countParameters());
    }

    @Test
    public void testMultipleParametersReturnsCorrectCount() {
        TestTGChannel channel = new TestTGChannel();
        channel.addParameter(makeParameter("k1", "v1"));
        channel.addParameter(makeParameter("k2", "v2"));
        channel.addParameter(makeParameter("k3", "v3"));

        assertEquals(3, channel.countParameters());
    }
}

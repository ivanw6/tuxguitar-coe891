package app.tuxguitar.song.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TGChannelGetParameterTest {

    private static class TestTGChannel extends TGChannel { }

    private static class TestTGChannelParameter extends TGChannelParameter { }

    private TestTGChannelParameter makeParameter(String key, String value) {
        TestTGChannelParameter param = new TestTGChannelParameter();
        param.setKey(key);
        param.setValue(value);
        return param;
    }

    @Test
    public void testNegativeIndexReturnsNull() {
        TestTGChannel channel = new TestTGChannel();
        channel.addParameter(makeParameter("k1", "v1"));

        assertNull(channel.getParameter(-1));
    }

    @Test
    public void testValidIndexReturnsParameter() {
        TestTGChannel channel = new TestTGChannel();
        channel.addParameter(makeParameter("k1", "v1"));
        channel.addParameter(makeParameter("k2", "v2"));

        assertEquals("k2", channel.getParameter(1).getKey());
        assertEquals("v2", channel.getParameter(1).getValue());
    }

    @Test
    public void testTooLargeIndexReturnsNull() {
        TestTGChannel channel = new TestTGChannel();
        channel.addParameter(makeParameter("k1", "v1"));

        assertNull(channel.getParameter(5));
    }
}

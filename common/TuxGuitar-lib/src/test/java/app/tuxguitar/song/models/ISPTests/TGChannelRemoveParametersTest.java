package app.tuxguitar.song.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TGChannelRemoveParametersTest {

    private static class TestTGChannel extends TGChannel { }

    private static class TestTGChannelParameter extends TGChannelParameter { }

    private TestTGChannelParameter makeParameter(String key, String value) {
        TestTGChannelParameter param = new TestTGChannelParameter();
        param.setKey(key);
        param.setValue(value);
        return param;
    }

    @Test
    public void testRemoveFromEmptyListKeepsItEmpty() {
        TestTGChannel channel = new TestTGChannel();

        channel.removeParameters();

        assertEquals(0, channel.countParameters());
    }

    @Test
    public void testRemoveFromOneElementListMakesItEmpty() {
        TestTGChannel channel = new TestTGChannel();
        channel.addParameter(makeParameter("k1", "v1"));

        channel.removeParameters();

        assertEquals(0, channel.countParameters());
        assertNull(channel.getParameter(0));
    }

    @Test
    public void testRemoveFromMultipleElementListMakesItEmpty() {
        TestTGChannel channel = new TestTGChannel();
        channel.addParameter(makeParameter("k1", "v1"));
        channel.addParameter(makeParameter("k2", "v2"));
        channel.addParameter(makeParameter("k3", "v3"));

        channel.removeParameters();

        assertEquals(0, channel.countParameters());
        assertNull(channel.getParameter(0));
    }
}

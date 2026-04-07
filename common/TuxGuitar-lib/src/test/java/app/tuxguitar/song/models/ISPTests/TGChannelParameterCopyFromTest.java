package app.tuxguitar.song.models;

import static org.junit.Assert.*;
import org.junit.Test;

public class TGChannelParameterCopyFromTest {

    private static class TestTGChannelParameter extends TGChannelParameter { }

    @Test
    public void testCopyFromCopiesKeyAndValue() {
        TestTGChannelParameter source = new TestTGChannelParameter();
        source.setKey("volume");
        source.setValue("127");

        TestTGChannelParameter target = new TestTGChannelParameter();
        target.copyFrom(source);

        assertEquals("volume", target.getKey());
        assertEquals("127", target.getValue());
    }

    @Test
    public void testCopyFromOverwritesExistingValues() {
        TestTGChannelParameter source = new TestTGChannelParameter();
        source.setKey("balance");
        source.setValue("64");

        TestTGChannelParameter target = new TestTGChannelParameter();
        target.setKey("oldKey");
        target.setValue("oldValue");

        target.copyFrom(source);

        assertEquals("balance", target.getKey());
        assertEquals("64", target.getValue());
    }

    @Test
    public void testCopyFromCopiesNullValues() {
        TestTGChannelParameter source = new TestTGChannelParameter();
        source.setKey(null);
        source.setValue(null);

        TestTGChannelParameter target = new TestTGChannelParameter();
        target.setKey("something");
        target.setValue("something");

        target.copyFrom(source);

        assertNull(target.getKey());
        assertNull(target.getValue());
    }
}

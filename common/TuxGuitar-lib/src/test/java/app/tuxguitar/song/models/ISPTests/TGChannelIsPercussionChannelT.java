package app.tuxguitar.song.models;

import static org.junit.Assert.*;
import org.junit.Test;

public class TGChannelIsPercussionChannelTest {

    private static class TestTGChannel extends TGChannel { }

    @Test
    public void testPercussionBankReturnsTrue() {
        TestTGChannel channel = new TestTGChannel();
        channel.setBank(TGChannel.DEFAULT_PERCUSSION_BANK);

        assertTrue(channel.isPercussionChannel());
    }

    @Test
    public void testDefaultBankReturnsFalse() {
        TestTGChannel channel = new TestTGChannel();
        channel.setBank(TGChannel.DEFAULT_BANK);

        assertFalse(channel.isPercussionChannel());
    }

    @Test
    public void testOtherBankReturnsFalse() {
        TestTGChannel channel = new TestTGChannel();
        channel.setBank((short) 5);

        assertFalse(channel.isPercussionChannel());
    }
}

package app.tuxguitar.song.models.LogicBasedTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.factory.TGFactory;
import app.tuxguitar.song.models.TGNote;
import app.tuxguitar.song.models.TGVoice;

public class TGVoiceSetEmptyTest {

    @Test
    public void testSetEmpty_True() {
        TGFactory factory = new TGFactory();
        TGVoice voice = factory.newVoice(0);
        TGNote note = factory.newNote();
        voice.addNote(note);
        assertEquals(1, voice.countNotes());
        voice.setEmpty(true);
        assertEquals(0, voice.countNotes());
        assertTrue(voice.isEmpty());
    }

    @Test
    public void testSetEmpty_False() {
        TGFactory factory = new TGFactory();
        TGVoice voice = factory.newVoice(0);
        TGNote note = factory.newNote();
        voice.addNote(note);
        voice.setEmpty(false);
        assertEquals(1, voice.countNotes());
        assertFalse(voice.isEmpty());
    }
}

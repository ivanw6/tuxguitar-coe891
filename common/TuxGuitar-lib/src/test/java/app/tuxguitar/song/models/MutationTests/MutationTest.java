package app.tuxguitar.song.models.MutationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.tuxguitar.song.factory.TGFactory;
import app.tuxguitar.song.models.TGBeat;
import app.tuxguitar.song.models.TGColor;
import app.tuxguitar.song.models.TGDuration;
import app.tuxguitar.song.models.TGNote;
import app.tuxguitar.song.models.TGString;
import app.tuxguitar.song.models.TGStroke;
import app.tuxguitar.song.models.TGTimeSignature;
import app.tuxguitar.song.models.TGVoice;

import static org.junit.jupiter.api.Assertions.*;

public class MutationTest {
    private TGFactory factory;
    private TGDuration duration;
    private TGNote note;
    private TGString string;
    private TGTimeSignature timeSig;
    private TGVoice voice;
    private TGColor color;
    private TGStroke stroke;

    @BeforeEach
    void setUp() {
        factory = new TGFactory();
        duration = factory.newDuration();
        note = factory.newNote();
        string = factory.newString();
        timeSig = factory.newTimeSignature();
        voice = factory.newVoice(0);
        color = factory.newColor();
        stroke = factory.newStroke();
    }

    // TGDuration.isDotted() Tests
    @Test
    void isDotted_returnsTrue_whenSet() {
        duration.setDotted(true);
        assertTrue(duration.isDotted());
    }

    @Test
    void isDotted_returnsFalse_byDefault() {
        assertFalse(duration.isDotted());
    }

    // TGDuration.isDoubleDotted() Tests
    @Test
    void isDoubleDotted_returnsTrue_whenSet() {
        duration.setDoubleDotted(true);
        assertTrue(duration.isDoubleDotted());
    }

    @Test
    void isDoubleDotted_returnsFalse_byDefault() {
        assertFalse(duration.isDoubleDotted());
    }

    @Test
    void isDoubleDotted_independentFromDotted() {
        duration.setDotted(true);
        assertFalse(duration.isDoubleDotted());
    }

    // TGDuration.setDotted(boolean) Tests
    @Test
    void setDotted_storesTrue() {
        duration.setDotted(true);
        assertTrue(duration.isDotted());
    }

    @Test
    void setDotted_storesFalse_afterTrue() {
        duration.setDotted(true);
        duration.setDotted(false);
        assertFalse(duration.isDotted());
    }

    // TGNote.setValue(int) Tests
    @Test
    void setValue_storesExactValue() {
        note.setValue(12);
        assertEquals(12, note.getValue());
    }

    @Test
    void setValue_storesZero_lowerBoundary() {
        note.setValue(0);
        assertEquals(0, note.getValue());
    }

    @Test
    void setValue_storesTwentyFour_upperBoundary() {
        note.setValue(24);
        assertEquals(24, note.getValue());
    }

    // TGString.getValue() Tests
    @Test
    void getString_returnsLowE_midi40() {
        string.setValue(40);
        assertEquals(40, string.getValue());
    }

    @Test
    void getString_returnsHighE_midi64() {
        string.setValue(64);
        assertEquals(64, string.getValue());
    }

    // TGTimeSignature.getNumerator() Tests
    @Test
    void getNumerator_returnsFour_forCommonTime() {
        timeSig.setNumerator(4);
        assertEquals(4, timeSig.getNumerator());
    }

    @Test
    void getNumerator_returnsThree_forWaltz() {
        timeSig.setNumerator(3);
        assertEquals(3, timeSig.getNumerator());
    }

    // 7. TGVoice.getNote(int) Tests
    @Test
    void getNote_returnsFirstNote_atIndexZero() {
        note.setValue(5);
        voice.addNote(note);
        assertSame(note, voice.getNote(0));
    }

    @Test
    void getNote_returnsSecondNote_atIndexOne() {
        TGNote first = factory.newNote();
        TGNote second = factory.newNote();
        first.setValue(3);
        second.setValue(7);
        voice.addNote(first);
        voice.addNote(second);
        assertSame(second, voice.getNote(1));
    }

    // TGVoice.getHighestFret() Tests
    @Test
    void getHighestFret_returnsMaxFret() {
        TGNote first = factory.newNote();
        TGNote second = factory.newNote();
        TGNote third = factory.newNote();
        first.setValue(5);
        second.setValue(15);
        third.setValue(9);
        voice.addNote(first);
        voice.addNote(second);
        voice.addNote(third);
        assertEquals(15, voice.getHighestFret());
    }

    @Test
    void getHighestFret_returnsNegativeOne_whenNoNotes() {
        assertEquals(-1, voice.getHighestFret());
    }

    @Test
    void getHighestFret_returnsSingleNoteFret() {
        TGNote first = factory.newNote();
        first.setValue(7);
        voice.addNote(first);
        assertEquals(7, voice.getHighestFret());
    }

    // TGColor.isEqual(TGColor) Tests
    @Test
    void isEqual_returnsFalse_forNullArgument() {
        color.setR(100);
        color.setG(150);
        color.setB(200);
        TGColor color2 = factory.newColor();
        color2.setR(101);
        color2.setG(151);
        color2.setB(201);
        assertFalse(color.isEqual(color2));
    }

    @Test
    void isEqual_returnsTrue_forIdenticalRGB() {
        color.setR(10);
        color.setG(20);
        color.setB(30);
        TGColor same = factory.newColor();
        same.setR(10);
        same.setG(20);
        same.setB(30);
        assertTrue(color.isEqual(same));
    }

    @Test
    void isEqual_returnsFalse_whenOnlyRDiffers() {
        color.setR(10);
        color.setG(20);
        color.setB(30);
        TGColor other = factory.newColor();
        other.setR(99);
        other.setG(20);
        other.setB(30);
        assertFalse(color.isEqual(other));
    }

    // TGStroke.getIncrementTime(TGBeat) Tests
    @Test
    void getIncrementTime_returnsZero_whenStrokeValueIsZero() {
        stroke.setValue(0);
        TGBeat beat = factory.newBeat();
        assertEquals(0, stroke.getIncrementTime(beat));
    }
 
    @Test
    void getIncrementTime_returnsZero_whenAllVoicesEmpty() {
        stroke.setValue(TGDuration.QUARTER);
        TGBeat beat = factory.newBeat();
        assertEquals(0, stroke.getIncrementTime(beat));
    }
}
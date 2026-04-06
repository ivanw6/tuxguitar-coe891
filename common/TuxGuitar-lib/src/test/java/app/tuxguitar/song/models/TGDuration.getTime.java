import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.factory.TGFactory;
import app.tuxguitar.song.models.TGDuration;

public class TGDurationGetTimeTest {

    @Test
    public void testGetTime_Dotted() {
        TGFactory factory = new TGFactory();
        TGDuration d = factory.newDuration();
        d.setValue(TGDuration.QUARTER);
        d.setDotted(true);
        d.setDoubleDotted(false);
        assertEquals(1440L, d.getTime());
    }

    @Test
    public void testGetTime_DoubleDotted() {
        TGFactory factory = new TGFactory();
        TGDuration d = factory.newDuration();
        d.setValue(TGDuration.QUARTER);
        d.setDotted(false);
        d.setDoubleDotted(true);
        assertEquals(1680L, d.getTime());
    }

    @Test
    public void testGetTime_Normal() {
        TGFactory factory = new TGFactory();
        TGDuration d = factory.newDuration();
        d.setValue(TGDuration.QUARTER);
        d.setDotted(false);
        d.setDoubleDotted(false);
        assertEquals(960L, d.getTime());
    }
}

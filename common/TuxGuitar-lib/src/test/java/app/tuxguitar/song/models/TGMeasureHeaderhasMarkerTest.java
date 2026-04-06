import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.factory.TGFactory;
import app.tuxguitar.song.models.TGMarker;
import app.tuxguitar.song.models.TGMeasureHeader;

public class TGMeasureHeaderHasMarkerTest {

    @Test
    public void testHasMarker_True() {
        TGFactory factory = new TGFactory();
        TGMeasureHeader header = factory.newHeader();
        TGMarker marker = factory.newMarker();
        header.setMarker(marker);
        assertTrue(header.hasMarker());
    }

    @Test
    public void testHasMarker_False() {
        TGFactory factory = new TGFactory();
        TGMeasureHeader header = factory.newHeader();
        assertFalse(header.hasMarker());
    }
}

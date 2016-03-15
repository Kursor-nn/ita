package CommunicationMessage;

import com.kozachuk.ita.CommunicationMessage.Respond;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.*;

/**
 * Created by alexanderkozachuk on 15.03.16.
 */
public class RespondTest {
    Respond respond = null;

    @Before
    public void before(){
        respond = new Respond();
    }

    @After
    public void after(){
        respond = null;
    }

    @Test
    public void testRespondDefaultValues(){
        assertNull(respond.getMessage());
        assertNull(respond.getContent());
    }

    @Test
    public void testRespondContent(){
        String testContentText = "testContentText";

        respond.setContent(testContentText);
        assertEquals(testContentText, respond.getContent());
    }

    @Test
    public void testRespondMessage(){
        String testMessageText = "testMessageText";

        respond.setMessage(testMessageText);
        assertEquals(testMessageText, respond.getMessage());
    }

}

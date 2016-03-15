package CommunicationMessage;

import com.kozachuk.ita.CommunicationMessage.Request;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.StateType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by alexanderkozachuk on 15.03.16.
 */
public class RequestTest {
    Request request = null;

    @Before
    public void before(){
        request = new Request();
    }

    @After
    public void after(){
        request = null;
    }

    @Test
    public void testRequestStateTypeSetting(){
        StateType type = StateType.TWO;
        request.setStateType(type);

        assertEquals(type, request.getStateType());
    }

    @Test
    public void testRequestUserInputSetting(){
        String userInput = new String("user input");
        request.setUserInput(userInput);

        assertEquals(userInput, request.getUserInput());
    }
}

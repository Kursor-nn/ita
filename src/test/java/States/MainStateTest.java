package States;

import com.kozachuk.ita.Application.Application;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.LivinigStates.CatalogState;
import com.kozachuk.ita.States.MainState;
import com.kozachuk.ita.States.StateType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexanderkozachuk on 15.03.16.
 */
public class MainStateTest {

    MainState app = null;

    @Before
    public void before(){
        app = new MainState();
    }

    @After
    public void after(){
        app = null;
    }

    @Test
    public void MainStateMessageDefaultTest(){
        Respond respond = app.handle();
        assertNotNull(respond.getMessage());
    }

    @Test
    public void MainStateContentDefaultTest(){
        Respond respond = app.handle();
        assertNull(respond.getContent());
    }

    @Test
    public void MainStateStatesTest(){
        ApplicationState oneState = app.next(StateType.ONE);
        assertNotNull(oneState);

        oneState = app.next(StateType.TWO);
        assertNotNull(oneState);

        oneState = app.next(StateType.THREE);
        assertNotNull(oneState);

        oneState = app.next(StateType.ASTERISK);
        assertNotNull(oneState);

        oneState = app.next(StateType.LATTICE);
        assertNotNull(oneState);
    }

    @Test
    public void MainStateNullStateTest() {
        ApplicationState unknownStateType = app.next(null);
        assertNotNull(unknownStateType);
        assertSame(app, unknownStateType);
    }

    @Test
    public void MainStateUnknownStateTest() {
        ApplicationState unknownStateType = app.next(StateType.LATTICE);

        assertNotNull(unknownStateType);
        assertSame(app, unknownStateType);
    }
}

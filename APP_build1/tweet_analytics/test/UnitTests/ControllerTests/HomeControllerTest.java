/**
 
 * @author Simarpreet Singh
 * This class tests the Home Controller Controller object
 
 */
package UnitTests.ControllerTests;

import akka.actor.ActorSystem;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import controllers.HomeController;
import org.junit.Test;
import play.mvc.Result;
import java.util.concurrent.CompletionStage;

/**
 * @author Prasanth Vaishnavi Vigneswar Anil
 */
public class HomeControllerTest {
	/**
	 * Tests the functionality of HomeController method asynchronously
	 * Asserts that the home page has "Start Your Search"
	 * Asserts the status of home page
	 */
    @Test
    public void testHomeControllerAsync() {
        final HomeController controller = new HomeController();
        final CompletionStage<Result> future = controller.index();
        Result homeResult = future.toCompletableFuture().join();
        assertThat(contentAsString(homeResult).contains("Start Your Search"), is(true));
        assertThat(OK, is(homeResult.status()));
    }
}

package UnitTests.ServiceTests;

import static org.junit.Assert.*;
import org.junit.Test;
import services.Utils;
import static org.hamcrest.CoreMatchers.*;

/**
 * Utils test Class.
 *
 *
 * @author Prasanth Vaishnavi Vigneswar Anil
 */

public class UtilsTest {

	/**
	 * Tests if the actual tweet text is as the expected tweet text
	 */
	
	@Test
	public void testReplaceUrlWithHtmlTags() {
		Utils utils = new Utils();
		String actual=utils.replaceUrlWithHtmlTags("abc http://abc.com xyz");
		assertThat(actual,is("abc <a href=\"http://abc.com\" target=\"_blank\" >http://abc.com</a> xyz"));
		assertThat("abc",is("abc"));
		assertThat("",is(""));
	}
}

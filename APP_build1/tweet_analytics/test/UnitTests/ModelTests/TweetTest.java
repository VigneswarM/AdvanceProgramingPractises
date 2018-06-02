/**
 * @author Simarpreet Singh
 */
package UnitTests.ModelTests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import Models.Tweet;

/**
 * @author Prasanth Vaishnavi Vigneswar Anil
 */
public class TweetTest {
		/**
		 * Tests the Tweet Model class with some valid inputs
		 */
		@Test
		public void testTweetsWithValidInputString() {
			Tweet tweet=new Tweet("Adam H","Addie_H","abc",100,120,"11:12 AM","https://play-God.jpg");
			assertEquals("Adam H",tweet.getUserName());
			assertEquals("Addie_H",tweet.getScreenName());
			assertEquals("abc",tweet.getTweetText());
			assertEquals(100,tweet.getRetweetCount());
			assertEquals(120,tweet.getFavoriteCount());
			assertEquals("https://play-God.jpg",tweet.getUserImageUrl());
			assertEquals("Adam H :: Addie_H :: abc :: 100 :: 120 :: 11:12 AM",tweet.toString());
		}

}

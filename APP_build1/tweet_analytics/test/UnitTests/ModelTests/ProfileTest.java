/**
 * @author Simarpreet Singh
 * @version 1.0
 */
package UnitTests.ModelTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import Models.Profile;

/**
 * @author Prasanth Vaishnavi Vigneswar Anil
 */
public class ProfileTest {
    /**
     * Tests the Profile Model class with some valid inputs
     */
    @Test
    public void testProfileWithValidInputString() {
        Profile profile = new Profile("Adam H", "Addie_H", 100, 120, "USA", 123, "https://boss.jpg");
        assertEquals("Adam H", profile.getUserName());
        assertEquals("Addie_H", profile.getScreenName());
        assertEquals(100, profile.getFollowersCount());
        assertEquals(120, profile.getFriendsCount());
        assertEquals("USA", profile.getLocation());
        assertEquals("Adam H :: Addie_H :: 100 :: 120 :: USA :: https://boss_400x400.jpg", profile.toString());
    }

    /**
     * Tests the Profile class with empty inputs
     */
    @Test
    public void testProfileWithEmptyString() {
        Profile profile = new Profile();
        assertEquals("", profile.getUserName());
        assertEquals("", profile.getScreenName());
        assertEquals(0, profile.getFollowersCount());
        assertEquals(0, profile.getFriendsCount());
        assertEquals("", profile.getLocation());
    }
}

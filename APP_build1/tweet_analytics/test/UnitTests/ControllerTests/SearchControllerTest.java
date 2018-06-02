package UnitTests.ControllerTests;
/**
 * @author Anil H R
 * This class tests the Search Controller object
 */
import Models.Profile;
import Models.Tweet;
import controllers.HomeController;
import controllers.SearchController;
import org.apache.xpath.SourceTree;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import play.mvc.Result;
import services.TwitterApi;
import twitter4j.ResponseList;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

public class SearchControllerTest {
	/**
	 * Tests the functionality of SearchTweets method asynchronously
	 * Asserts that the search page returns status OK for the key searched
	 * Asserts the search result contains the search key
	 */
    @Test
    public void testSearchTweetsAsync() {
        final SearchController searchController = new SearchController();
        searchController.SetTwitterApi(getMock());
        final CompletionStage<Result> future = searchController.search("Virat");
        Result searchResults = future.toCompletableFuture().join();
        assertThat(OK, is(searchResults.status()));
        assertThat(contentAsString(searchResults).contains("name"), is(true));
    }

    /**
	 * Tests the functionality of SearchUserTweets method asynchronously
	 * Asserts that the search page returns status OK for the particular user searched
	 */
    @Test
    public void testSearchUserTweetsAsync() {
        final SearchController searchController = new SearchController();
        searchController.SetTwitterApi(getMock());
        final CompletionStage<Result> future = searchController.getUser("Virat");
        Result searchResults = future.toCompletableFuture().join();
        assertThat(OK, is(searchResults.status()));
    }

    /**
     * Method to create TwitterApi object
	 * @return the twitterApi object(mocked) having ArrayList<Tweet>
	 */
    private TwitterApi getMock() {
        TwitterApi twitterApi = Mockito.mock(TwitterApi.class);
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet("name", "sname", "This is the tweet text", 10, 10, "now", "app.org"));
        tweets.add(new Tweet("name", "sname", "This is the tweet text", 10, 10, "now", "app.org"));
        when(twitterApi.getTweets(any())).thenReturn(tweets);
        when(twitterApi.getUserTweets(any())).thenReturn(tweets);
        when(twitterApi.getProfile(any())).thenReturn(new Profile("Adam H", "Addie_H", 100, 120, "USA", 123, "https://boss.jpg"));
        return twitterApi;
    }
}

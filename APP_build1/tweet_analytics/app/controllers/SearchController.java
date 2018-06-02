/**
 * @author Simarpreet Singh
 */
package controllers;

import Models.Profile;
import Models.Tweet;
import play.mvc.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.twirl.api.Html;
import twitter4j.Twitter;
import views.html.*;
import views.html.home;
import views.html.tweets;
import views.html.user;
import services.TwitterApi;

import javax.swing.text.html.HTML;

/**
 *
 * @author Prasanth, Vaishnavi, Anil, Simarpreet
 *
 * This controller contains  action to handle HTTP requests for
 * *** searching latest tweets
 * *** fetching the latest tweets by the user
 * *** Fetching the user information
 */
public class SearchController extends Controller {

    private TwitterApi TwitterApi;

    /**
     * Constructor for the Search Controller.
     * Creates an instance for the Twitter API Wrapper which will
     * be used to call the actual TWitter API for serving the
     * HTTP requests
     */
    public SearchController() {
        this.TwitterApi = new TwitterApi();
    }

    /**
     * Sets the twitter API
     * @param twitterApi
     */
    public void SetTwitterApi(TwitterApi twitterApi) {
        TwitterApi = twitterApi;
    }

    /**
     * Gets the Twitter API
     * @return a Twitter API object {{@link TwitterApi}}
     */
    private TwitterApi GetTwitterApi() {
        return  this.TwitterApi;
    }

    /**
     * Calls the Twitter API service to fetch the top 10 tweets based on the given search keyword Asychronously
     * and returns a CompletionStage with Html response embedded in it.
     *
     * @param keyword The search text
     *
     * @return a response of type CompletionStage<Result> (Html response) with the Top 10 Tweets
     * based on the search keyword asynchronously
     */
    public CompletionStage<Result> search(String keyword) {
        return GetLatestTweets(keyword).thenApplyAsync(Results::ok);
    }

    /**
     * Calls the Twitter API service to fetch the top 10 tweets for the given username Asychronously
     * and returns a CompletionStage with Html response embedded in it.
     *
     * @param username The name of the user
     *
     * @return a response of type CompletionStage<Result> (Html response) with the Top 10 Tweets
     * by the given user.
     */
    public CompletionStage<Result> getUser(String username) {
        return GetUserInfo(username).thenApplyAsync(Results::ok);
    }

    /**
     * Gets the profile information and Latest tweets of the user asynchronously and returns a {{@link CompletionStage<Html>}}
     * with the html response embedded in it
     *
     * @param username The name of the user
     *
     * @return a {{CompletionStage<Html>}} with Tweets and Profile Information
     */
    public CompletionStage<Html> GetUserInfo(String username) {
        return GetUserTweets(username).thenCombine(GetProfile(username),
                (tweets, profile) -> user.render(tweets, profile)
                );
        
    }

    /**
     * Gets the Latest tweets (Last 10) for the given search keyword and returns it as
     * a {{CompletionStage<Html>}} object
     *
     * @param keyword The search text
     *
     * @return a {@link CompletionStage} object with the top 10 tweets corresponding to the search keyword.
    */
    public CompletionStage<Html> GetLatestTweets(String keyword) {
        return CompletableFuture.supplyAsync(() -> {
            TwitterApi twitterApi = this.GetTwitterApi();
            ArrayList<Tweet> tweetList = twitterApi.getTweets(keyword);
            return tweets.render(tweetList);
        });
    }

    /**
     * Gets the profile information of the user asynchronously and returns a {@link CompletionStage} object
     *
     * @param  userName Name of the user
     *
     * @return Gets the profile information of the user asynchronously and returns a {@link CompletionStage} object
     */
    public CompletionStage<Profile> GetProfile(String userName) {
        return CompletableFuture.supplyAsync(() -> {
            TwitterApi twitterApi = this.GetTwitterApi();
            return twitterApi.getProfile(userName);
        });
    }

    /**
     * Gets the top ten tweets of the user asynchronously and returns a {@link CompletionStage} object
     *
     * @param  username The userName of the user
     *
     * @return a CompletionStage Object with the Top 10 tweets of the user in a list
     */
    public CompletionStage<ArrayList<Tweet>> GetUserTweets(String username) {
        return CompletableFuture.supplyAsync(() -> {
            TwitterApi twitterApi = this.GetTwitterApi();
            return twitterApi.getUserTweets(username);
        });
    }
}
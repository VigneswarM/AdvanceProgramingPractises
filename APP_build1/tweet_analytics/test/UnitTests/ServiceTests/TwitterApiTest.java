package UnitTests.ServiceTests;

import org.junit.Assert;
import services.AccountInformation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import services.ConfBuilder;
import services.TwitterApi;
import org.mockito.Mockito;
import Models.Profile;
import Models.Tweet;
import org.junit.Test;

/**
 * TwitterAPI test Class.
 *
 * <P>Tests the various behaviour of the class TwitterAPI.
 *
 * @author Prasanth Vaishnavi Vigneswar Anil
 */
public class TwitterApiTest {

	/**
	 *  Tests the GetUserTweets functionality by passing a twitter instance(mocked)
	 *  Asserts whether size of the list getUserTweets returns satisfies test size
	 */
	 @Test
	 public void testGetUserTweets() throws TwitterException {
		 Twitter twitterInstance = createUserTweetMock("Prasanth","Prasanth","Prasanth","Prasanth",10,15);
		 TwitterApi api=new TwitterApi();
		 api.SetTwitterInstance(twitterInstance);
		 assertThat(0,is(api.getUserTweets("").size()));
	 }

	 /**
	 *  Tests the GetUserTweets functionality by passing a Null twitter instance
	 *  Asserts whether getUserTweets returns empty list 
	 */ 

    @Test
    public void testGetUserTweetsWithNullInstance() throws TwitterException {
        TwitterApi api=new TwitterApi();
        api.SetTwitterInstance(null);
        assertThat(0,is(api.getUserTweets("").size()));
    }

    /**
   	 *  Tests the getTweets functionality by passing a retweetflag as true and twitterInstance(mocked)
   	 *  Asserts whether {@link isRetweet} affects the tweets returned 
   	 */ 

	 @Test
	 public void testGetTweetsWithRetweet() throws TwitterException {
        Twitter twitterInstance = createTweetMock("Prasanth","Prasanth","Prasanth","Prasanth",10,15,true);
        TwitterApi api = new TwitterApi();
        api.SetTwitterInstance(twitterInstance);
        assertThat(2, is(api.getTweets("").size()));
	 }
	 
	 /**
	  *  Tests the getTweets functionality by passing a retweetflag as false and twitterInstance(mocked)
	  *  Asserts whether {@link isRetweet} affects the tweets returned 
	  */ 
	 
	 @Test
	 public void testGetTweetsWithoutRetweet() throws TwitterException {
        Twitter twitterInstance = createTweetMock("Prasanth","Prasanth","Prasanth","Prasanth",10,15,false);
        TwitterApi api = new TwitterApi();
        api.SetTwitterInstance(twitterInstance);
        assertThat(2, is(api.getTweets("").size()));
	 }
	 
	 /**
	  *  Tests the getTweets functionality by passing a Null twitter instance
	  *  Asserts that number of tweets returned is not null
	  */ 
	 
	 @Test
	 public void testGetTweetsWithNullInstance() throws TwitterException {
		TwitterApi api = new TwitterApi();
        api.SetTwitterInstance(null);
        assertThat(0, is(api.getTweets("").size()));
	 }
	 
	 /**
	  *  Tests the AccountInfo functionality
	  *  Asserts that object of type account information is returned
	  */ 
	 @Test
	 public void testAccountInfo() {
	     AccountInformation ac = new AccountInformation();
	     Assert.assertNotNull(ac);
    }
	 
	 /**
	  *  Returns the twitterInstances(mocked) having the ResponseList<Status> object 
	  *  @param Name UserName of the user
	  *  @param screenName Screen Name of the user
	  *  @param imgURL URL of image of the user
	  *  @param text Text of the tweet
	  *  @param retweets retweet count of the tweet
	  *  @param favourites number of times tweet is marked as favourite
	  *  @param isRetweet states whether tweet is retweeted
	  *  @param getStatusCount Status Id of the user
	  *  @return instance of the twitter object {@link twitterInstance} containing User details
	  */ 
	 private Twitter createUserTweetMock(String userName,String screenName,String imgURL,String text,int retweets,int favorites) throws TwitterException {
		 ResponseList<Status> tweetsInput= Mockito.mock(ResponseList.class);
         Status s = Mockito.mock(Status.class);
         Date date = Mockito.mock(Date.class);
         
         User user = Mockito.mock(User.class);
         when(user.getName()).thenReturn(userName);
         when(user.getScreenName()).thenReturn(screenName);
         when(user.getBiggerProfileImageURL()).thenReturn(imgURL);
         
         
         
         when(s.getUser()).thenReturn(user);
         when(s.getText()).thenReturn(text);
         when(s.getRetweetCount()).thenReturn(retweets);
         when(s.getFavoriteCount()).thenReturn(favorites);
         when(s.getCreatedAt()).thenReturn(date);
         
         
         Twitter twitterInstance = Mockito.mock(Twitter.class);
         when(twitterInstance.getUserTimeline("")).thenReturn(tweetsInput);
         return twitterInstance;
	 }
	 
	 /**
	  *  Returns the twitterInstances(mocked) having the List<Status> object 
	  *  @param Name UserName of the user
	  *  @param screenName Screen Name of the user
	  *  @param imgURL URL of image of the user
	  *  @param text Text of the tweet
	  *  @param retweets retweet count of the tweet
	  *  @param favourites number of times tweet is marked as favourite
	  *  @param isRetweet states whether tweet is retweeted
	  *  @param getStatusCount Status Id of the user
	  *  @return instance of the twitter object {@link twitterInstance} containing User details
	  */ 

    private Twitter createTweetMock(String userName,String screenName,String imgURL,String text,int retweets,int favorites,boolean isRetweet) throws TwitterException {
		 List<Status> tweetsInput=new ArrayList<>();
         Status nestedStatus=Mockito.mock(Status.class);
         Status baseStatus=Mockito.mock(Status.class);
         Status s = Mockito.mock(Status.class);
         
         User user = Mockito.mock(User.class);
         when(user.getName()).thenReturn(userName);
         when(user.getScreenName()).thenReturn(screenName);
         when(user.getBiggerProfileImageURL()).thenReturn(imgURL);
         
         Date date = Mockito.mock(Date.class);
         
         when(s.getUser()).thenReturn(user);
         when(s.getText()).thenReturn(text);
         when(s.getRetweetCount()).thenReturn(retweets);
         when(s.getCreatedAt()).thenReturn(date);
         when(s.getId()).thenReturn((long)1);
         
         tweetsInput.add(s);
         tweetsInput.add(s);
         
        
         Twitter twitterInstance = Mockito.mock(Twitter.class);
         QueryResult result = Mockito.mock(QueryResult.class);
         when(result.getTweets()).thenReturn(tweetsInput);   
         when(twitterInstance.search(any())).thenReturn(result);
         
         when(baseStatus.isRetweet()).thenReturn(isRetweet);
         when(nestedStatus.getFavoriteCount()).thenReturn(favorites);
         when(baseStatus.getRetweetedStatus()).thenReturn(nestedStatus);
         when(baseStatus.getFavoriteCount()).thenReturn(favorites);
         when(twitterInstance.showStatus(1)).thenReturn(baseStatus);
       
         return twitterInstance;
	 }
    /**
	  *  Tests the getProfile functionality by passing a Null twitter instance
	  *  assert that profile details is not null
	  */ 
	 @Test
	 public void testgetProfilewithnullInstance() throws TwitterException {
		    TwitterApi api = new TwitterApi();
	        api.SetTwitterInstance(null);
	        assertThat("", is(api.getProfile("").getLocation()));    
	    }
	 
	 /**
	  *  Tests the getProfile functionality by passing a twitterInstance(mocked)
	  *  Asserts whether profile object returned contains proper values
	  */ 
	 @Test
	 public void testgetProfile() throws TwitterException {
		    Twitter twitterInstance = createProfileMock("Prasanth","Prasanth",10,20,"Prasanth",10,"https://prasanth.jpg");
	        TwitterApi api = new TwitterApi();
	        api.SetTwitterInstance(twitterInstance);
	        assertThat("Prasanth", is(api.getProfile("").getLocation()));    
	    }
	 
	 /**
	  *  Returns the twitterInstance(mocked) having the User object 
	  *  @param Name UserName of the user
	  *  @param screenName Screen Name of the user
	  *  @param followerCount Number of Followers of the user
	  *  @param friendCount Number of Friends of the user
	  *  @param getLocation Location details of the user
	  *  @param getStatusCount Status Id of the user
	  *  @param profileURL URL of the profile
	  *  @return instance of the twitter object {@link twitterInstance} containing User details
	  */ 

    private Twitter createProfileMock(String Name,String screenName,int followerCount,int friendCount,String getLocation,int getStatusCount,String profileURL) throws TwitterException {
         User user = Mockito.mock(User.class);
         Twitter twitterInstance = Mockito.mock(Twitter.class);         
		 when(user.getName()).thenReturn(Name);
         when(user.getScreenName()).thenReturn(screenName);
         when(user.getFollowersCount()).thenReturn(followerCount);
         when(user.getFriendsCount()).thenReturn(friendCount);
         when(user.getLocation()).thenReturn(getLocation);
         when(user.getStatusesCount()).thenReturn(getStatusCount);
         when(user.getBiggerProfileImageURL()).thenReturn(profileURL);
         when(twitterInstance.showUser(any())).thenReturn(user);
    
         return twitterInstance;
	 }
}

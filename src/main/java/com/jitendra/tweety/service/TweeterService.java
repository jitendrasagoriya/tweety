package com.jitendra.tweety.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

 
import twitter4j.DirectMessage;
 
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TweeterService {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TweeterService.class);
	
	 
	public static Twitter getTwitterinstance() {
		/**
		 * if not using properties file, we can set access token by following way
		 */
		logger.info("getTwitterinstance : self");
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("lH4fyE00ULszvOT1Ym3cCC8Vl")
		  .setOAuthConsumerSecret("b7p1nvqeivjFO9ogxYE4EbySaVDsSrW1gsMhZOLVyjEANlVygv")
		  .setOAuthAccessToken("340289539-13exIfZsxj9ctw1ZgvYhlhPIuj5cvSrwDQtkjTbM")
		  .setOAuthAccessTokenSecret("W9PsfTCs7dPZpUUhQFeA0YXAu5I6n97LpXhwudidiMedH");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		//Twitter twitter = TwitterFactory.getSingleton();
		return twitter;
		
	}
	
	public static Twitter getTwitterinstance(String accessToken) {
		/**
		 * if not using properties file, we can set access token by following way
		 */
		logger.info("getTwitterinstance : header");
		String[] assesstokens = accessToken.split(",");
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("lH4fyE00ULszvOT1Ym3cCC8Vl")
		  .setOAuthConsumerSecret("b7p1nvqeivjFO9ogxYE4EbySaVDsSrW1gsMhZOLVyjEANlVygv")
		  .setOAuthAccessToken(assesstokens[0])
		  .setOAuthAccessTokenSecret(assesstokens[1]);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		//Twitter twitter = TwitterFactory.getSingleton();
		return twitter;
		
	}
	
	public void closeInstance() {}
	
	
	public static String createTweet(String tweet) throws TwitterException {
		Twitter twitter = getTwitterinstance();
		Status status = twitter.updateStatus("creating baeldung API");
	        return status.getText();
	}
	
	public static List<String> getTimeLine(boolean self,String accessToken) throws TwitterException {
		Twitter twitter = null;
		if(self) {
			twitter = getTwitterinstance();
		}else {
			twitter = getTwitterinstance(accessToken);
		}
		List<Status> statuses = twitter.getHomeTimeline();
		return statuses.stream().map(
				item -> item.getText()).collect(
						Collectors.toList());
	}
	
	public static String sendDirectMessage(String recipientName, String msg) throws TwitterException {
		Twitter twitter = getTwitterinstance();
	        DirectMessage message = twitter.sendDirectMessage(recipientName, msg);
	        return message.getText();
	}
	
	public static List<String> searchtweets() throws TwitterException {
		Twitter twitter = getTwitterinstance();
	        Query query = new Query("source:twitter4j baeldung");
	        QueryResult result = twitter.search(query);
	        List<Status> statuses = result.getTweets();
	        return statuses.stream().map(
				item -> item.getText()).collect(
						Collectors.toList());
	}
	
	 
	
	 

}

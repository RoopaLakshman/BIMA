package com.twitter.interview.model;

import java.util.Date;

public interface RelatedTweets {

	/*
	 * private long tweetId;
	 * 
	 * private Date timestamp;
	 * 
	 * private String tweet;
	 * 
	 * private String firstname;
	 * 
	 * private Long favcount;
	 * 
	 * private Long retweetscount;
	 */

	public long getTweetId();

	/*
	 * public void setTweetId(long tweetId) { this.tweetId = tweetId; }
	 */

	public Date getTimestamp();

	/*
	 * public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
	 */
	
	public String getTweet();

	/*
	 * public void setTweet(String tweet) { this.tweet = tweet; }
	 */

	public String getFirstname();

	/*
	 * public void setFirstname(String firstname) { this.firstname = firstname; }
	 */

	public Long getFavcount();
	
	/*
	 * public void setFavcount(Long favcount) { this.favcount = favcount; }
	 */

	public Long getRetweetscount();

	/*
	 * public void setRetweetscount(Long retweetscount) { this.retweetscount =
	 * retweetscount; }
	 */


}

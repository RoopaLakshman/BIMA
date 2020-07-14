package com.twitter.interview.model;

import java.util.Comparator;
import java.util.Date;

public class RelatedTweets implements Comparator<RelatedTweets> {

	private long tweetId;

	private Date timestamp;

	private String tweet;

	private String firstname;

	private Long favcount;

	private Long retweetscount;

	public long getTweetId() {
		return tweetId;
	}

	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public long getFavcount() {
		return favcount;
	}

	public void setFavcount(long favcount) {
		this.favcount = favcount;
	}

	public long getRetweetscount() {
		return retweetscount;
	}

	public void setRetweetscount(long retweetscount) {
		this.retweetscount = retweetscount;
	}

	@Override
	public int compare(RelatedTweets o1, RelatedTweets o2) {
	        int value1 = o1.favcount.compareTo(o2.favcount);
	        if (value1 == 0) {
	            int value2 = o1.retweetscount.compareTo(o2.retweetscount);
	            if (value2 == 0) {
	            	Long timestamp1 = (o1.getTimestamp().getTime()/1000);
	            	Long timestamp2 = (o2.getTimestamp().getTime()/1000);
	                return timestamp1.compareTo(timestamp2);
	            } else {
	                return value2;
	            }
	        }
	        return value1;
	}
}

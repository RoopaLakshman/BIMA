package com.twitter.interview.model;

import javax.validation.constraints.NotNull;

public class Retweet {

	@NotNull
	private long tweetId;

	public long getTweetId() {
		return tweetId;
	}

	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}

	@Override
	public String toString() {
		return "Retweet [tweetId=" + tweetId + "]";
	}

}

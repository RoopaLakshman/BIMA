package com.twitter.interview.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tweetId;

	private String tweet;

	private Date timestamp;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToMany(mappedBy = "favouriteTweets")
	private Set<User> favouriteBy;

	@ManyToMany(mappedBy = "reTweets")
	private Set<User> retweetedBy;

	public Tweet() {
		super();
	}

	public long getTweetId() {
		return tweetId;
	}

	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getFavouriteBy() {
		return favouriteBy;
	}

	public void setFavouriteBy(Set<User> favouriteBy) {
		this.favouriteBy = favouriteBy;
	}

	public Set<User> getRetweetedBy() {
		return retweetedBy;
	}

	public void setRetweetedBy(Set<User> retweetedBy) {
		this.retweetedBy = retweetedBy;
	}

	@Override
	public String toString() {
		return "Tweet [tweetId=" + tweetId + ", tweet=" + tweet + ", timestamp=" + timestamp + "]";
	}

}

package com.twitter.interview.model;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Tweet object used for posting a tweet")
@Entity
public class TweetVo {

	@Size(min = 1, max = 500)
	@ApiModelProperty(notes = "Tweet content should not exceed more than 50 characters")
	private String tweet;

	public TweetVo() {
		super();
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	@Override
	public String toString() {
		return "TweetVo [tweet=" + tweet + "]";
	}

}

package com.twitter.interview.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	private String firstname;

	private String lastname;

	@JsonIgnore
	private String password;

	private String email;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "favourite_tweets", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "tweetId"))
	private Set<Tweet> favouriteTweets = new HashSet<Tweet>();;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "retweets", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "tweetId"))
	private Set<Tweet> reTweets = new HashSet<Tweet>();;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "followers", joinColumns = @JoinColumn(name = "followeeId"), inverseJoinColumns = @JoinColumn(name = "followerId"))
	private Set<User> followers = new HashSet<User>();

	public User() {
		super();
	}

	public User(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public User(int id, String firstname, String lastname) {
		super();
		this.userId = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Set<Tweet> getFavouriteTweets() {
		return favouriteTweets;
	}

	public void setFavouriteTweets(Set<Tweet> favouriteTweets) {
		this.favouriteTweets = favouriteTweets;
	}

	public Set<Tweet> getReTweets() {
		return reTweets;
	}

	public void setReTweets(Set<Tweet> reTweets) {
		this.reTweets = reTweets;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + "]";
	}

}

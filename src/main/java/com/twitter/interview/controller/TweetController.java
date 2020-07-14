package com.twitter.interview.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.twitter.interview.entity.Tweet;
import com.twitter.interview.entity.User;
import com.twitter.interview.exception.NotFoundException;
import com.twitter.interview.model.FavoritizeTweet;
import com.twitter.interview.model.Retweet;
import com.twitter.interview.model.TweetVo;
import com.twitter.interview.repository.TweetRepository;
import com.twitter.interview.repository.UserRepository;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "The tweet object used to post a tweet")
@RestController
public class TweetController {
	
	@Autowired
	private TweetRepository tweetRepo;
	
	@Autowired
	private UserRepository userRepo;

	//Save tweet
	@PostMapping(path="/user/{user_id}/tweet")
	public ResponseEntity<Object> postTweet(@Valid @PathVariable("user_id") long userId, @RequestBody TweetVo tweet) {
		Optional<User> user = userRepo.findById(userId);
		if(!user.isPresent()) {
			throw new NotFoundException(new Date(), "User by id "+userId + " is not found", "Error in the request sent");
		} 
		com.twitter.interview.entity.Tweet t = new com.twitter.interview.entity.Tweet();
		t.setTweet(tweet.getTweet());
		t.setTimestamp(new Date());
		t.setUser(user.get());
		com.twitter.interview.entity.Tweet resp = tweetRepo.save(t);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resp.getTweetId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	//get all tweets
	@GetMapping(path="/user/tweet")
	public List<Tweet> getAllTweets() {
		return tweetRepo.findAll();
	}

	//like a tweet
	@PostMapping(path="/user/{user_id}/favouritetweet")
	public ResponseEntity<Object> favouriteTweet(@Valid @PathVariable("user_id") long userId, @RequestBody FavoritizeTweet favouriteTweet) {
		Optional<Tweet> tweet = tweetRepo.findById(favouriteTweet.getTweetId());
		if (!tweet.isPresent()) {
			throw new NotFoundException(new Date(), "This tweet with ID " + favouriteTweet.getTweetId() + " is not found",
					"Resource not found");
		}
		Optional<User> u = userRepo.findById(userId);
		if (!u.isPresent()) {
			throw new NotFoundException(new Date(), "User by ID " + userId + " is not found",
					"Resource not found");
		}
		try {
			User user = u.get();
			user.getFavouriteTweets().add(tweet.get());
			userRepo.save(user);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//retweet
	@PostMapping(path = "/user/{user_id}/retweet")
	public ResponseEntity<Object> retweet(@Valid @PathVariable("user_id") long userId, @RequestBody Retweet retweet) {
		
		Optional<Tweet> tweet = tweetRepo.findById(retweet.getTweetId());
		if (!tweet.isPresent()) {
			throw new NotFoundException(new Date(), "This tweet with ID " + retweet.getTweetId() + " is not found",
					"Resource not found");
		}
		
		Optional<User> u = userRepo.findById(userId);
		if (!u.isPresent()) {
			throw new NotFoundException(new Date(), "User by ID " + userId + " is not found",
					"Resource not found");
		}
		
		try {
			User user = u.get();
			user.getReTweets().add(tweet.get());
			userRepo.save(user);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}

package com.twitter.interview.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.twitter.interview.comparator.ChronologyComparator;
import com.twitter.interview.comparator.RelatedTweetsComparator;
import com.twitter.interview.entity.User;
import com.twitter.interview.exception.NotFoundException;
import com.twitter.interview.model.RelatedTweets;
import com.twitter.interview.model.UserVo;
import com.twitter.interview.repository.UserRepository;
import com.twitter.interview.utils.DateUtil;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// save user
	@PostMapping(path = "/user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody UserVo userDetails) {
		User user = new User();
		user.setFirstname(userDetails.getFirstname());
		user.setEmail(userDetails.getEmail());
		user.setLastname(userDetails.getLastname());
		User u = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getUserId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	// retrieve all users
	@GetMapping(path = "/user/all")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// retrieve one user
	@GetMapping(path = "/user/{user_id}")
	public User findUser(@PathVariable("user_id") long id) {

		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new NotFoundException(new Date(), "user by ID - " + id + " is not found", "Resource not found");
		}
		return user.get();
	}

	// Delete user
	@DeleteMapping(path = "/user/{user_id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("user_id") long id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException(new Date(), "user by ID - " + id + " is not found ", "Resource not found");
		}

		return ResponseEntity.noContent().build();
	}

	// Follow user
	@PostMapping(path = "/user/{user_id}/follow")
	public ResponseEntity<Object> followUser(@PathVariable("user_id") long id, @RequestBody long followeeId) {

		Optional<User> followee = userRepository.findById(followeeId);
		if (!followee.isPresent()) {
			throw new NotFoundException(new Date(), "Followee by ID " + followeeId + " user is not found",
					"Resource not found");
		}

		Optional<User> follower = userRepository.findById(id);
		if (!follower.isPresent()) {
			throw new NotFoundException(new Date(), "Follower by ID " + follower + " user is not found",
					"Resource not found");
		}
		try {
			User enduser = followee.get();
			enduser.getFollowers().add(follower.get());
			userRepository.save(enduser);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Get Timeline feeds
	@GetMapping(path = "/user/{user_id}/load/usertimeline")
	public List<RelatedTweets> loadUserTimeline(@PathVariable("user_id") long id) {
		Optional<User> follower = userRepository.findById(id);
		if (!follower.isPresent()) {
			throw new NotFoundException(new Date(), "Follower by ID " + follower + " user is not found",
					"Resource not found");
		}
		
		List<RelatedTweets> relatedTweets  = new ArrayList<RelatedTweets>();
		relatedTweets = userRepository.getRelatedTweets(id);
		
		List<RelatedTweets> topTweets  = new ArrayList<RelatedTweets>();
		List<RelatedTweets> chronologyTweets  = new ArrayList<RelatedTweets>();
		
		Iterator<RelatedTweets> iterator = relatedTweets.iterator();
		while(iterator.hasNext()) {
			RelatedTweets t = iterator.next();
			if(DateUtil.inLastDay(t.getTimestamp())) {
				topTweets.add(t);
			} else {
				chronologyTweets.add(t);
			}
		}		
		
		Collections.sort(chronologyTweets, new ChronologyComparator());
		Collections.sort(topTweets, new RelatedTweetsComparator());
		
		Collections.reverse(topTweets);
		Collections.reverse(chronologyTweets);
		
		topTweets.addAll(chronologyTweets);
		
		return topTweets;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}

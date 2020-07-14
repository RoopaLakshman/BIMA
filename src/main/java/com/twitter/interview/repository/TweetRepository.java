package com.twitter.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twitter.interview.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{

}

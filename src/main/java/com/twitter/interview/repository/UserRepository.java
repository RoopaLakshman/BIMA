package com.twitter.interview.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.twitter.interview.entity.User;
import com.twitter.interview.model.RelatedTweets;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT t.tweet_id as tweetid, t.timestamp, t.tweet, o.firstname, count(ft.tweet_id) as favcount, " + 
					"	count(rt.tweet_id) as retweetscount FROM tweet t" + 
					"	left outer join user o on o.user_id = t.user_id " + 
					"	left outer join followers f on f.followee_id = t.user_id " + 
					"	left outer join user u on u.User_id = f.follower_id " + 
					"	left outer join favourite_tweets ft on ft.tweet_id = t.tweet_id " + 
					"	left outer join retweets rt on rt.tweet_id = t.tweet_id " + 
					"	where u.user_id = :id" + 
					"	group by t.tweet_id order by timestamp desc", nativeQuery = true)
	public ArrayList<RelatedTweets> getRelatedTweets(long id);
	

}

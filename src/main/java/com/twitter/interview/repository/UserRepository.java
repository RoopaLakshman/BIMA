package com.twitter.interview.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.twitter.interview.entity.User;
import com.twitter.interview.model.RelatedTweets;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT t.tweet_id as tweetid, t.timestamp, t.tweet, o.firstname, count(ft.tweet_id) as favcount, count(rt.tweet_id) as retweetscount FROM Tweet t" + 
			" left outer join User o on o.user_id = t.user_id" + 
			" left outer join Followers f on f.followee_id = t.user_id" + 
			" left outer join User u on u.User_id = f.follower_id" + 
			" left outer join Favourite_tweets ft on ft.tweet_id = t.tweet_id" + 
			" left outer join Retweets rt on rt.tweet_id = t.tweet_id" + 
			" where u.user_id = :id" + 
			" group by t.tweet_id order by FAV_COUNT desc", nativeQuery = true)
	public ArrayList<RelatedTweets> getRelatedTweets(long id);
	

}

package com.twitter.interview.comparator;

import java.util.Comparator;

import com.twitter.interview.model.RelatedTweets;

public class RelatedTweetsComparator implements Comparator<RelatedTweets> {
	
	@Override
	public int compare(RelatedTweets o1, RelatedTweets o2) {
	        int value1 = o1.getFavcount().compareTo(o2.getFavcount());
	        if (value1 == 0) {
	            int value2 = o1.getRetweetscount().compareTo(o2.getRetweetscount());
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

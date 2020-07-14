package com.twitter.interview.comparator;

import java.util.Comparator;

import com.twitter.interview.model.RelatedTweets;

public class ChronologyComparator implements Comparator<RelatedTweets> {

	@Override
	public int compare(RelatedTweets o1, RelatedTweets o2) {
		Long timestamp1 = (o1.getTimestamp().getTime() / 1000);
		Long timestamp2 = (o2.getTimestamp().getTime() / 1000);

		int value1 = timestamp1.compareTo(timestamp2);
		return value1;
	}

}

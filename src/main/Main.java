/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pb.main;

import pb.rss.Feed;
import pb.rss.FeedMessage;
import pb.rss.RSSFeedParser;

/**
 *
 * @author miguel
 */
public class Main {

    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser("http://feeds.reuters.com/Reuters/UKTopNews?format=xml");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }

}

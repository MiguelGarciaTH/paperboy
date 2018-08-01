/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pb.main;

import com.google.common.collect.Lists;
import com.twitter.hbc.httpclient.auth.Authentication;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import pb.rss.Feed;
import pb.rss.FeedMessage;
import pb.rss.RSSFeedParser;
import socialnetworks.twitter.TwitterAuthenticator;
import socialnetworks.twitter.TwitterClient;
import socialnetworks.twitter.TwitterInputStream;
import socialnetworks.twitter.TwitterOutputStream;

/**
 *
 * @author miguel
 */
public class Main {

    public static void main(String[] args) {
        Twitter();
    }

    private static void Twitter() {
        Authentication auth = new TwitterAuthenticator()
                .setConsumerKey("XXX")
                .setConsumerSecret("XXX")
                .setSecretToken("YXXX")
                .setToken("XXXX")
                .build();
        List<String> terms = Lists.newArrayList("Trump", "donald");
        BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>(10000);
        TwitterClient client = new TwitterClient(inputQueue, auth, terms);
        TwitterInputStream tis = new TwitterInputStream(client);
        tis.start();
        TwitterOutputStream tos = new TwitterOutputStream(client);
        new Thread(tos).start();
    }

    private static void tetsParser() {
        RSSFeedParser parser = new RSSFeedParser("http://feeds.reuters.com/Reuters/UKTopNews?format=xml");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }
}

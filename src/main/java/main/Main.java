/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pb.main;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pb.rss.Feed;
import pb.rss.FeedMessage;
import pb.rss.RSSFeedParser;
import socialnetworks.twitter.TwitterAuthenticator;
import socialnetworks.twitter.TwitterJSONParser;

/**
 *
 * @author miguel
 */
public class Main {

    public static void main(String[] args) {
        testTwitter();
    }

    private static void testTwitter() {
        /**
         * Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream
         */
        TwitterJSONParser parser = new TwitterJSONParser();
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

        /**
         * Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth)
         */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
// Optional: set up some followings and track terms
        List<Long> followings = Lists.newArrayList(1234L, 566788L);
        List<String> terms = Lists.newArrayList("Trump", "donald");
        hosebirdEndpoint.followings(followings);
        hosebirdEndpoint.trackTerms(terms);

// These secrets should be read from a config file
        Authentication auth = new TwitterAuthenticator()
                .setConsumerKey("wbk1k9yvhCSDsWbXUcMtBhfw6")
                .setConsumerSecret("GQcWhUwOLu4Rn8a6tzi1OsoAadzCa8hRV2fpOXAftaE39JJnek")
                .setSecretToken("YjWKX8z4yvquk8nNeMlRIIiYi3FsfqwyPeR8WfYlhlwCc")
                .setToken("2408826883-n9yQ7Dnaf8ZaqauqwVIXLhyIlz1UszqdQ6VwaGH")
                .build();

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01") // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(auth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);                          // optional: use this if you want to process client events

        Client hosebirdClient = builder.build();
// Attempts to establish a connection.
        hosebirdClient.connect();
        // on a different thread, or multiple different threads....
        while (!hosebirdClient.isDone()) {
            try {
                String msg = msgQueue.take();
                System.out.println("RAW=" + msg);
                System.out.println("TEXT=" + parser.parse(msg));
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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

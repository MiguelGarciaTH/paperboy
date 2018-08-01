/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworks.twitter;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author miguel
 */
public class TwitterClient {

    private final BlockingQueue<String> inputQueue;
    private final Client hosebirdClient;

    public TwitterClient(BlockingQueue inputQueue, Authentication auth, List<String> terms) {
        this.inputQueue = inputQueue;
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01") // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(auth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(inputQueue));
        hosebirdEndpoint.trackTerms(terms);
        hosebirdClient = builder.build();
    }

    public boolean isDone(){
        return hosebirdClient.isDone();
    }
    
    public BlockingQueue<String> getInputQueue(){
        return inputQueue;
    }
    public void start() {
        hosebirdClient.connect();

    }

    public void stop() {
        hosebirdClient.stop();

    }
}

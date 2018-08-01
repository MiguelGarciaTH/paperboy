/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworks.twitter;

/**
 *
 * @author miguel
 */
public class TwitterInputStream {

    private TwitterClient client;

    public TwitterInputStream(TwitterClient client) {
        this.client = client;
    }

    public void start() {
        client.start();
    }

    public void stop() {
        client.stop();
    }

}

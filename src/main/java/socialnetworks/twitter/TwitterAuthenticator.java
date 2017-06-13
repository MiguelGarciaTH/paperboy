/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworks.twitter;

import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

/**
 *
 * @author miguel
 */
public class TwitterAuthenticator {

    private String consumerKey;
    private String consumerSecret;
    private String token;
    private String secretToken;

    public TwitterAuthenticator() {
    }

    public String getSecretToken() {
        return secretToken;
    }

    public TwitterAuthenticator setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
        return this;
    }

    public TwitterAuthenticator setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
        return this;
    }

    public TwitterAuthenticator setToken(String token) {
        this.token = token;
        return this;
    }

    public TwitterAuthenticator setSecretToken(String secretToken) {
        this.secretToken = secretToken;
        return this;
    }

    public Authentication build() {
        return new OAuth1(this.consumerKey, this.consumerSecret, this.token, this.secretToken);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworks.twitter;

import org.json.JSONObject;

/**
 *
 * @author miguel
 */
public class TwitterJSONParser {

    public TwitterJSONParser() {
    }
    /**
     * Transforms raw tweets (kind of json format) into TwitterMessage
     * @param rawTweet, text retrieved from the Streamer API
     * @return TwitterMessage with the relevant fields 
     */
    public TwitterMessage parse(String rawTweet) {
        TwitterMessage message = new TwitterMessage();
        JSONObject obj = new JSONObject(rawTweet);
        message.setText(obj.getString("text"));
        message.setAuthor(obj.getJSONObject("user").getString("name"));
        message.setDate(obj.getString("created_at"));
        return message;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworks.twitter;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pb.main.Main;

/**
 *
 * @author miguel
 */
public class TwitterOutputStream implements Runnable{
    
    private final BlockingQueue<String> outputQueue;
    private final TwitterClient client;
    private final TwitterJSONParser parser;

    public TwitterOutputStream(TwitterClient client) {
        
        this.client = client;
        this.outputQueue = client.getInputQueue();
        this.parser=new TwitterJSONParser();
    }

    @Override
    public void run() {
        while (!client.isDone()) {
            try {
                String msg = outputQueue.take();
//                System.out.println("RAW=" + msg);
                System.out.println(parser.parse(msg));
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
    
}

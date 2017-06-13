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
public class TwitterMessage {

    private String text;
    private String author;
    private String date;

    public TwitterMessage(String text, String author, String date) {
        this.text = text;
        this.author = author;
        this.date = date;
    }

    TwitterMessage() {
        
    }

    @Override
    public String toString() {
        return "TwitterMessage{" + "text=" + text + ", author=" + author + ", date=" + date + '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

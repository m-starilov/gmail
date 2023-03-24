package com.gmail.model;

import java.util.Objects;

public class Message {
    private String to;
    private String subject;
    private String text;

    public Message() {
    }

    public Message(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(to, message.to) && Objects.equals(subject, message.subject) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, subject, text);
    }

    @Override
    public String toString() {
        return "Message{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

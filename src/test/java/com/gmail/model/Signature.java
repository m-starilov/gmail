package com.gmail.model;

import java.util.Objects;

public class Signature {
    private String name;

    private String text;

    public Signature(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Signature signature = (Signature) o;
        return Objects.equals(name, signature.name) && Objects.equals(text, signature.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text);
    }

    @Override
    public String toString() {
        return "Signature{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

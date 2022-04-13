package com.epam.jwd.task2.model.entity;

public abstract class CompositeTextElement extends TextElement{
    public abstract void add(TextElement composeTextElement);
    public abstract void remove(TextElement composeTextElement);
}

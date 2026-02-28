package com.oitc;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {

    private List<Topic> topics;

    public KnowledgeBase() {
        topics = new ArrayList<>();

        topics.add(new Topic(
                "Java Basics",
                "Java is an object-oriented programming language. It runs on the Java Virtual Machine (JVM)."
        ));

        topics.add(new Topic(
                "OOP",
                "OOP stands for Object Oriented Programming. It includes encapsulation, inheritance, polymorphism and abstraction."
        ));
    }

    public Topic findTopic(String keyword) {
        for (Topic topic : topics) {
            if (topic.getName().toLowerCase().contains(keyword.toLowerCase())) {
                return topic;
            }
        }
        return null;
    }
}
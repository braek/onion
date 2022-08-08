package be.koder.library.domain;

public interface EventPublisher {
    void publish(Event event);
}
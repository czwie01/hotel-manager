package rooms.domain.model;

import java.util.LinkedList;
import java.util.Queue;

import rooms.domain.events.DomainEvent;

public abstract class Entity {
    protected final Queue<DomainEvent> domainEvents = new LinkedList<>();

    public final Queue<DomainEvent> domainEvents() {
        return domainEvents;
    }
}

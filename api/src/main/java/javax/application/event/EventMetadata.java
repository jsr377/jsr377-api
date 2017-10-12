package javax.application.event;

import java.time.Instant;

/**
 * Defines metadata of an event. This type provides additional information that can be used in a {@link EventFilter}
 *
 * @param <E> type of the event
 *
 * @author Hendrik Ebbers
 * @author Andres Almiray
 */
public interface EventMetadata<E> {
    /**
     * The point in time when this event was published.
     */
    Instant getTimestamp();

    /**
     * The event that was published.
     */
    E getEvent();
}

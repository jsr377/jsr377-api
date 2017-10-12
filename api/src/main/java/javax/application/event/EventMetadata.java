package javax.application.event;

/**
 * Defines all metadata of an event. This is used to provide additional information for an {@link EventFilter}
 * @param <E> type of the event
 *
 * @author Hendrik Ebbers
 */
public interface EventMetadata<E> {

    /**
     * The event 
     * @return the event
     */
    E getEvent();

}

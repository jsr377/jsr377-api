/*
 * Copyright 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javax.application.event;

/**
 * Base contract for classes that can publish events using their own
 * event bus.
 *
 * @author Andres Almiray
 * @author Hendrik Ebbers
 */
public interface EventPublisher {

    /**
     * Adds an event handler.<p> This event handler will automatically be registered for all
     * event types that are supported by the handler.
     *
     * @param handler an event handler. Must not be {@code null}.
     */
    void subscribe(Object handler);

    /**
     * Adds an event handler.<p> This event handler will only be registered for the given
     * event type.
     * @param eventType the event type. Must not be {@code null}.
     * @param handler an event handler. Must not be {@code null}.
     */
    void subscribe(Class<?> eventType, Object handler);

    /**
     * Removes an event handler.<p> This event handler will automatically be deregistered for all
     * event types that are supported by the handler.
     *
     * @param handler an event handler. Must not be {@code null}.
     */
    void unsubscribe(Object handler);

    /**
     * Removes an event handler.<p> This event handler will only be deregistered for the given
     * event type.
     * @param eventType the event type. Must not be {@code null}.
     * @param handler an event handler. Must not be {@code null}.
     */
    void unsubscribe(Class<?> eventType, Object handler);

    /**
     * Publishes an event.<p>
     * Handlers will be notified in the same thread as the publisher.
     *
     * @param event the event to be published. Must not be {@code null}.
     */
    <E> void publishEvent(E event);

    /**
     * Publishes an event.<p>
     * Handlers will be notified in a different thread.
     *
     * @param event the event to be published. Must not be {@code null}.
     */
    <E> void publishEventAsync(E event);
}

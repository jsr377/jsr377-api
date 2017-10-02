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

import java.util.function.Consumer;

/**
 * Base contract for classes that can publish events using their own
 * event bus.
 *
 * @author Andres Almiray
 */
public interface EventPublisher {
    /**
     * Adds an event handler.<p>
     *
     * @param handler an event handler. Must not be {@code null}.
     */
    void addEventHandler(Object handler);

    /**
     * Adds a {@code Consumer} as event handler.<p>
     *
     * @param eventType    the type of the event. Must not be {@code null}.
     * @param eventHandler an event handler. Must not be {@code null}.
     * @param eventFilters event filters to be applied (if any)
     */
    <E> void addEventHandler(Class<E> eventType, Consumer<? extends E> eventHandler, Consumer<? extends E>... eventFilters);

    /**
     * Removes an event handler.<p>
     *
     * @param handler an event handler. Must not be {@code null}.
     */
    void removeEventHandler(Object handler);

    /**
     * Removes a {@code Consumer} as an event handler.<p>
     *
     * @param eventType    the type of the event. Must not be {@code null}.
     * @param eventHandler an event handler. Must not be {@code null}.
     */
    <E> void removeEventHandler(Class<E> eventType, Consumer<? extends E> eventHandler);

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

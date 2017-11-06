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
package javax.application.context;

import static java.util.Objects.requireNonNull;

/**
 * A specialization of {@code Context} that can trigger events when a key/value pair changes.
 * The following rules apply to determine if an event will be triggered:
 * <ul>
 * <strong>{@code ContextEvent.Type.ADD}</strong>
 * <li>The {@code key} did not exist before in this context or its parent.</li>
 * </ul>
 * <p>
 * <ul>
 * <strong>{@code ContextEvent.Type.UPDATE}</strong>
 * <li>The {@code key} is found in this context and its previous value differs from the new one.</li>
 * <li>The {@code key} is found in the parent context and its value differs from the parent's value.</li>
 * <li>A {@code key} is removed from this context; the parent context also contains {@code key} and its value differs from the value just removed from this context.</li>
 * </ul>
 * <p>
 * <ul>
 * <strong>{@code ContextEvent.Type.REMOVE}</strong>
 * <li>The {@code key} is removed from this context; the {@code key} is not found in its parent context.</li>
 * <li>The {@code key} is removed from the  parent context; the {@code key} is not found in this context.</li>
 * </ul>
 *
 * @author Andres Almiray
 */
public interface ObservableContext extends Context {
    /**
     * Registers a {@code ContextEventListener} with this Context.
     *
     * @param listener the listener to be added; {@code null} and/or
     *                 duplicate values must be ignored.
     */
    void addContextEventListener(ContextEventListener listener);

    /**
     * UnRegisters a {@code ContextEventListener} from this {@code ObservableContext}.
     *
     * @param listener the listener to be removed; {@code null} and/or
     *                 duplicate values must be ignored.
     */
    void removeContextEventListener(ContextEventListener listener);

    /**
     * Returns an array of all the listeners that were added to this {@code ObservableContext}.
     *
     * @return all of the <code>ContextEventListener</code> added or an
     * empty array if no listeners have been added. Never returns {@code null}.
     */
    ContextEventListener[] getContextEventListeners();

    /**
     * A {@code ContextEvent} event gets fired whenever a context key is added, changes value, or is removed
     * from a context or its context hierarchy.
     */
    interface ContextEventListener {
        /**
         * Handle value changes published by instances of {@code ObservableContext}.
         *
         * @param contextEvent an event triggered when the value of a context key changed. Must not be {@code null}.
         */
        void contextChanged(ContextEvent contextEvent);
    }

    /**
     * A "ContextEvent" event gets delivered whenever a context changes the value of a key due to
     * key additions, updates, or removals.
     * <p>
     * Normally ContextEvents are accompanied by the name and the old
     * and new value of the changed key.  If the new value is a primitive
     * type (such as int or boolean) it must be wrapped as the
     * corresponding java.lang.* Object type (such as Integer or Boolean).
     * <p>
     * Null values may be provided for the old and the new values if their
     * true values are not known.
     * <p>
     */
    class ContextEvent {
        private final Type type;
        private final String key;
        private final Object oldValue;
        private final Object newValue;

        /**
         * Constructs a new {@code ContextEvent}.
         *
         * @param type     the type of event. Must not be {@code null}.
         * @param key      the programmatic name of the key that was changed. Must not be {@code null}.
         * @param oldValue the old value of the key. May be {@code null}.
         * @param newValue the new value of the key. May be {@code null}.
         *
         * @throws NullPointerException if either {@code type} or {@code key} are {@code null}.
         */
        public ContextEvent(Type type, String key, Object oldValue, Object newValue) {
            this.type = requireNonNull(type, "Argument 'type' must not be null");
            this.key = requireNonNull(key, "Argument 'key' must not be null");
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        /**
         * Gets the type of this event.
         *
         * @return One of {@code ADD}, {@code UPDATE}, or {@code REMOVE}. Never returns {@code null}.
         */
        public Type getType() {
            return type;
        }

        /**
         * Gets the programmatic name of the key that was changed.
         *
         * @return The programmatic name of the key that was changed. Never returns {@code null}.
         */
        public String getKey() {
            return key;
        }

        /**
         * Gets the new value for the key, expressed as an Object.
         *
         * @return The new value for the key, expressed as an Object. May be {@code null}.
         */
        public Object getOldValue() {
            return oldValue;
        }

        /**
         * Gets the old value for the key, expressed as an Object.
         *
         * @return The old value for the key, expressed as an Object. May be {@code null}.
         */
        public Object getNewValue() {
            return newValue;
        }

        @Override
        public int hashCode() {
            int result = type.hashCode();
            result = 31 * result + key.hashCode();
            result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
            result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }

            ContextEvent that = (ContextEvent) o;

            return key.equals(that.key) &&
                !(newValue != null ? !newValue.equals(that.newValue) : that.newValue != null) &&
                !(oldValue != null ? !oldValue.equals(that.oldValue) : that.oldValue != null) &&
                type == that.type;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ContextEvent{");
            sb.append("type=").append(type);
            sb.append(", key='").append(key).append('\'');
            sb.append(", oldValue=").append(oldValue);
            sb.append(", newValue=").append(newValue);
            sb.append('}');
            return sb.toString();
        }

        /**
         * Describes the type of a {@code ContextEvent}.
         */
        public enum Type {
            ADD,
            REMOVE,
            UPDATE
        }
    }
}

/*
 * Copyright 2015-2016 the original author or authors.
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
package javax.application;

import static java.util.Objects.requireNonNull;

/**
 * A specialization of {@code Context} that can trigger events when a key/value pair changes.
 * The following rules apply to determine if an event will be triggered:
 * <ul>
 * <strong><tt>ContextEvent.Type.ADD</tt></strong>
 * <li>The <tt>key</tt> did not exist before in this context or its parent.</li>
 * </ul>
 * <p>
 * <ul>
 * <strong><tt>ContextEvent.Type.UPDATE</tt></strong>
 * <li>The <tt>key</tt> is found in this context and its previous value differs from the new one.</li>
 * <li>The <tt>key</tt> is found in the parent context and its value differs from the parent's value.</li>
 * <li>A <tt>key</tt> is removed from this context; the parent context also contains <tt>key</tt> and its value differs from the value just removed from this context.</li>
 * </ul>
 * <p>
 * <ul>
 * <strong><tt>ContextEvent.Type.REMOVE</tt></strong>
 * <li>The <tt>key</tt> is removed from this context; the <tt>key</tt> is not found in its parent context.</li>
 * <li>The <tt>key</tt> is removed from the  parent context; the <tt>key</tt> is not found in this context.</li>
 * </ul>
 *
 * @author Andres Almiray
 */
public interface ObservableContext extends Context {
    /**
     * Registers a {@code ContextEventListener} with this Context.
     * <strong>Implementation notes:</strong>
     * <ul>
     * <li><tt>null</tt> arguments are ignored.</li>
     * <li>duplicate values are discarded.</li>
     * </ul>
     *
     * @param listener the listener to be added.
     */
    void addContextEventListener(ContextEventListener listener);

    /**
     * UnRegisters a {@code ContextEventListener} from this {@code ObservableContext}.
     * <strong>Implementation notes:</strong>
     * <ul>
     * <li><tt>null</tt> arguments are ignored.</li>
     * <li>removing a listener that's not registered has no effect.</li>
     * </ul>
     *
     * @param listener the listener to be removed.
     */
    void removeContextEventListener(ContextEventListener listener);

    /**
     * Returns an array of all the listeners that were added to this {@code ObservableContext}.
     *
     * @return all of the <code>ContextEventListener</code> added or an
     * empty array if no listeners have been added.
     */
    ContextEventListener[] getContextEventListeners();

    /**
     * A <tt>ContextEvent</tt> event gets fired whenever a context key is added, changes value, or is removed
     * from a context or its context hierarchy.
     */
    interface ContextEventListener {
        /**
         * Handle value changes published by instances of {@code ObservableContext}.
         *
         * @param contextEvent an event triggered when the value of a context key changed.
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
         * @param type     the type of event
         * @param key      the programmatic name of the key that was changed
         * @param oldValue the old value of the key
         * @param newValue the new value of the key
         *
         * @throws NullPointerException if either {@code type} or {@code key} are {@code null}.
         */
        public ContextEvent(Type type, String key, Object oldValue, Object newValue) {
            this.type = requireNonNull(type, "Argument 'type' must not be null");
            this.key = requireNonNull(key, "Argument 'key' must not be null");
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public Type getType() {
            return type;
        }

        /**
         * Gets the programmatic name of the key that was changed.
         *
         * @return The programmatic name of the key that was changed.
         */
        public String getKey() {
            return key;
        }

        /**
         * Gets the new value for the key, expressed as an Object.
         *
         * @return The new value for the key, expressed as an Object.
         */
        public Object getOldValue() {
            return oldValue;
        }

        /**
         * Gets the old value for the key, expressed as an Object.
         *
         * @return The old value for the key, expressed as an Object.
         */
        public Object getNewValue() {
            return newValue;
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
        public int hashCode() {
            int result = type.hashCode();
            result = 31 * result + key.hashCode();
            result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
            result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
            return result;
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
         *
         */
        public enum Type {
            ADD,
            REMOVE,
            UPDATE
        }
    }
}

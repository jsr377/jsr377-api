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

import java.util.Set;

/**
 * @author Andres Almiray
 */
public interface Context {
    /**
     * Searches for the key in this context and its hierarchy.
     *
     * @param key the key to search.
     *
     * @return <tt>true</tt> if the context (or its parent) contains the given key, <tt>false</tt> otherwise.
     */
    boolean containsKey(String key);

    /**
     * Searches for the key in this context only.
     *
     * @param key the key to search.
     *
     * @return <tt>true</tt> if the context contains the given key, <tt>false</tt> otherwise.
     */
    boolean hasKey(String key);

    /**
     * Removes a key from this context. Does not affect the context's hierarchy.
     * Blindly casts the returned value.
     *
     * @param key the key to be removed.
     *
     * @return the value associated with the key or <tt>null</tt> if there wasn't any value.
     */
    <T> T remove(String key);

    /**
     * Removes a key from this context. Does not affect the context's hierarchy. The value is
     * converted to type <tt>T</tt> if found using a {@code PropertyEditor}.
     *
     * @param key  the key to be removed.
     * @param type the type to be returned.
     *
     * @return the value associated with the key or <tt>null</tt> if there wasn't any value.
     */
    <T> T removeConverted(String key, Class<T> type);

    /**
     * Sets a key/value pair on this context. If the context has a parent and if the
     * key matches a parent key then the value will shadow the parent's, that is, the parent
     * value will not be overwritten.
     *
     * @param key   the key to be registered.
     * @param value the value to save.
     */
    <T> void put(String key, T value);

    /**
     * /**
     * Finds a value associated with the given key. The value is
     * blindly cast to type <tt>T</tt> if found.
     *
     * @param key the key to search.
     */
    <T> T get(String key);

    /**
     * Finds a value associated with the given key. The value is
     * blindly cast to type <tt>T</tt> if found. If not found then the
     * supplied <tt>defaultValue</tt> will be returned.
     *
     * @param key          the key to search.
     * @param defaultValue the value to be returned if the key is not found.
     */
    <T> T get(String key, T defaultValue);

    /**
     * Destroys this context. Once destroyed a context should not be used anymore.
     */
    void destroy();

    /**
     * Returns the parent {@code Context} if it exists.
     */
    Context getParentContext();

    /**
     * Returns a {@link Set} view of the keys contained in this context.
     *
     * @return a set view of the keys contained in this map
     */
    Set<String> keySet();

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>boolean</tt> if found.
     *
     * @param key the key to search.
     */
    boolean getAsBoolean(String key);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>boolean</tt> if found. If not found then the
     * supplied <tt>defaultValue</tt> will be returned.
     *
     * @param key          the key to search.
     * @param defaultValue the value to be returned if the key is not found.
     */
    boolean getAsBoolean(String key, boolean defaultValue);

    /**
     * Finds a value associated with the given key. The value is
     * converted to an <tt>int</tt> if found.
     *
     * @param key the key to search.
     */
    int getAsInt(String key);

    /**
     * Finds a value associated with the given key. The value is
     * converted to an <tt>int</tt> if found. If not found then the
     * supplied <tt>defaultValue</tt> will be returned.
     *
     * @param key          the key to search.
     * @param defaultValue the value to be returned if the key is not found.
     */
    int getAsInt(String key, int defaultValue);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>long</tt> if found.
     *
     * @param key the key to search.
     */
    long getAsLong(String key);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>long</tt> if found. If not found then the
     * supplied <tt>defaultValue</tt> will be returned.
     *
     * @param key          the key to search.
     * @param defaultValue the value to be returned if the key is not found.
     */
    long getAsLong(String key, long defaultValue);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>float</tt> if found.
     *
     * @param key the key to search.
     */
    float getAsFloat(String key);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>float</tt> if found. If not found then the
     * supplied <tt>defaultValue</tt> will be returned.
     *
     * @param key          the key to search.
     * @param defaultValue the value to be returned if the key is not found.
     */
    float getAsFloat(String key, float defaultValue);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>double</tt> if found.
     *
     * @param key the key to search.
     */
    double getAsDouble(String key);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>double</tt> if found. If not found then the
     * supplied <tt>defaultValue</tt> will be returned.
     *
     * @param key          the key to search.
     * @param defaultValue the value to be returned if the key is not found.
     */
    double getAsDouble(String key, double defaultValue);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>String</tt> if found.
     *
     * @param key the key to search.
     */
    String getAsString(String key);

    /**
     * Finds a value associated with the given key. The value is
     * converted to a <tt>String</tt> if found. If not found then the
     * supplied <tt>defaultValue</tt> will be returned.
     *
     * @param key          the key to search.
     * @param defaultValue the value to be returned if the key is not found.
     */
    String getAsString(String key, String defaultValue);

    /**
     * /**
     * Finds a value associated with the given key. The value is
     * converted to type <tt>T</tt> if found using a {@code Converter}.
     *
     * @param key  the key to search.
     * @param type the type to be returned.
     */
    <T> T getConverted(String key, Class<T> type);

    /**
     * Finds a value associated with the given key. The value is
     * converted to type <tt>T</tt> if found using a {@code Converter}.
     * If not found then the supplied <tt>defaultValue</tt> will be returned.
     *
     * @param key          the key to search.
     * @param type         the type to be returned.
     * @param defaultValue the value to be returned if the key is not found.
     */
    <T> T getConverted(String key, Class<T> type, T defaultValue);

    /**
     * Inject properties and members annotated with {@code javax.application.Contextual}.
     *
     * @param instance the instance on which contextual members will be injected.
     * @param <T>      the type of the instance.
     *
     * @return the instance on which contextual members where injected.
     */
    <T> T injectMembers(T instance);
}

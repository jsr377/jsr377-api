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

/**
 * @author Andres Almiray
 */
public interface Configuration {
    /**
     * Searches for the key in this configuration.
     *
     * @param key the key to search.
     *
     * @return true if the context (or its parent) contains the given key, false otherwise.
     */
    boolean containsKey(String key);

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
}

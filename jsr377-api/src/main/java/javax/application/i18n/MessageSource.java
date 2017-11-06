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
package javax.application.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Andres Almiray
 */
public interface MessageSource {
    /**
     * Attempt to resolve a message.
     *
     * @param key Key to lookup, such as 'log4j.appenders.console'. Must not be {@code null}.
     *
     * @return The resolved message at the given key for the default locale.
     *
     * @throws NoSuchMessageException if no message is found.
     */
    String getMessage(String key) throws NoSuchMessageException;

    /**
     * Attempt to resolve a message.
     *
     * @param key    Key to lookup, such as 'log4j.appenders.console'. Must not be {@code null}.
     * @param locale Locale in which to lookup. Must not be {@code null}.
     *
     * @return The resolved message at the given key for the given locale.
     *
     * @throws NoSuchMessageException if no message is found.
     */
    String getMessage(String key, Locale locale) throws NoSuchMessageException;

    /**
     * Attempt to resolve a message.
     *
     * @param key  Key to lookup, such as 'log4j.appenders.console'. Must not be {@code null}.
     * @param args Arguments that will be filled in for params within the message (params look like "{0}" within a
     *             message, but this might differ between implementations), or {@code null} if none.
     *
     * @return The resolved message at the given key for the default locale.
     *
     * @throws NoSuchMessageException if no message is found.
     */
    String getMessage(String key, Object[] args) throws NoSuchMessageException;

    /**
     * Attempt to resolve a message.
     *
     * @param key    Key to lookup, such as 'log4j.appenders.console'. Must not be {@code null}.
     * @param args   Arguments that will be filled in for params within the message (params look like "{0}" within a
     *               message, but this might differ between implementations), or {@code null} if none.
     * @param locale Locale in which to lookup. Must not be {@code null}.
     *
     * @return The resolved message at the given key for the given locale.
     *
     * @throws NoSuchMessageException if no message is found.
     */
    String getMessage(String key, Object[] args, Locale locale) throws NoSuchMessageException;

    /**
     * Resolves a message. Returns {@code defaultMessage} if no message was found.
     *
     * @param key            Key to lookup, such as 'log4j.appenders.console'. Must not be {@code null}.
     * @param defaultMessage Message to return if the lookup fails.
     *
     * @return The resolved message at the given key for the default locale.
     */
    String getMessage(String key, String defaultMessage);

    /**
     * Resolves a message. Returns {@code defaultMessage} if no message was found.
     *
     * @param key            Key to lookup, such as 'log4j.appenders.console'. Must not be {@code null}.
     * @param locale         Locale in which to lookup. Must not be {@code null}.
     * @param defaultMessage Message to return if the lookup fails.
     *
     * @return The resolved message at the given key for the given locale.
     */
    String getMessage(String key, Locale locale, String defaultMessage);

    /**
     * Resolves a message. Returns {@code defaultMessage} if no message was found.
     *
     * @param key            Key to lookup, such as 'log4j.appenders.console'. Must not be {@code null}.
     * @param args           Arguments that will be filled in for params within the message (params look like "{0}"
     *                       within a message, but this might differ between implementations), or {@code null} if none.
     * @param defaultMessage Message to return if the lookup fails.
     *
     * @return The resolved message at the given key for the default locale.
     */
    String getMessage(String key, Object[] args, String defaultMessage);

    /**
     * Resolves a message. Returns {@code defaultMessage} if no message was found.
     *
     * @param key            Key to lookup, such as 'log4j.appenders.console'. Must not be {@code null}.
     * @param args           Arguments that will be filled in for params within the message (params look like "{0}"
     *                       within a message, but this might differ between implementations), or {@code null} if none.
     * @param locale         Locale in which to lookup. Must not be {@code null}.
     * @param defaultMessage Message to return if the lookup fails.
     *
     * @return The resolved message at the given key for the given locale.
     */
    String getMessage(String key, Object[] args, Locale locale, String defaultMessage);

    /**
     * Formats the given message using supplied args to substitute placeholders.
     *
     * @param message The message following a predefined format. Must not be {@code null}.
     * @param args    Arguments that will be filled in for params within the message (params look like "{0}"
     *                within a message, but this might differ between implementations), or {@code null} if none.
     *
     * @return the formatted message with all matching placeholders with their substituted values.
     */
    String formatMessage(String message, Object[] args);

    /**
     * Offers a view of this {@code MessageSource} as a {@code ResourceBundle}.
     *
     * @return a {@code ResourceBundle} containing the keys this {@code MessageSource}
     * can resolve. Never returns {@code null}.
     */
    ResourceBundle asResourceBundle();
}

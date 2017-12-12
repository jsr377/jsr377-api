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
package javax.application.resources;

import java.util.Locale;

/**
 * @author Andres Almiray
 */
public interface ResourceResolver {
    /**
     * Attempt to resolve the resource.
     *
     * @param key Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the default locale.
     *
     * @throws NoSuchResourceException if no resource is found.
     * @throws ClassCastException      if the resource is not of the expected type.
     */
    <T> T resolveResource(String key) throws NoSuchResourceException;

    /**
     * Attempt to resolve the resource.
     *
     * @param key    Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param locale Locale in which to lookup. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the given locale.
     *
     * @throws NoSuchResourceException if no resource is found.
     * @throws ClassCastException      if the resource is not of the expected type.
     */
    <T> T resolveResource(String key, Locale locale) throws NoSuchResourceException;

    /**
     * Attempt to resolve the resource.
     *
     * @param key  Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param args Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *             resource, but this might differ between implementations). Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the default locale.
     *
     * @throws NoSuchResourceException if no resource is found.
     * @throws ClassCastException      if the resource is not of the expected type.
     */
    <T> T resolveResource(String key, Object[] args) throws NoSuchResourceException;

    /**
     * Attempt to resolve the resource.
     *
     * @param key    Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param args   Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *               resource, but this might differ between implementations), or {@code null} if none.
     * @param locale Locale in which to lookup. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the given locale.
     *
     * @throws NoSuchResourceException if no resource is found.
     * @throws ClassCastException      if the resource is not of the expected type.
     */
    <T> T resolveResource(String key, Object[] args, Locale locale) throws NoSuchResourceException;

    /**
     * Attempt to resolve the resource. Return default resource if no resource was found.
     *
     * @param key          Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param defaultValue value to return if the lookup fails. May be {@code null}.
     *
     * @return The resolved resource at the given key for the default locale.
     *
     * @throws ClassCastException if the resource is not of the expected type.
     */
    <T> T resolveResource(String key, T defaultValue);

    /**
     * Attempt to resolve the resource. Return default resource if no resource was found.
     *
     * @param key          Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param locale       Locale in which to lookup. Must not be {@code null}.
     * @param defaultValue value to return if the lookup fails. May be {@code null}.
     *
     * @return The resolved resource at the given key for the given locale.
     *
     * @throws ClassCastException if the resource is not of the expected type.
     */
    <T> T resolveResource(String key, Locale locale, T defaultValue);

    /**
     * Attempt to resolve the resource. Return default resource if no resource was found.
     *
     * @param key          Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param args         Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *                     resource, but this might differ between implementations). Must not be {@code null}.
     * @param defaultValue value to return if the lookup fails. May be {@code null}.
     *
     * @return The resolved resource at the given key for the default locale.
     *
     * @throws ClassCastException if the resource is not of the expected type.
     */
    <T> T resolveResource(String key, Object[] args, T defaultValue);

    /**
     * Attempt to resolve the resource. Return default resource if no resource was found.
     *
     * @param key          Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param args         Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *                     resource, but this might differ between implementations). Must not be {@code null}.
     * @param locale       Locale in which to lookup. Must not be {@code null}.
     * @param defaultValue value to return if the lookup fails. May be {@code null}.
     *
     * @return The resolved resource at the given key for the given locale.
     *
     * @throws ClassCastException if the resource is not of the expected type.
     */
    <T> T resolveResource(String key, Object[] args, Locale locale, T defaultValue);

    /**
     * Attempt to resolve the resource. The value is converted to type {@code T} if found using a {@code Converter}.
     *
     * @param key  Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param type Type to be returned. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the default locale.
     *
     * @throws NoSuchResourceException                         if no resource is found.
     * @throws javax.application.converter.ConversionException if the resource could not be converted to the target type {@code T}.
     */
    <T> T resolveResourceConverted(String key, Class<T> type) throws NoSuchResourceException;

    /**
     * Attempt to resolve the resource. The value is converted to type {@code T} if found using a {@code Converter}.
     *
     * @param key    Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param locale Locale in which to lookup. Must not be {@code null}.
     * @param type   Type to be returned. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the given locale.
     *
     * @throws NoSuchResourceException                         if no resource is found.
     * @throws javax.application.converter.ConversionException if the resource could not be converted to the target type {@code T}.
     */
    <T> T resolveResourceConverted(String key, Locale locale, Class<T> type) throws NoSuchResourceException;

    /**
     * Attempt to resolve the resource. The value is converted to type {@code T} if found using a {@code Converter}.
     *
     * @param key  Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param args Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *             resource, but this might differ between implementations). Must not be {@code null}.
     * @param type Type to be returned. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the default locale.
     *
     * @throws NoSuchResourceException                         if no resource is found.
     * @throws javax.application.converter.ConversionException if the resource could not be converted to the target type {@code T}.
     */
    <T> T resolveResourceConverted(String key, Object[] args, Class<T> type) throws NoSuchResourceException;

    /**
     * Attempt to resolve the resource. The value is converted to type {@code T} if found using a {@code Converter}.
     *
     * @param key    Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param args   Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *               resource, but this might differ between implementations). Must not be {@code null}.
     * @param locale Locale in which to lookup. Must not be {@code null}.
     * @param type   Type to be returned. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the given locale.
     *
     * @throws NoSuchResourceException                         if no resource is found.
     * @throws javax.application.converter.ConversionException if the resource could not be converted to the target type {@code T}.
     */
    <T> T resolveResourceConverted(String key, Object[] args, Locale locale, Class<T> type) throws NoSuchResourceException;

    /**
     * Attempt to resolve the resource. Returns default value if no resource was found.
     * The value is converted to type {@code T} if found using a {@code Converter}.
     *
     * @param key          Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param defaultValue value to return if the lookup fails.
     * @param type         Type to be returned. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the default locale.
     *
     * @throws javax.application.converter.ConversionException if the resource could not be converted to the target type {@code T}.
     */
    <T> T resolveResourceConverted(String key, T defaultValue, Class<T> type);

    /**
     * Attempt to resolve the resource. Returns default value if no resource was found.
     * The value is converted to type {@code T} if found using a {@code Converter}.
     *
     * @param key          Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param locale       Locale in which to lookup. Must not be {@code null}.
     * @param defaultValue value to return if the lookup fails. May be {@code null}.
     * @param type         Type to be returned. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the given locale.
     *
     * @throws javax.application.converter.ConversionException if the resource could not be converted to the target type {@code T}.
     */
    <T> T resolveResourceConverted(String key, Locale locale, T defaultValue, Class<T> type);

    /**
     * Attempt to resolve the resource. Returns default value if no resource was found.
     * The value is converted to type {@code T} if found using a {@code Converter}.
     *
     * @param key          Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param args         Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *                     resource, but this might differ between implementations). Must not be {@code null}.
     * @param defaultValue value to return if the lookup fails. May be {@code null}.
     * @param type         Type to be returned. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the default locale.
     *
     * @throws javax.application.converter.ConversionException if the resource could not be converted to the target type {@code T}.
     */
    <T> T resolveResourceConverted(String key, Object[] args, T defaultValue, Class<T> type);

    /**
     * Attempt to resolve the resource. Returns default value if no resource was found.
     * The value is converted to type {@code T} if found using a {@code Converter}.
     *
     * @param key          Key to lookup, such as 'sample.SampleModel.icon'. Must not be {@code null}.
     * @param args         Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *                     resource, but this might differ between implementations). Must not be {@code null}.
     * @param locale       Locale in which to lookup. Must not be {@code null}.
     * @param defaultValue value to return if the lookup fails. May be {@code null}.
     * @param type         Type to be returned. Must not be {@code null}.
     *
     * @return The resolved resource at the given key for the given locale.
     *
     * @throws javax.application.converter.ConversionException if the resource could not be converted to the target type {@code T}.
     */
    <T> T resolveResourceConverted(String key, Object[] args, Locale locale, T defaultValue, Class<T> type);

    /**
     * Formats the given resource using supplied args to substitute placeholders.
     *
     * @param resource The resource following a predefined format. Must not be {@code null}.
     * @param args     Arguments that will be filled in for params within the resource (params look like "{0}" within a
     *                 resource, but this might differ between implementations). Must not be {@code null}.
     *
     * @return the formatted resource with all matching placeholders with their substituted values.
     */
    String formatResource(String resource, Object[] args);
}

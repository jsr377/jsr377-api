/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 the original author or authors.
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
package javax.application.converter;

import java.util.Objects;

/**
 * @author Andres Almiray
 */
public interface Converter<T> {
    /**
     * Converts the input argument to the given type {@code T}.
     *
     * @param value the value to be converted. May be {@code null}.
     *
     * @return the converted value. May be {@code null}.
     *
     * @throws ConversionException if the given value could not be converted to the target type.
     */
    T fromObject(Object value) throws ConversionException;

    /**
     * Converts the input argument to the a {@code String}.
     *
     * @param value the value to be converted. May be {@code null}.
     *
     * @return the {@code String} representation of the given value. May be {@code null}.
     *
     * @throws ConversionException if the given value could not be converted to a String.
     */
    default String toString(T value) throws ConversionException {
        return Objects.toString(value, null);
    }
}

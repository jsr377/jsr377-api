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
package javax.application.converter;

/**
 * The ConverterRegistry can be used to locate a converter for
 * any given type name. Converters must support the
 * {@code javax.application.converter.Converter} interface for converting a given value.
 * <p>
 *
 * @author Andres Almiray
 */
public interface ConverterRegistry {
    /**
     * Registers a converter class used to convert values of the given target class.
     * If the converter class is {@code null},
     * then any existing definition will be removed.
     * Thus this method can be used to cancel the registration.
     * The registration is canceled automatically
     * if either the target or converter class is unloaded.
     * <p>
     *
     * @param targetType     the class object of the type to be converted
     * @param converterClass the class object of the converter class
     */
    <T> void registerConverter(Class<T> targetType, Class<? extends Converter<T>> converterClass);

    /**
     * Unregisters converter class used to convert values of the given target class.
     *
     * @param targetType     the class object of the type to be converted
     * @param converterClass the class object of the converter class
     */
    <T> void unregisterConverter(Class<T> targetType, Class<? extends Converter<T>> converterClass);

    /**
     * Locates a value converter for a given target type.
     * <p>
     * If the input {@code type} is an Enum then an instance of {@code EnumPropertyEditor}
     * is returned with the {@code type} set as {@code enumType}.
     *
     * @param targetType The Class object for the type to be converter
     *
     * @return A converter object for the given target class.
     * The result is null if no suitable converter can be found.
     *
     * @see javax.application.converter.EnumConverter
     */
    <T> Converter<T> findConverter(Class<T> targetType);
}

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

/*
 * @author Andres Almiray
 */
public class ConversionException extends RuntimeException {
    private static final long serialVersionUID = -9122143348121235390L;

    private final transient Object value;
    private Class<?> type;

    public ConversionException(Object value) {
        this(value, (Exception) null);
    }

    public ConversionException(Object value, Class<?> type) {
        this(value, type, null);
    }

    public ConversionException(Object value, Class<?> type, Exception cause) {
        super("Can't convert '" + value + "' into " + type.getName(), cause);
        this.value = value;
        this.type = type;
    }

    public ConversionException(Object value, Exception cause) {
        super("Can't convert '" + value + "'", cause);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getType() {
        return type;
    }
}

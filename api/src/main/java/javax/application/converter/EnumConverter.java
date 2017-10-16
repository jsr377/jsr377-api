/*
 * Copyright 2008-2017 the original author or authors.
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
 * @author Andres Almiray
 */
@SuppressWarnings("rawtypes")
public class EnumConverter<T extends Enum<T>> extends AbstractConverter<T> {
    private Class<T> enumType;

    public Class<T> getEnumType() {
        return enumType;
    }

    public void setEnumType(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T fromObject(Object value) throws ConversionException {
        if (null == value) {
            return null;
        } else if (value instanceof CharSequence) {
            return handleAsString(String.valueOf(value));
        } else if (enumType.isAssignableFrom(value.getClass())) {
            return enumType.cast(value);
        } else {
            throw illegalValue(value, enumType);
        }
    }

    @Override
    public String toString(T value) {
        return value == null ? null : value.name();
    }

    protected T handleAsString(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        try {
            return Enum.valueOf(enumType, str);
        } catch (Exception e) {
            throw illegalValue(str, enumType, e);
        }
    }
}

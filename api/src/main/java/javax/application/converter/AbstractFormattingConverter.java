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

import javax.application.formatter.Formatter;
import javax.application.formatter.ParseException;

/**
 * @author Andres Almiray
 */
public abstract class AbstractFormattingConverter<T> extends AbstractConverter<T> implements FormattingConverter<T> {
    private String format;

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public T fromObject(Object value) throws ConversionException {
        if (value instanceof CharSequence) {
            String literal = String.valueOf(value);
            Formatter<T> formatter = resolveFormatter(format);

            if (formatter != null) {
                try {
                    return formatter.parse(literal);
                } catch (ParseException e) {
                    throw new ConversionException(value, e);
                }
            }
        }
        return convertFromObject(value);
    }

    protected abstract T convertFromObject(Object value) throws ConversionException;

    @Override
    public String toString(T value) throws ConversionException {
        if (null == value) {
            return null;
        }

        if (null == format) {
            return String.valueOf(value);
        }

        return getFormatted(value, format);
    }

    protected String getFormatted(T value, String format) throws ConversionException {
        Formatter<T> formatter = resolveFormatter(format);
        if (formatter != null) {
            return formatter.format(value);
        }
        return value != null ? value.toString() : null;
    }

    protected abstract Formatter<T> resolveFormatter(String format);
}

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

import javax.application.formatter.BooleanFormatter;
import javax.application.formatter.Formatter;

/**
 * @author Andres Almiray
 */
public class BooleanConverter extends AbstractPrimitiveConverter<Boolean> {
    @Override
    protected Boolean doConvertFromObject(Object value) throws ConversionException {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof CharSequence) {
            return convertFromString(String.valueOf(value).trim());
        }
        throw illegalValue(value, Boolean.class);
    }

    protected Boolean convertFromString(String str) {
        if (isBlank(str)) {
            if (isNullAccepted()) {
                return null;
            } else {
                Boolean defaultValue = getDefaultValue();
                if (null != defaultValue) {
                    return defaultValue;
                } else {
                    throw new ConversionException(getClass().getSimpleName() + " does not accept empty input");
                }
            }
        }
        return doConvertFromString(str);
    }

    protected Boolean doConvertFromString(String value) throws ConversionException {
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            throw illegalValue(value, Boolean.class, e);
        }
    }

    @Override
    protected Formatter<Boolean> resolveFormatter(String format) {
        return isBlank(format) ? null : BooleanFormatter.getInstance(format);
    }
}

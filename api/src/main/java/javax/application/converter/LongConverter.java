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
import javax.application.formatter.LongFormatter;

/**
 * @author Andres Almiray
 */
public class LongConverter extends AbstractPrimitiveNumberConverter<Long> {
    @Override
    protected Class<Long> getTypeClass() {
        return Long.class;
    }

    @Override
    protected Long convertFromNumber(Number value) {
        return value.longValue();
    }

    @Override
    protected Long doConvertFromString(String value) throws ConversionException {
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            throw illegalValue(value, Long.class, e);
        }
    }

    @Override
    protected Formatter<Long> resolveFormatter(String format) {
        return isBlank(format) ? null : new LongFormatter(format);
    }
}

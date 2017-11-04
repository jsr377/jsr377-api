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

import javax.application.formatter.ByteFormatter;
import javax.application.formatter.Formatter;

/**
 * @author Andres Almiray
 */
public class ByteConverter extends AbstractPrimitiveNumberConverter<Byte> {
    @Override
    protected Class<Byte> getTypeClass() {
        return Byte.class;
    }

    @Override
    protected Byte convertFromNumber(Number value) {
        return value.byteValue();
    }

    @Override
    protected Byte doConvertFromString(String value) throws ConversionException {
        try {
            return Byte.valueOf(value);
        } catch (Exception e) {
            throw illegalValue(value, Byte.class, e);
        }
    }

    @Override
    protected Formatter<Byte> resolveFormatter(String format) {
        return isBlank(format) ? null : new ByteFormatter(format);
    }
}

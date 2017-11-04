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

import javax.application.formatter.BigDecimalFormatter;
import javax.application.formatter.Formatter;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Andres Almiray
 */
public class BigDecimalConverter extends AbstractFormattingConverter<BigDecimal> implements NumberConverter<BigDecimal> {
    @Override
    protected BigDecimal convertFromObject(Object value) throws ConversionException {
        if (null == value) {
            return null;
        } else if (value instanceof Number) {
            return convertFromNumber((Number) value);
        } else if (value instanceof CharSequence) {
            return convertFromString(String.valueOf(value).trim());
        } else {
            throw illegalValue(value, BigDecimal.class);
        }
    }

    protected BigDecimal convertFromString(String str) {
        try {
            return isBlank(str) ? null : new BigDecimal(str);
        } catch (NumberFormatException e) {
            throw illegalValue(str, BigDecimal.class, e);
        }
    }

    protected BigDecimal convertFromNumber(Number number) {
        if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        } else if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else {
            return BigDecimal.valueOf(number.longValue());
        }
    }

    @Override
    protected Formatter<BigDecimal> resolveFormatter(String format) {
        return isBlank(format) ? null : new BigDecimalFormatter(format);
    }
}

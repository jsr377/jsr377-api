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

import javax.application.formatter.DoubleFormatter;
import javax.application.formatter.Formatter;

/**
 * @author Andres Almiray
 */
public class DoubleConverter extends AbstractPrimitiveNumberConverter<Double> {
    @Override
    protected Class<Double> getTypeClass() {
        return Double.class;
    }

    @Override
    protected Double convertFromNumber(Number value) {
        return value.doubleValue();
    }

    @Override
    protected Double doConvertFromString(String value) throws ConversionException {
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            throw illegalValue(value, Double.class, e);
        }
    }

    @Override
    protected Formatter<Double> resolveFormatter(String format) {
        return isBlank(format) ? null : new DoubleFormatter(format);
    }
}

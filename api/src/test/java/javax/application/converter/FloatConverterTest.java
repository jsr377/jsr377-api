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

import java.util.Arrays;
import java.util.Collections;

import static javax.application.formatter.AbstractNumberFormatter.PATTERN_CURRENCY;
import static javax.application.formatter.AbstractNumberFormatter.PATTERN_PERCENT;

/**
 * @author Andres Almiray
 */
public class FloatConverterTest extends AbstractNumberConverterTestCase<Float> {
    @Override
    protected NumberConverter<Float> createConverter() {
        return new FloatConverter();
    }

    @Override
    protected NumberConverter<Float> createConverter(String format) {
        NumberConverter<Float> converter = createConverter();
        converter.setFormat(format);
        return converter;
    }

    protected Object[] where_value_format_result() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{"", null, null},
            new Object[]{"0.1", null, 0.1f},
            new Object[]{"10%", PATTERN_PERCENT, 0.1f},
            new Object[]{"$0.10", PATTERN_CURRENCY, 0.1f},
            new Object[]{0.1d, null, 0.1f},
            new Object[]{0.1f, null, 0.1f}
        };
    }

    protected Object[] where_invalid_value() {
        return new Object[]{
            new Object[]{"garbage"},
            new Object[]{"1, 2, 3"},
            new Object[]{Collections.emptyList()},
            new Object[]{Collections.emptyMap()},
            new Object[]{Arrays.asList(1, 2, 3)},
            new Object[]{new Object()},
        };
    }
}

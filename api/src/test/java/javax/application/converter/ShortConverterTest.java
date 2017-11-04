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
public class ShortConverterTest extends AbstractNumberConverterTestCase<Short> {
    @Override
    protected NumberConverter<Short> createConverter() {
        return new ShortConverter();
    }

    @Override
    protected NumberConverter<Short> createConverter(String format) {
        NumberConverter<Short> converter = createConverter();
        converter.setFormat(format);
        return converter;
    }

    protected Object[] where_value_format_result() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{"", null, null},
            new Object[]{"1", null, (short) 1},
            new Object[]{"100%", PATTERN_PERCENT, (short) 1},
            new Object[]{"$1.00", PATTERN_CURRENCY, (short) 1},
            new Object[]{(short) 1, null, (short) 1},
            new Object[]{1, null, (short) 1}
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
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
package javax.application.formatter;

import static javax.application.formatter.AbstractNumberFormatter.PATTERN_CURRENCY;
import static javax.application.formatter.AbstractNumberFormatter.PATTERN_PERCENT;

/**
 * @author Andres Almiray
 */
public class ByteFormatterTest extends AbstractNumberFormatterTestCase<Byte> {
    @Override
    protected Formatter<Byte> createFormatter() {
        return new ByteFormatter();
    }

    @Override
    protected Formatter<Byte> createFormatter(String pattern) {
        return new ByteFormatter(pattern);
    }

    protected Object[] where_simple() {
        return new Object[]{
            new Object[]{(byte) 100, "100"}
        };
    }

    protected Object[] where_pattern() {
        return new Object[]{
            new Object[]{PATTERN_PERCENT, null, null},
            new Object[]{PATTERN_CURRENCY, (byte) 100, "$100.00"},
            new Object[]{PATTERN_PERCENT, (byte) 1, "100%"},
            new Object[]{null, (byte) 100, "100"},
            new Object[]{"", (byte) 100, "100"},
            new Object[]{"##.0", (byte) 20, "20.0"}
        };
    }

    protected Object[] where_parse_error() {
        return new Object[]{
            new Object[]{PATTERN_CURRENCY, "abc"},
            new Object[]{PATTERN_PERCENT, "abc"}
        };
    }

    protected Object[] where_invalid_pattern() {
        return new Object[]{
            new Object[]{";garbage*@%&"},
        };
    }
}

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

import java.math.BigInteger;

import static javax.application.formatter.AbstractNumberFormatter.PATTERN_CURRENCY;
import static javax.application.formatter.AbstractNumberFormatter.PATTERN_PERCENT;

/**
 * @author Andres Almiray
 */
public class BigIntegerFormatterTest extends AbstractNumberFormatterTestCase<BigInteger> {
    @Override
    protected Formatter<BigInteger> createFormatter() {
        return new BigIntegerFormatter();
    }

    @Override
    protected Formatter<BigInteger> createFormatter(String pattern) {
        return new BigIntegerFormatter(pattern);
    }

    protected Object[] where_simple() {
        return new Object[]{
            new Object[]{BigInteger.valueOf(100L), "100"}
        };
    }

    protected Object[] where_pattern() {
        return new Object[]{
            new Object[]{PATTERN_CURRENCY, null, null},
            new Object[]{PATTERN_PERCENT, null, null},
            new Object[]{PATTERN_CURRENCY, BigInteger.valueOf(100L), "$100.00"},
            new Object[]{PATTERN_PERCENT, BigInteger.ONE, "100%"},
            new Object[]{null, BigInteger.valueOf(100L), "100"},
            new Object[]{"", BigInteger.valueOf(100L), "100"},
            new Object[]{"##.0", BigInteger.valueOf(20L), "20.0"}
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

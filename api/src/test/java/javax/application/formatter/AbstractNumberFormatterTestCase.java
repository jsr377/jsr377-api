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

import junitparams.Parameters;
import org.junit.Test;

import javax.application.ConversionSupport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public abstract class AbstractNumberFormatterTestCase<T extends Number> extends ConversionSupport {
    protected abstract Formatter<T> createFormatter();

    protected abstract Formatter<T> createFormatter(String pattern);

    @Test
    @Parameters(method = "where_simple")
    public void valueProducesLiteral(T value, String literal) {
        // given:
        Formatter<T> formatter = createFormatter();

        // when:
        String str = formatter.format(value);
        T result = formatter.parse(str);

        // then:
        assertThat(str, equalTo(literal));
        assertThat(result, equalTo(value));
    }

    @Test
    @Parameters(method = "where_pattern")
    public void valueWithPatternProducesLiteral(String pattern, T value, String literal) {
        // given:
        Formatter<T> formatter = createFormatter(pattern);

        // when:
        String str = formatter.format(value);
        T result = formatter.parse(str);

        // then:
        assertThat(str, equalTo(literal));
        assertThat(result, equalTo(value));
    }

    @Test(expected = ParseException.class)
    @Parameters(method = "where_parse_error")
    public void parseErrorWithPatternAndLiteral(String pattern, String literal) {
        // given:
        Formatter<T> formatter = createFormatter(pattern);

        // when:
        formatter.parse(literal);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "where_invalid_pattern")
    public void createFormatterWithInvalidPattern(String pattern) {
        // expect:
        createFormatter(pattern);
    }
}

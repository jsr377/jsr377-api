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
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class LocalDateTimeFormatterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_literal")
    public void valueProducesLiteral(LocalDateTime value, String literal) {
        // given:
        LocalDateTimeFormatter formatter = new LocalDateTimeFormatter();

        // when:
        String str = formatter.format(value);
        LocalDateTime result = formatter.parse(str);

        // then:
        assertThat(str, equalTo(literal));
        assertThat(result, equalTo(value));
    }

    protected Object[] where_value_literal() {
        return new Object[]{
            new Object[]{LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0), "1970-01-01T00:00:00"}
        };
    }

    @Test
    @Parameters(method = "where_pattern_value_literal")
    public void valueWithPatternProducesLiteral(String pattern, LocalDateTime value, String literal) {
        // given:
        LocalDateTimeFormatter formatter = new LocalDateTimeFormatter(pattern);

        // when:
        String str = formatter.format(value);
        LocalDateTime result = formatter.parse(str);

        // then:
        assertThat(str, equalTo(literal));
        assertThat(result, equalTo(value));
    }

    protected Object[] where_pattern_value_literal() {
        return new Object[]{
            new Object[]{"yyyy-MM-dd HH:mm:ss", null, null},
            new Object[]{"yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0), "1970-01-01 00:00:00"}
        };
    }

    @Test(expected = ParseException.class)
    @Parameters(method = "where_parse_error")
    public void parseErrorWithPatternAndLiteral(String pattern, String literal) {
        // given:
        LocalDateTimeFormatter formatter = new LocalDateTimeFormatter(pattern);

        // when:
        formatter.parse(literal);
    }

    protected Object[] where_parse_error() {
        return new Object[]{
            new Object[]{"yyyy-MM-dd HH:mm:ss", "abc"}
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "where_invalid_pattern")
    public void createFormatterWithInvalidPattern(String pattern) {
        // expect:
        new LocalDateTimeFormatter(pattern);
    }

    protected Object[] where_invalid_pattern() {
        return new Object[]{
            new Object[]{";garbage*@%&"},
        };
    }
}

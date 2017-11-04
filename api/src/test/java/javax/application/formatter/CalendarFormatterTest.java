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
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class CalendarFormatterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_literal")
    public void valueProducesLiteral(Calendar value, String literal) {
        // given:
        CalendarFormatter formatter = new CalendarFormatter();

        // when:
        String str = formatter.format(value);
        Calendar result = formatter.parse(str);

        Calendar v1 = null != value ? clearTime(value) : value;
        Calendar v2 = null != result ? clearTime(result) : result;

        // then:
        assertThat(str, equalTo(literal));
        assertThat(v1, equalTo(v2));
    }

    protected Object[] where_value_literal() {
        return new Object[]{
            new Object[]{epochAsCalendar(), "1/1/70 12:00 AM"}
        };
    }

    @Test
    @Parameters(method = "where_pattern_value_literal")
    public void valueWithPatternProducesLiteral(String pattern, Calendar value, String literal) {
        // given:
        CalendarFormatter formatter = new CalendarFormatter(pattern);

        // when:
        String str = formatter.format(value);
        Calendar result = formatter.parse(str);

        Calendar v1 = null != value ? clearTime(value) : value;
        Calendar v2 = null != result ? clearTime(result) : result;

        // then:
        assertThat(pattern, equalTo(formatter.getPattern()));
        assertThat(str, equalTo(literal));
        assertThat(v1, equalTo(v2));
    }

    protected Object[] where_pattern_value_literal() {
        return new Object[]{
            new Object[]{"yyyy-MM-dd", null, null},
            new Object[]{"yyyy-MM-dd", epochAsCalendar(), "1970-01-01"}
        };
    }

    @Test(expected = ParseException.class)
    @Parameters(method = "where_parse_error")
    public void parseErrorWithPatternAndLiteral(String pattern, String literal) {
        // given:
        CalendarFormatter formatter = new CalendarFormatter(pattern);

        // when:
        formatter.parse(literal);
    }

    protected Object[] where_parse_error() {
        return new Object[]{
            new Object[]{"yyyy-MM-dd", "abc"}
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "where_invalid_pattern")
    public void createFormatterWithInvalidPattern(String pattern) {
        // expect:
        new CalendarFormatter(pattern);
    }

    protected Object[] where_invalid_pattern() {
        return new Object[]{
            new Object[]{";garbage*@%&"},
        };
    }
}

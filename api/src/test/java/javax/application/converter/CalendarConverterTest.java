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

import junitparams.Parameters;
import org.junit.Test;

import javax.application.ConversionSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class CalendarConverterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_format_result")
    public void valueWithFormatProducesResult(Object value, String format, Calendar result) {
        // given:
        CalendarConverter converter = new CalendarConverter();
        converter.setFormat(format);

        // when:
        Calendar output = converter.fromObject(value);

        Calendar v1 = null != output ? clearTime(output) : output;
        Calendar v2 = null != result ? clearTime(result) : result;

        // then:
        assertThat(v1, equalTo(v2));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        CalendarConverter converter = new CalendarConverter();

        // when:
        converter.fromObject(value);
    }

    protected Object[] where_value_format_result() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{"", null, null},
            new Object[]{"1/1/70 12:00 AM", null, epochAsCalendar()},
            new Object[]{"0", null, epochAsCalendar()},
            new Object[]{0, null, epochAsCalendar()},
            new Object[]{epochAsDate(), null, epochAsCalendar()},
            new Object[]{epochAsCalendar(), null, epochAsCalendar()},
            new Object[]{"", "yyyy-MM-dd HH:mm:ss", null},
            new Object[]{"1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", epochAsCalendar()},
            new Object[]{0, "yyyy-MM-dd HH:mm:ss", epochAsCalendar()},
            new Object[]{epochAsDate(), "yyyy-MM-dd HH:mm:ss", epochAsCalendar()},
            new Object[]{epochAsCalendar(), "yyyy-MM-dd HH:mm:ss", epochAsCalendar()},
            new Object[]{LocalDate.of(1970, 1, 1), null, epochAsCalendar()},
            new Object[]{LocalDateTime.of(1970, 1, 1, 0, 0, 0), null, epochAsCalendar()}
        };
    }

    protected Object[] where_invalid_value() {
        return new Object[]{
            new Object[]{"garbage"},
            new Object[]{Collections.emptyList()},
            new Object[]{Collections.emptyMap()},
            new Object[]{Arrays.asList(1, 2, 3)},
            new Object[]{new Object()},
        };
    }
}

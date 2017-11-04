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
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class DateConverterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_format_result")
    public void valueWithFormatProducesResult(Object value, String format, Date result) {
        // given:
        DateConverter converter = new DateConverter();
        converter.setFormat(format);

        // when:
        Date output = converter.fromObject(value);

        Date v1 = null != output ? clearTime(output) : output;
        Date v2 = null != result ? clearTime(result) : result;

        // then:
        assertThat(v1, equalTo(v2));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        DateConverter converter = new DateConverter();

        // when:
        converter.fromObject(value);
    }

    protected Object[] where_value_format_result() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{"", null, null},
            new Object[]{"1/1/70 12:00 AM", null, epochAsDate()},
            new Object[]{"0", null, epochAsDate()},
            new Object[]{0, null, epochAsDate()},
            new Object[]{epochAsDate(), null, epochAsDate()},
            new Object[]{epochAsCalendar(), null, epochAsDate()},
            new Object[]{"", "yyyy-MM-dd HH:mm:ss", null},
            new Object[]{"1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", epochAsDate()},
            new Object[]{0, "yyyy-MM-dd HH:mm:ss", epochAsDate()},
            new Object[]{epochAsDate(), "yyyy-MM-dd HH:mm:ss", epochAsDate()},
            new Object[]{epochAsCalendar(), "yyyy-MM-dd HH:mm:ss", epochAsDate()},
            new Object[]{LocalDate.of(1970, 1, 1), null, epochAsDate()},
            new Object[]{LocalDateTime.of(1970, 1, 1, 12, 13, 14), null, epochAsDate()}
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

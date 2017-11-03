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
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class LocalDateTimeConverterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_format_result")
    public void valueWithFormatProducesResult(Object value, String format, LocalDateTime result) {
        // given:
        LocalDateTimeConverter converter = new LocalDateTimeConverter();
        converter.setFormat(format);

        // when:
        LocalDateTime output = converter.fromObject(value);

        // then:
        assertThat(output, equalTo(result));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        LocalDateTimeConverter converter = new LocalDateTimeConverter();

        // when:
        converter.fromObject(value);
    }

    protected Object[] where_value_format_result() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{emptyList(), null, null},
            new Object[]{"", null, null},
            new Object[]{"1970-01-01T12:13:14", null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{0, null, LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)},
            new Object[]{epochAsDate(), null, LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)},
            new Object[]{epochAsCalendar(), null, LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)},
            new Object[]{asList(1970, 1, 1), null, LocalDateTime.of(1970, 1, 1, 0, 0, 0)},
            new Object[]{LocalDate.of(1970, 1, 1), null, LocalDateTime.of(1970, 1, 1, 0, 0, 0)},
            new Object[]{asList(1970, 1, 1, 12), null, LocalDateTime.of(1970, 1, 1, 12, 0, 0)},
            new Object[]{asList(1970, 1, 1, 12, 13), null, LocalDateTime.of(1970, 1, 1, 12, 13, 0)},
            new Object[]{asList(1970, 1, 1, 12, 13, 14), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{asList(1970, 1, 1, 12, 13, 14, 0), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14, 0)},
            new Object[]{asList("1970", "1", "1", "12", "13", "14"), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{LocalDateTime.of(1970, 1, 1, 12, 13, 14), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{LocalDateTime.of(1970, 1, 1, 12, 13, 14), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{"", "yyyy-MM-dd HH:mm:ss", null},
            new Object[]{"1970-01-01 12:13:14", "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{0, "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)},
            new Object[]{epochAsDate(), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)},
            new Object[]{epochAsCalendar(), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)},
            new Object[]{LocalDate.of(1970, 1, 1), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0)},
            new Object[]{asList(1970, 1, 1), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0)},
            new Object[]{asList(1970, 1, 1, 12), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 0, 0)},
            new Object[]{asList(1970, 1, 1, 12, 13), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 0)},
            new Object[]{asList(1970, 1, 1, 12, 13, 14), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{asList(1970, 1, 1, 12, 13, 14, 0), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14, 0)},
            new Object[]{asList("1970", "1", "1", "12", "13", "14"), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{LocalDateTime.of(1970, 1, 1, 12, 13, 14), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
            new Object[]{LocalDateTime.of(1970, 1, 1, 12, 13, 14), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)},
        };
    }

    protected Object[] where_invalid_value() {
        return new Object[]{
            new Object[]{"garbage"},
            new Object[]{Collections.emptyMap()},
            new Object[]{asList(1, 2)},
            new Object[]{asList(1, 2, 3, 4, 5, 6, 7, 8)},
            new Object[]{new Object()},
        };
    }
}

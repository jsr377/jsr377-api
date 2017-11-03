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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class LocalDateConverterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_format_result")
    public void valueWithFormatProducesResult(Object value, String format, LocalDate result) {
        // given:
        LocalDateConverter converter = new LocalDateConverter();
        converter.setFormat(format);

        // when:
        LocalDate output = converter.fromObject(value);

        // then:
        assertThat(output, equalTo(result));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        LocalDateConverter converter = new LocalDateConverter();

        // when:
        converter.fromObject(value);
    }

    protected Object[] where_value_format_result() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{"", null, null},
            new Object[]{"1970-01-01", null, LocalDate.of(1970, 1, 1)},
            new Object[]{0, null, LocalDate.of(1970, 1, 1)},
            new Object[]{epochAsDate(), null, LocalDate.of(1970, 1, 1)},
            new Object[]{epochAsCalendar(), null, LocalDate.of(1970, 1, 1)},
            new Object[]{Arrays.asList(1970, 1, 1), null, LocalDate.of(1970, 1, 1)},
            new Object[]{Arrays.asList("1970", "1", "1"), null, LocalDate.of(1970, 1, 1)},
            new Object[]{dateMap(1970, 1, 1), null, LocalDate.of(1970, 1, 1)},
            new Object[]{dateMap("1970", "1", "1"), null, LocalDate.of(1970, 1, 1)},
            new Object[]{LocalDate.of(1970, 1, 1), null, LocalDate.of(1970, 1, 1)},
            new Object[]{LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0), null, LocalDate.of(1970, 1, 1)},
            new Object[]{"", "yyyy-MM-dd", null},
            new Object[]{"1970-01-01", "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{0, "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{epochAsDate(), "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{epochAsCalendar(), "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{Arrays.asList(1970, 1, 1), "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{Arrays.asList("1970", "1", "1"), "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{dateMap(1970, 1, 1), "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{dateMap("1970", "1", "1"), "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{LocalDate.of(1970, 1, 1), "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0), "yyyy-MM-dd", LocalDate.of(1970, 1, 1)},
            new Object[]{Collections.emptyList(), null, null},
            new Object[]{Collections.emptyMap(), null, null}
        };
    }

    protected Object[] where_invalid_value() {
        return new Object[]{
            new Object[]{"garbage"},
            new Object[]{Arrays.asList(1, 2)},
            new Object[]{Arrays.asList(1, 2, 3, 4)},
            new Object[]{new Object()},
        };
    }
}

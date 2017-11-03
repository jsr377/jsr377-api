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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class LocalTimeConverterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_format_result")
    public void valueWithFormatProducesResult(Object value, String format, LocalTime result) {
        // given:
        LocalTimeConverter converter = new LocalTimeConverter();
        converter.setFormat(format);

        // when:
        LocalTime output = converter.fromObject(value);

        // then:
        assertThat(output, equalTo(result));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        LocalTimeConverter converter = new LocalTimeConverter();

        // when:
        converter.fromObject(value);
    }

    protected Object[] where_value_format_result() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{"", null, null},
            new Object[]{emptyList(), null, null},
            new Object[]{"01:02:03.400", null, LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{0, null, LocalTime.of(0, 0, 0, 0)},
            new Object[]{epochAsDate(), null, LocalTime.of(0, 0, 0, 0)},
            new Object[]{epochAsCalendar(), null, LocalTime.of(0, 0, 0, 0)},
            new Object[]{asList(1, 2, 3), null, LocalTime.of(1, 2, 3)},
            new Object[]{asList(1, 2, 3, 400000000), null, LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{asList("1", "2", "3", "400000000"), null, LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{LocalTime.of(1, 2, 3, 400000000), null, LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{LocalDateTime.of(1970, 1, 1, 1, 2, 3, 400000000), null, LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{"", "HH:mm:ss.SSS", null},
            new Object[]{"01:02:03.400", "HH:mm:ss.SSS", LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{0, "HH:mm:ss.SSS", LocalTime.of(0, 0, 0, 0)},
            new Object[]{epochAsDate(), "HH:mm:ss.SSS", LocalTime.of(0, 0, 0, 0)},
            new Object[]{epochAsCalendar(), "HH:mm:ss.SSS", LocalTime.of(0, 0, 0, 0)},
            new Object[]{asList(1, 2, 3), "HH:mm:ss.SSS", LocalTime.of(1, 2, 3, 0)},
            new Object[]{asList(1, 2, 3, 400000000), "HH:mm:ss.SSS", LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{asList("1", "2", "3", "400000000"), "HH:mm:ss.SSS", LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{LocalTime.of(1, 2, 3, 400000000), "HH:mm:ss.SSS", LocalTime.of(1, 2, 3, 400000000)},
            new Object[]{LocalDateTime.of(1970, 1, 1, 1, 2, 3, 400000000), "HH:mm:ss.SSS", LocalTime.of(1, 2, 3, 400000000)}

        };
    }

    protected Object[] where_invalid_value() {
        return new Object[]{
            new Object[]{"garbage"},
            new Object[]{Collections.emptyMap()},
            new Object[]{asList(1, 2)},
            new Object[]{asList(1, 2, 3, 4, 5)},
            new Object[]{new Object()},
        };
    }
}

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
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class BooleanConverterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_format_result")
    public void nullsafe_valueWithFormatProducesResult(Object value, String format, Boolean result) {
        // given:
        BooleanConverter converter = new BooleanConverter();
        converter.setFormat(format);
        converter.setNullAccepted(true);

        // when:
        Boolean output = converter.fromObject(value);

        // then:
        assertThat(output, equalTo(result));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        BooleanConverter converter = new BooleanConverter();

        // when:
        converter.fromObject(value);
    }

    protected Object[] where_value_format_result() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{"", null, null},
            new Object[]{"true", null, true},
            new Object[]{"false", null, false},
            new Object[]{true, null, true},
            new Object[]{false, null, false},
            new Object[]{"true", "boolean", true},
            new Object[]{"false", "boolean", false},
            new Object[]{"yes", "query", true},
            new Object[]{"no", "query", false},
            new Object[]{"on", "switch", true},
            new Object[]{"off", "switch", false}
        };
    }

    protected Object[] where_invalid_value() {
        return new Object[]{
            new Object[]{Collections.emptyList()},
            new Object[]{Collections.emptyMap()},
            new Object[]{Arrays.asList(1, 2, 3)},
            new Object[]{new Object()},
        };
    }
}

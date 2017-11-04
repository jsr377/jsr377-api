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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public abstract class AbstractNumberConverterTestCase<T extends Number> extends ConversionSupport {
    protected abstract NumberConverter<T> createConverter();

    protected abstract NumberConverter<T> createConverter(String format);

    @Test
    @Parameters(method = "where_value_format_result")
    public void nullsafe_valueWithFormatProducesResult(Object value, String format, T result) {
        // given:
        NumberConverter<T> converter = createConverter(format);
        if (converter instanceof PrimitiveConverter) {
            ((PrimitiveConverter) converter).setNullAccepted(true);
        }

        // when:
        T output = converter.fromObject(value);

        // then:
        assertThat(output, equalTo(result));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        NumberConverter<T> converter = createConverter();

        // when:
        converter.fromObject(value);
    }
}

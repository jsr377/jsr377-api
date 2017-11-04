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
package javax.application.converter.spi;

import junit.framework.AssertionFailedError;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.application.converter.BigDecimalConverter;
import javax.application.converter.BigIntegerConverter;
import javax.application.converter.BooleanConverter;
import javax.application.converter.ByteConverter;
import javax.application.converter.CalendarConverter;
import javax.application.converter.Converter;
import javax.application.converter.DateConverter;
import javax.application.converter.DoubleConverter;
import javax.application.converter.FileConverter;
import javax.application.converter.FloatConverter;
import javax.application.converter.IntegerConverter;
import javax.application.converter.LocalDateConverter;
import javax.application.converter.LocalDateTimeConverter;
import javax.application.converter.LocalTimeConverter;
import javax.application.converter.LocaleConverter;
import javax.application.converter.LongConverter;
import javax.application.converter.PathConverter;
import javax.application.converter.ShortConverter;
import javax.application.converter.StringConverter;
import javax.application.converter.URIConverter;
import javax.application.converter.URLConverter;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
@RunWith(JUnitParamsRunner.class)
public class ConverterProviderTest {
    @Test
    @Parameters(method = "where_types")
    @SuppressWarnings("rawtypes")
    public <T> void loadAndCheckConverterProvider(Class<T> targetType, Class<? extends Converter<T>> converterType) {
        // given:
        ServiceLoader<ConverterProvider> providers = ServiceLoader.load(ConverterProvider.class);

        // when:
        ConverterProvider converterProvider = StreamSupport.stream(providers.spliterator(), false)
            .filter(cp -> targetType.equals(cp.getTargetType()))
            .findFirst()
            .orElseThrow(() -> new AssertionFailedError("Did not find a ConverterProvider for target type " + targetType.getName()));

        // then:
        assertThat(converterProvider.getConverterType(), equalTo(converterType));
    }

    protected Object[] where_types() {
        return new Object[]{
            new Object[]{BigDecimal.class, BigDecimalConverter.class},
            new Object[]{BigInteger.class, BigIntegerConverter.class},
            new Object[]{Boolean.class, BooleanConverter.class},
            new Object[]{Byte.class, ByteConverter.class},
            new Object[]{Calendar.class, CalendarConverter.class},
            new Object[]{Date.class, DateConverter.class},
            new Object[]{Double.class, DoubleConverter.class},
            new Object[]{File.class, FileConverter.class},
            new Object[]{Float.class, FloatConverter.class},
            new Object[]{Integer.class, IntegerConverter.class},
            new Object[]{LocalDate.class, LocalDateConverter.class},
            new Object[]{LocalDateTime.class, LocalDateTimeConverter.class},
            new Object[]{LocalTime.class, LocalTimeConverter.class},
            new Object[]{Locale.class, LocaleConverter.class},
            new Object[]{Long.class, LongConverter.class},
            new Object[]{Path.class, PathConverter.class},
            new Object[]{Short.class, ShortConverter.class},
            new Object[]{String.class, StringConverter.class},
            new Object[]{URI.class, URIConverter.class},
            new Object[]{URL.class, URLConverter.class},
        };
    }
}

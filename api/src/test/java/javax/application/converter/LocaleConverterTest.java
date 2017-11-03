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
import java.util.Collections;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class LocaleConverterTest extends ConversionSupport {
    private static final Locale DE_CH_BASEL = new Locale("de", "CH", "Basel");

    @Test
    @Parameters(method = "where_bidirectional")
    public void bidirectionalConversion(Object value, Locale locale, String literal) {
        // given:
        LocaleConverter formatter = new LocaleConverter();

        // when:
        Locale lcl = formatter.fromObject(value);
        String str = formatter.toString(locale);

        // then:
        assertThat(str, equalTo(literal));
        assertThat(lcl, equalTo(locale));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid")
    public void checkInvalidConversion(Object value) {
        // given:
        LocaleConverter formatter = new LocaleConverter();

        // when:
        formatter.fromObject(value);
    }

    protected Object[] where_bidirectional() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{"", null, null},
            new Object[]{" ", null, null},
            new Object[]{Locale.ENGLISH, Locale.ENGLISH, "en"},
            new Object[]{"en", Locale.ENGLISH, "en"},
        };
    }

    protected Object[] where_invalid() {
        return new Object[]{
            new Object[]{1},
            new Object[]{new Object()},
            new Object[]{Collections.emptyList()},
            new Object[]{Collections.emptyMap()},
        };
    }
}

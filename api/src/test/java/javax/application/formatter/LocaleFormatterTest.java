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
import java.util.Locale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class LocaleFormatterTest extends ConversionSupport {
    private static final Locale DE_CH_BASEL = new Locale("de", "CH", "Basel");

    @Test
    @Parameters(method = "where_bidirectional")
    public void bidirectionalConversion(Locale locale, String literal) {
        // given:
        LocaleFormatter formatter = new LocaleFormatter();

        // when:
        String str = formatter.format(locale);
        Locale lcl = formatter.parse(literal);

        // then:
        assertThat(str, equalTo(literal));
        assertThat(lcl, equalTo(locale));
    }

    @Test
    @Parameters(method = "where_parse")
    public void checkParse(Locale locale, String literal) {
        // given:
        LocaleFormatter formatter = new LocaleFormatter();

        // when:
        Locale lcl = formatter.parse(literal);

        // then:
        assertThat(lcl, equalTo(locale));
    }

    protected Object[] where_bidirectional() {
        return new Object[]{
            new Object[]{null, null},
            new Object[]{Locale.ENGLISH, "en"},
            new Object[]{Locale.US, "en_US"},
            new Object[]{Locale.UK, "en_GB"},
            new Object[]{DE_CH_BASEL, "de_CH_Basel"}
        };
    }

    protected Object[] where_parse() {
        return new Object[]{
            new Object[]{null, null},
            new Object[]{null, ""},
            new Object[]{null, " "},
            new Object[]{Locale.ENGLISH, "en"},
            new Object[]{Locale.US, "en_US"},
            new Object[]{DE_CH_BASEL, "de_CH_Basel"},
            new Object[]{null, "de_CH_Basel_X"},
        };
    }
}

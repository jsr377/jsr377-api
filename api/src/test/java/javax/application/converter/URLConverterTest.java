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
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class URLConverterTest extends ConversionSupport {
    private static final URL DEFAULT_URL;

    static {
        try {
            DEFAULT_URL = new URL("http://localhost");
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    @Parameters(method = "where_value_result")
    public void valueProducesResult(Object value, URL result) {
        // given:
        URLConverter converter = new URLConverter();

        // when:
        URL output = converter.fromObject(value);

        // then:
        assertThat(output, equalTo(result));
    }

    @Test(expected = ConversionException.class)
    @Parameters(method = "where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        URLConverter converter = new URLConverter();

        // when:
        converter.fromObject(value);
    }

    protected Object[] where_value_result() throws Exception {
        return new Object[]{
            new Object[]{null, null},
            new Object[]{"", null},
            new Object[]{" ", null},
            new Object[]{"http://localhost", DEFAULT_URL},
            new Object[]{new File("/").getAbsoluteFile(), new File("/").getAbsoluteFile().toURI().toURL()},
            new Object[]{DEFAULT_URL, DEFAULT_URL},
        };
    }

    protected Object[] where_invalid_value() {
        return new Object[]{
            new Object[]{Collections.emptyList()},
            new Object[]{Collections.emptyMap()},
            new Object[]{new Object()},
            new Object[]{"garbage"},
        };
    }
}

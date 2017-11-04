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

import static javax.application.formatter.BooleanFormatter.PATTERN_BOOL;
import static javax.application.formatter.BooleanFormatter.PATTERN_QUERY;
import static javax.application.formatter.BooleanFormatter.PATTERN_SWITCH;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
public class BooleanFormatterTest extends ConversionSupport {
    @Test
    @Parameters(method = "where_value_literal")
    public void valueProducesLiteral(Boolean value, String literal) {
        // given:
        BooleanFormatter formatter = new BooleanFormatter();

        // when:
        String str = formatter.format(value);
        Boolean result = formatter.parse(str);

        // then:
        assertThat(str, equalTo(literal));
        assertThat(result, equalTo(value));
    }

    protected Object[] where_value_literal() {
        return new Object[]{
            new Object[]{false, "false"},
            new Object[]{true, "true"}
        };
    }

    @Test
    @Parameters(method = "where_parse_value_literal")
    public void parseValueProducesLiteral(Boolean value, String literal) {
        // expect:
        assertThat(BooleanFormatter.parseBoolean(literal), equalTo(value));
    }

    protected Object[] where_parse_value_literal() {
        return new Object[]{
            new Object[]{null, null},
            new Object[]{null, ""},
            new Object[]{false, "false"},
            new Object[]{true, "true"},
            new Object[]{false, "no"},
            new Object[]{true, "yes"},
            new Object[]{false, "off"},
            new Object[]{true, "on"}
        };
    }

    @Test
    @Parameters(method = "where_pattern_value_literal")
    public void valueWithPatternProducesLiteral(String pattern, Boolean value, String literal) {
        // given:
        BooleanFormatter formatter = BooleanFormatter.getInstance(pattern);

        // when:
        String str = formatter.format(value);
        Boolean result = formatter.parse(str);

        // then:
        assertThat(str, equalTo(literal));
        assertThat(result, equalTo(value));
        if (null != pattern) {
            assertThat(formatter.getPattern(), equalTo(pattern));
        }
    }

    protected Object[] where_pattern_value_literal() {
        return new Object[]{
            new Object[]{null, null, null},
            new Object[]{null, false, "false"},
            new Object[]{null, true, "true"},
            new Object[]{PATTERN_BOOL, null, null},
            new Object[]{PATTERN_BOOL, false, "false"},
            new Object[]{PATTERN_BOOL, true, "true"},
            new Object[]{PATTERN_QUERY, null, null},
            new Object[]{PATTERN_QUERY, false, "no"},
            new Object[]{PATTERN_QUERY, true, "yes"},
            new Object[]{PATTERN_SWITCH, null, null},
            new Object[]{PATTERN_SWITCH, false, "off"},
            new Object[]{PATTERN_SWITCH, true, "on"}
        };
    }

    @Test(expected = ParseException.class)
    @Parameters(method = "where_parse_error")
    public void parseErrorWithPatternAndLiteral(String pattern, String literal) {
        // given:
        BooleanFormatter formatter = BooleanFormatter.getInstance(pattern);

        // when:
        formatter.parse(literal);
    }

    protected Object[] where_parse_error() {
        return new Object[]{
            new Object[]{PATTERN_BOOL, "abc"},
            new Object[]{PATTERN_QUERY, "abc"},
            new Object[]{PATTERN_SWITCH, "abc"}
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "where_invalid_pattern")
    public void createFormatterWithInvalidPattern(String pattern) {
        // expect:
        BooleanFormatter.getInstance(pattern);
    }

    protected Object[] where_invalid_pattern() {
        return new Object[]{
            new Object[]{";garbage*@%&"},
        };
    }

    @Test(expected = ParseException.class)
    @Parameters(method = "where_invalid_literal")
    public void parseInvalidLiteral(String literal) {
        // expect:
        BooleanFormatter.parseBoolean(literal);
    }

    protected Object[] where_invalid_literal() {
        return new Object[]{
            new Object[]{";garbage*@%&"},
        };
    }
}

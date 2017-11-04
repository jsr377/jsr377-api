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

/**
 * @author Andres Almiray
 */
public class ShortFormatter extends AbstractNumberFormatter<Short> {
    public ShortFormatter() {
        this(null);
    }

    public ShortFormatter(String pattern) {
        super(pattern);
    }

    @Override
    public Short parse(String str) throws ParseException {
        if (isBlank(str)) { return null; }
        try {
            return numberFormat.parse(str).shortValue();
        } catch (java.text.ParseException e) {
            throw new ParseException(e);
        }
    }
}

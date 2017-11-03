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

import javax.application.formatter.Formatter;
import javax.application.formatter.LocalDateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.time.LocalDate.ofEpochDay;

/**
 * @author Andres Almiray
 */
public class LocalDateConverter extends AbstractFormattingConverter<LocalDate> {
    @Override
    protected LocalDate convertFromObject(Object value) throws ConversionException {
        if (null == value) {
            return null;
        } else if (value instanceof CharSequence) {
            return convertFromString(String.valueOf(value).trim());
        } else if (value instanceof LocalDate) {
            return (LocalDate) value;
        } else if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).toLocalDate();
        } else if (value instanceof Date) {
            return ofEpochDay(((Date) value).getTime());
        } else if (value instanceof Calendar) {
            return ofEpochDay(((Calendar) value).getTime().getTime());
        } else if (value instanceof Number) {
            return ofEpochDay(((Number) value).longValue());
        } else if (value instanceof List) {
            return convertFromList((List) value);
        } else if (value instanceof Map) {
            return convertFromMap((Map) value);
        } else {
            throw illegalValue(value, LocalDate.class);
        }
    }

    protected LocalDate convertFromString(String str) {
        if (isBlank(str)) {
            return null;
        }

        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException dtpe) {
            throw illegalValue(str, LocalDate.class, dtpe);
        }
    }

    @Override
    protected Formatter<LocalDate> resolveFormatter(String format) {
        return isBlank(format) ? null : new LocalDateFormatter(format);
    }

    protected LocalDate convertFromList(List<?> list) {
        if (list.isEmpty()) {
            return null;
        }

        List<Object> values = new ArrayList<>();
        values.addAll(list);
        if (values.size() != 3) {
            throw illegalValue(list, LocalDate.class);
        }

        for (int i = 0, valuesSize = values.size(); i < valuesSize; i++) {
            Object val = values.get(i);
            if (val instanceof Number) {
                values.set(i, parse((Number) val));
            } else if (val instanceof CharSequence) {
                values.set(i, parse(String.valueOf(val)));
            } else {
                throw illegalValue(list, LocalDate.class);
            }
        }

        return LocalDate.of(
            (Integer) values.get(0),
            (Integer) values.get(1),
            (Integer) values.get(2));
    }

    protected LocalDate convertFromMap(Map<?, ?> map) {
        if (map.isEmpty()) {
            return null;
        }

        int y = getMapValue(map, "year", 1970);
        int m = getMapValue(map, "month", 1);
        int d = getMapValue(map, "day", 1);
        return LocalDate.of(y, m, d);
    }

    protected int parse(String val) {
        try {
            return Integer.parseInt(val.trim());
        } catch (NumberFormatException e) {
            throw illegalValue(val, LocalDate.class, e);
        }
    }

    protected int parse(Number val) {
        return val.intValue();
    }

    protected int getMapValue(Map<?, ?> map, String key, int defaultValue) {
        Object val = map.get(key);
        if (null == val) { val = map.get(String.valueOf(key.charAt(0))); }
        if (null == val) {
            return defaultValue;
        } else if (val instanceof CharSequence) {
            return parse(String.valueOf(val));
        } else if (val instanceof Number) {
            return parse((Number) val);
        }
        throw illegalValue(map, LocalDate.class);
    }
}

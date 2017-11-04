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
import javax.application.formatter.LocalTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Andres Almiray
 */
public class LocalTimeConverter extends AbstractFormattingConverter<LocalTime> {

    @Override
    protected LocalTime convertFromObject(Object value) throws ConversionException {
        if (null == value) {
            return null;
        } else if (value instanceof CharSequence) {
            return convertFromString(String.valueOf(value).trim());
        } else if (value instanceof LocalTime) {
            return (LocalTime) value;
        } else if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).toLocalTime();
        } else if (value instanceof Date) {
            return convertFromDate((Date) value);
        } else if (value instanceof Calendar) {
            return convertFromCalendar((Calendar) value);
        } else if (value instanceof Number) {
            return convertFromDate(new Date(((Number) value).longValue()));
        } else if (value instanceof List) {
            return convertFromList((List) value);
        } else {
            throw illegalValue(value, LocalTime.class);
        }
    }

    protected LocalTime convertFromString(String str) {
        if (isBlank(str)) {
            return null;
        }

        try {
            return LocalTime.parse(str);
        } catch (DateTimeParseException dtpe) {
            throw illegalValue(str, LocalTime.class, dtpe);
        }
    }

    protected LocalTime convertFromDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return convertFromCalendar(c);
    }

    protected LocalTime convertFromCalendar(Calendar value) {
        int h = value.get(Calendar.HOUR);
        int i = value.get(Calendar.MINUTE);
        int s = value.get(Calendar.SECOND);
        int n = value.get(Calendar.MILLISECOND) * 1000;
        return LocalTime.of(h, i, s, n);
    }

    @Override
    protected Formatter<LocalTime> resolveFormatter(String format) {
        return isBlank(format) ? null : new LocalTimeFormatter(format);
    }

    protected LocalTime convertFromList(List<?> list) {
        if (list.isEmpty()) {
            return null;
        }

        List<Object> values = new ArrayList<>();
        values.addAll(list);
        switch (values.size()) {
            case 4:
                // ok
                break;
            case 3:
                values.add(0);
                break;
            default:
                throw illegalValue(list, LocalTime.class);
        }

        for (int i = 0, valuesSize = values.size(); i < valuesSize; i++) {
            Object val = values.get(i);
            if (val instanceof Number) {
                values.set(i, parse((Number) val));
            } else if (val instanceof CharSequence) {
                values.set(i, parse(String.valueOf(val)));
            } else {
                throw illegalValue(list, LocalTime.class);
            }
        }
        return LocalTime.of(
            (Integer) values.get(0),
            (Integer) values.get(1),
            (Integer) values.get(2),
            (Integer) values.get(3));
    }

    protected int parse(String val) {
        try {
            return Integer.parseInt(val.trim());
        } catch (NumberFormatException e) {
            throw illegalValue(val, LocalTime.class, e);
        }
    }

    protected int parse(Number val) {
        return val.intValue();
    }

}

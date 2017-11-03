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

import javax.application.formatter.CalendarFormatter;
import javax.application.formatter.Formatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Andres Almiray
 */
public class CalendarConverter extends AbstractFormattingConverter<Calendar> {
    @Override
    protected Calendar convertFromObject(Object value) throws ConversionException {
        if (null == value) {
            return null;
        } else if (value instanceof CharSequence) {
            return convertFromString(String.valueOf(value).trim());
        } else if (value instanceof LocalDate) {
            return convertFromLocalDate((LocalDate) value);
        } else if (value instanceof LocalDateTime) {
            return convertFromLocalDateTime(((LocalDateTime) value));
        } else if (value instanceof Calendar) {
            return (Calendar) value;
        } else if (value instanceof Date) {
            return convertFromDate((Date) value);
        } else if (value instanceof Number) {
            return convertFromNumber((Number) value);
        } else {
            throw illegalValue(value, Calendar.class);
        }
    }

    protected Calendar convertFromString(String str) {
        if (isBlank(str)) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new Date(Long.parseLong(str)));
            return c;
        } catch (NumberFormatException nfe) {
            // ignore, let's try parsing the date in a locale specific format
        }

        try {
            c.setTime(new SimpleDateFormat().parse(str));
            return c;
        } catch (ParseException e) {
            throw illegalValue(str, Calendar.class, e);
        }
    }

    protected Calendar convertFromNumber(Number value) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(value.longValue()));
        return c;
    }

    protected Calendar convertFromDate(Date value) {
        Calendar c = Calendar.getInstance();
        c.setTime(value);
        return c;
    }

    protected Calendar convertFromLocalDate(LocalDate value) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(value.toEpochDay()));
        return c;
    }

    protected Calendar convertFromLocalDateTime(LocalDateTime value) {
        LocalDate localDate = value.toLocalDate();
        LocalTime localTime = value.toLocalTime();

        Calendar c = Calendar.getInstance();
        c.set(
            localDate.getYear(),
            localDate.getMonthValue() - 1,
            localDate.getDayOfMonth(),
            localTime.getHour(),
            localTime.getMinute(),
            localTime.getSecond()
        );

        return c;
    }

    @Override
    protected Formatter<Calendar> resolveFormatter(String format) {
        return isBlank(format) ? null : new CalendarFormatter(format);
    }
}

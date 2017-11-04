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
package javax.application;

import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Andres Almiray
 */
@RunWith(JUnitParamsRunner.class)
public abstract class ConversionSupport {
    private Locale defaultLocale = Locale.getDefault();

    @Before
    public void setup() {
        Locale.setDefault(Locale.US);
    }

    @After
    public void cleanup() {
        Locale.setDefault(defaultLocale);
    }

    public static Date epochAsDate() {
        // Thu Jan 01 00:00:00 1970
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/GMT"));
        return clearTime(new Date(0));
    }

    public static Calendar epochAsCalendar() {
        // Thu Jan 01 00:00:00 1970
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/GMT"));
        Calendar c = Calendar.getInstance();
        c.setTime(epochAsDate());
        return c;
    }

    private static void clearTimeCommon(Calendar self) {
        self.set(Calendar.HOUR_OF_DAY, 0);
        self.clear(Calendar.MINUTE);
        self.clear(Calendar.SECOND);
        self.clear(Calendar.MILLISECOND);
    }

    public static Date clearTime(Date self) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(self);
        clearTimeCommon(calendar);
        self.setTime(calendar.getTime().getTime());
        return self;
    }

    public static Calendar clearTime(Calendar self) {
        clearTimeCommon(self);
        return self;
    }

    public static Map<String, Integer> dateMap(int y, int m, int d) {
        Map<String, Integer> map = new HashMap<>();
        map.put("y", y);
        map.put("m", m);
        map.put("d", d);
        return map;
    }

    public static Map<String, String> dateMap(String y, String m, String d) {
        Map<String, String> map = new HashMap<>();
        map.put("y", y);
        map.put("m", m);
        map.put("d", d);
        return map;
    }
}

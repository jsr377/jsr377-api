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
package javax.application.event;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Andres Almiray
 */
@Documented
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface EventHandler {
    /**
     * Defines filters for this event handler. All filters are evaluated before the handler is invoked, which
     * can only happens if all filters return {@code true}.
     */
    EventFilter[] filters() default {};

    /**
     * Event handlers should be invoked by priority, where higher numbers takes precedence over lower numbers,
     * in other words, event handlers should be sorted in descending order of priority.
     *
     * @return the priority for this event handler.
     */
    int priority() default 0;
}

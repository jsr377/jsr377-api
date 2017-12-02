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

/**
 * {@code ExceptionHandler} is responsible for handling exceptions and throwables in a centralized manner.
 *
 * @author Andres Almiray
 */
public interface ExceptionHandler {
    /**
     * Processes a {@code Throwable} thrown by application code but that was not caught earlier.
     * Implementors must guarantee that no further exceptions or throwables will be thrown
     * from this method.
     *
     * @param thread    the {@code Thread} on which the throwable was thrown. Must not be {@code null}.
     * @param throwable an uncaught throwable. Must not be {@code null}.
     */
    void handleUncaught(Thread thread, Throwable throwable);
}

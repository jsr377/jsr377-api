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
 * @author Andres Almiray
 */
public interface ShutdownHandler {
    /**
     * Asks this handler if the application's shutdown sequence can proceed or not.<p>
     * Returns {@code false} if the shutdown sequence must be aborted.
     *
     * @param application the current running application. Must not be {@code null}.
     *
     * @return {@code true} if the shutdown sequence can proceed, {@code false} otherwise
     */
    default boolean canShutdown(Application application) {
        return true;
    }

    /**
     * Called when the shutdown sequence continues.
     *
     * @param application the current running application. Must not be {@code null}.
     */
    void onShutdown(Application application);
}

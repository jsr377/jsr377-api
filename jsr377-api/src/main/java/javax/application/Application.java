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

import javax.application.configuration.Configuration;
import java.util.Locale;

/**
 * @author Andres Almiray
 */
public interface Application {
    /**
     * Lifecycle method. Signals the application to bootstrap itself and load its configuration.
     * {@code ApplicationPhase} should be set automatically to {@code ApplicationPhase.INITIALIZE}.
     */
    void initialize();

    /**
     * Lifecycle method. Signals the application to assemble its components/artifacts.
     * {@code ApplicationPhase} should be set automatically to {@code ApplicationPhase.STARTUP}.
     */
    void startup();

    /**
     * Lifecycle method. Signals the application to display its main entry point (Window).
     * {@code ApplicationPhase} should be set automatically to {@code ApplicationPhase.READY}, followed
     * immediately with {@code ApplicationPhase.MAIN} once the ready sequence has finished.
     */
    void ready();

    /**
     * Lifecycle method. Shutdowns the application gracefully.
     * {@code ApplicationPhase} should be set automatically to {@code ApplicationPhase.SHUTDOWN}.
     *
     * @return the exit code that may be sent to the underlying platform process as exit value. Never returns {@code null}.
     */
    ExitState shutdown();

    /**
     * Queries any available {@code ShutdownHandler}s do determine if the application can be shutdown.
     *
     * @return {@code true} if the shutdown sequence can proceed, (@code false} otherwise
     */
    boolean canShutdown();

    /**
     * Registers a {@code ShutdownHandler} on this application
     *
     * @param handler the shutdown handler to be registered. Must not be {@code null}.
     *                Duplicate values must be ignored.
     */
    void addShutdownHandler(ShutdownHandler handler);

    /**
     * Removes a {@code ShutdownHandler} from this application
     *
     * @param handler the shutdown handler to be removed. Must not be {@code null}.
     *                Duplicate values must be ignored.
     */
    void removeShutdownHandler(ShutdownHandler handler);

    /**
     * Retrieves the {@code Configuration} of this application.
     *
     * @return the {@code Configuration} used by this application. Never returns {@code null}.
     */
    Configuration getConfiguration();

    /**
     * Returns the current phase.
     *
     * @return returns the current {@code ApplicationPhase}. Never returns {@code null}.
     */
    ApplicationPhase getPhase();

    /**
     * Gets the application locale.
     *
     * @return the current Locale used by the application. Never returns {@code null}.
     */
    Locale getLocale();

    /**
     * Returns the arguments set on the command line (if any).<p>
     *
     * @return an array of command line arguments. Never returns {@code null}.
     */
    String[] getStartupArguments();
}

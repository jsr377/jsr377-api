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
package javax.application.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;

/**
 * @author Andres Almiray
 */
public interface ThreadingHandler {
    /**
     * True if the current thread is the UI thread.
     */
    boolean isUIThread();

    /**
     * Executes a code block asynchronously on the UI thread.
     *
     * @param runnable block of code that must be executed. Must not be {@code null}.
     */
    void executeInsideUIAsync(Runnable runnable);

    /**
     * Executes a code block synchronously on the UI thread.
     *
     * @param runnable block of code that must be executed. Must not be {@code null}.
     */
    void executeInsideUISync(Runnable runnable);

    /**
     * Executes a code block synchronously on the UI thread.
     *
     * @param callable block of code that must be executed. Must not be {@code null}.
     *
     * @return return value from the executed block. May be {@code null}.
     */
    <R> R executeInsideUISync(Callable<R> callable);

    /**
     * Executes a code block outside of the UI thread.
     * The {@code runnable} will be invoked on the same thread as the caller if the caller
     * is already outside the UI thread.
     *
     * @param runnable block of code that must be executed. Must not be {@code null}.
     */
    void executeOutsideUI(Runnable runnable);

    /**
     * Executes a code block on a background thread, always.
     * The {@code runnable} will be invoked on a different thread regardless of the thread
     * where the caller issued the call.
     *
     * @param runnable block of code that must be executed. Must not be {@code null}.
     */
    void executeOutsideUIAsync(Runnable runnable);

    /**
     * Executes a code block on a background thread, always.
     * The {@code callable} will be invoked on a different thread regardless of the thread
     * where the caller issued the call.
     *
     * @param callable block of code that must be executed. Must not be {@code null}.
     *
     * @return a {@code CompletionStage} that can be used to signal the resolution or rejection of the code block. Never returns {@code null}.
     */
    <R> CompletionStage<R> executeOutsideUIAsync(Callable<R> callable);

    /**
     * Executes a code block asynchronously on the UI thread.
     *
     * @param callable block of code that must be executed. Must not be {@code null}.
     *
     * @return a {@code CompletionStage} that can be used to signal the resolution or rejection of the code block. Never returns {@code null}.
     */
    <R> CompletionStage<R> executeInsideUIAsync(Callable<R> callable);
}

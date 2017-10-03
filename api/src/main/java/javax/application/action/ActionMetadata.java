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
package javax.application.action;

import java.lang.annotation.Annotation;

/**
 * @author Andres Almiray
 */
public interface ActionMetadata {
    /**
     * Returns the set of annotations attached to the method related to an action.
     *
     * @return a non-null array of annotations.
     */
    Annotation[] getAnnotations();

    /**
     * Returns the type associated with the method related to an action.
     *
     * @return a non-null type.
     */
    Class<?> getReturnType();

    /**
     * Returns the set of parameters of the method related to an action.
     *
     * @return a non-null array of parameters.
     */
    ActionParameter[] getParameters();

    /**
     * Returns the simple name for the action.
     * This value is computed from the name of the method associated with an action.
     * Example, invoking this method returns "{@code click}" given an action defined as follows:
     * <pre>
     *     package org.example;
     *
     *     public class SampleController {
     *         public class click(ActionEvent event) {
     *             ...
     *         }
     *     }
     * </pre>
     *
     * @return a non-null name.
     */
    String getActionName();

    /**
     * Returns the name of the aciton with the fully qualified class name of its owner as prefix.</p>
     * Example, invoking this method returns "{@code org.example.SampleController.click}" given an action defined as follows:
     * <pre>
     *     package org.example;
     *
     *     public class SampleController {
     *         public class click(ActionEvent event) {
     *             ...
     *         }
     *     }
     * </pre>
     * <p>
     *
     * @return a non-null name.
     */
    String getFullyQualifiedName();

    /**
     * Finds out if there are any contextual arguments defined in the method's arguments.
     *
     * @return {@code true} if any parameter is annotated with {@code Contextual}, {@code false} otherwise.
     */
    boolean hasContextualArgs();
}

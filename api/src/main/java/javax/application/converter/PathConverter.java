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

import java.io.File;
import java.net.URI;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Andres Almiray
 */
public class PathConverter extends AbstractConverter<Path> {
    @Override
    public Path fromObject(Object value) throws ConversionException {
        try {
            if (null == value) {
                return null;
            } else if (value instanceof CharSequence) {
                return convertFromString(String.valueOf(value).trim());
            } else if (value instanceof File) {
                return convertFromFile((File) value);
            } else if (value instanceof URI) {
                return convertFromURI((URI) value);
            } else if (value instanceof Path) {
                return (Path) value;
            }
        } catch (Exception e) {
            throw illegalValue(value, Path.class, e);
        }
        throw illegalValue(value, Path.class);
    }

    protected Path convertFromFile(File value) {
        return value.toPath();
    }

    protected Path convertFromURI(URI value) {
        return Paths.get(value);
    }

    protected Path convertFromString(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Paths.get(str);
        } catch (InvalidPathException e) {
            throw illegalValue(str, Path.class, e);
        }
    }
}

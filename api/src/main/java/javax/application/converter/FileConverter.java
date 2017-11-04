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
import java.nio.file.Path;

/**
 * @author Andres Almiray
 */
public class FileConverter extends AbstractConverter<File> {
    @Override
    public File fromObject(Object value) throws ConversionException {
        try {
            if (null == value) {
                return null;
            } else if (value instanceof CharSequence) {
                return convertFromString(String.valueOf(value).trim());
            } else if (value instanceof File) {
                return (File) value;
            } else if (value instanceof Path) {
                return handeAsPath((Path) value);
            }
        } catch (Exception e) {
            throw illegalValue(value, File.class, e);

        }
        throw illegalValue(value, File.class);
    }

    protected File handeAsPath(Path value) {
        return value.toFile();
    }

    protected File convertFromString(String str) {
        if (isBlank(str)) {
            return null;
        }
        return new File(str);
    }
}

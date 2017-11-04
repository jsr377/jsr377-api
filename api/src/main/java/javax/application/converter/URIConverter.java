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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

/**
 * @author Andres Almiray
 */
public class URIConverter extends AbstractConverter<URI> {
    @Override
    public URI fromObject(Object value) throws ConversionException {
        if (null == value) {
            return null;
        } else if (value instanceof CharSequence) {
            return convertFromString(String.valueOf(value).trim());
        } else if (value instanceof File) {
            return convertFromFile((File) value);
        } else if (value instanceof Path) {
            return convertFromPath((Path) value);
        } else if (value instanceof URL) {
            return convertFromURL((URL) value);
        } else if (value instanceof URI) {
            return (URI) value;
        } else {
            throw illegalValue(value, URI.class);
        }
    }

    protected URI convertFromURL(URL value) {
        try {
            return value.toURI();
        } catch (URISyntaxException e) {
            throw illegalValue(value, URI.class, e);
        }
    }

    protected URI convertFromPath(Path value) {
        return value.toUri();
    }

    protected URI convertFromString(String str) {
        if (isBlank(str)) {
            return null;
        }

        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            throw illegalValue(str, URI.class, e);
        }
    }

    protected URI convertFromFile(File file) {
        return file.toURI();
    }
}

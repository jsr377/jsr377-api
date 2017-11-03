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
package javax.application.converter.spi;

import javax.application.converter.Converter;
import javax.application.converter.FileConverter;
import java.io.File;

/**
 * @author Andres Almiray
 */
public class FileConverterProvider implements ConverterProvider<File> {
    @Override
    public Class<File> getTargetType() {
        return File.class;
    }

    @Override
    public Class<? extends Converter<File>> getConverterType() {
        return FileConverter.class;
    }
}

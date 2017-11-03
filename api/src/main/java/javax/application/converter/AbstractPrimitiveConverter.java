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

/**
 * @author Andres Almiray
 */
public abstract class AbstractPrimitiveConverter<T> extends AbstractFormattingConverter<T> implements PrimitiveConverter<T> {
    private T defaultValue;
    private boolean nullAccepted;

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean isNullAccepted() {
        return nullAccepted;
    }

    @Override
    public void setNullAccepted(boolean nullAccepted) {
        this.nullAccepted = nullAccepted;
    }

    @Override
    protected T convertFromObject(Object value) throws ConversionException {
        if (null == value) {
            if (isNullAccepted()) {
                return null;
            } else {
                T defaultValue = getDefaultValue();
                if (null != defaultValue) {
                    return defaultValue;
                } else {
                    throw new ConversionException(getClass().getSimpleName() + " does not accept null input");
                }
            }
        }

        return doConvertFromObject(value);
    }

    protected abstract T doConvertFromObject(Object value) throws ConversionException;

    @Override
    public String toString(T value) throws ConversionException {
        if (null == value) {
            if (isNullAccepted()) {
                return null;
            } else {
                T defaultValue = getDefaultValue();
                if (null != defaultValue) {
                    return super.toString(defaultValue);
                } else {
                    throw new ConversionException(getClass().getSimpleName() + " does not produce null output");
                }
            }
        }
        return super.toString(value);
    }
}

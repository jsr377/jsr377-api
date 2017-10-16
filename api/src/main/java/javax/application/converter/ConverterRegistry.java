package javax.application.converter;

/**
 * The ConverterRegistry can be used to locate a converter for
 * any given type name. Converters must support the
 * {@code javax.application.converter.Converter} interface for converting a given value.
 * <p>
 *
 * @author Andres Almiray
 */
public interface ConverterRegistry {
    /**
     * Registers a converter class used to convert values of the given target class.
     * If the converter class is {@code null},
     * then any existing definition will be removed.
     * Thus this method can be used to cancel the registration.
     * The registration is canceled automatically
     * if either the target or converter class is unloaded.
     * <p>
     *
     * @param targetType     the class object of the type to be converted
     * @param converterClass the class object of the converter class
     */
    <T> void registerConverter(Class<T> targetType, Class<? extends Converter<T>> converterClass);

    /**
     * Unregisters converter class used to convert values of the given target class.
     *
     * @param targetType     the class object of the type to be converted
     * @param converterClass the class object of the converter class
     */
    <T> void unregisterConverter(Class<T> targetType, Class<? extends Converter<T>> converterClass);

    /**
     * Locates a value converter for a given target type.
     * <p>
     * If the input {@code type} is an Enum then an instance of {@code EnumPropertyEditor}
     * is returned with the {@code type} set as {@code enumType}.
     *
     * @param targetType The Class object for the type to be converter
     *
     * @return A converter object for the given target class.
     * The result is null if no suitable converter can be found.
     *
     * @see javax.application.converter.EnumConverter
     */
    <T> Converter<T> findConverter(Class<T> targetType);
}

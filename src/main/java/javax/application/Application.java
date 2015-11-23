package javax.application;

import java.util.Locale;

public interface Application {

    void initialize();

    void startup();

    void ready();

    boolean shutdown();

    boolean canShutdown();

    void addShutdownHandler(ShutdownHandler handler);

    void removeShutdownHandler(ShutdownHandler handler);

    Configuration getConfiguration();

    ApplicationPhase getPhase();

    Locale getLocale();

    String[] getStartupArguments();

}

package javax.application;

import java.util.concurrent.Callable;

public interface ThreadingHandler {

    boolean isUIThread();

    void runInsideUIAsync(Runnable runnable);

    void runInsideUISync(Runnable runnable);

    <R> R runInsideUISync(Callable<R> callable);

    void runOutsideUI(Runnable runnable);

}

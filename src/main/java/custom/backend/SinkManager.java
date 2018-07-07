package custom.backend;

import java.util.Collection;

/**
 * Responsible for invoking the Sink lifecycle events and retrieving sink implementations.
 * The SinkManager should register the sink comms in zookeeper via ServiceDisovery
 */
public interface SinkManager {
    /**
     * Register, initialize and start up sink implementation.
     * This invokes the initialize() and onStart() sink methods
     */
    void register(final Sink sink);

    /**
     * Stop sink implementation
     * This invokes the onShutdown() sink method
     */
    void stop();

    Collection<Sink> getSinks();
    Sink getSink(final String name);
}

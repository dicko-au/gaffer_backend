package custom.backend;

import java.util.Collection;

/**
 * Responsible for service loading sink implementations
 */
public interface SinkLoader {
    void loadSinks();
    Collection<String> getLoadedSinks();
}

package custom.backend.spi;

import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceLoader;

public class SinkLoaderService implements SinkLoader {

    private static ServiceLoader<SinkManager> loader;

    @Override
    public void loadSinks() {
        loader = ServiceLoader.load(SinkManager.class);
    }

    @Override
    public Iterator<SinkManager> getLoadedSinks() {
        loader.reload();
        return loader.iterator();
    }
}
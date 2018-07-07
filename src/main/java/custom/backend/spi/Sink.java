package custom.backend.spi;

import java.util.Map;

public interface Sink {
    void initialize(final String uuid, final Map<String, String> configuration);

    void onStart();

    void onShutdown();

    String getName();

    IngestService getIngestService();

    QueryService getQueryService();
}

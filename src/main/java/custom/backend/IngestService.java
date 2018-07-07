package custom.backend;

import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

public interface IngestService {

    // TODO what is this error re Futures
    Future<Void> ingest(final String id, final String domain, final List<File> ingestFiles, final IngestContext ingestContext);
    void ingest(final String id, final String domain, final List<File> ingestFiles, final IngestContext ingestContext);

    Future<Boolean> cancel(final String id, final IngestContext ingestContext);
    Boolean cancel(final String id, final IngestContext ingestContext);
}

package custom.backend.gaffer;

import custom.backend.spi.IngestContext;
import custom.backend.spi.IngestService;

import java.io.File;
import java.util.List;

/**
 * Implements the core Gaffer ingest logic
 */
public class GafferIngestService implements IngestService {

    @Override
    public void ingest(String id, String domain, List<File> ingestFiles, IngestContext ingestContext) {

    }

    @Override
    public Boolean cancel(String id, IngestContext ingestContext) {
        return null;
    }
}

package custom.backend;

import custom.backend.stubs.Domain;
import custom.backend.stubs.LablelMetricRegistry;
import custom.backend.stubs.NestedDomain;
import custom.backend.stubs.QueryStatus;

public interface IngestContext {
    LablelMetricRegistry getMetricRegistry();

    Domain fetchDOmain(final String domainName);

    NestedDomain fetchNestedDomain(final String domainName);

    void event(final String queryId, final String message, final QueryStatus status);
}

package custom.backend.gaffer;

import custom.backend.spi.QueryContext;
import custom.backend.spi.QueryService;
import custom.backend.stubs.QueryRequest;
import custom.backend.stubs.ResultBatch;

/**
 * Implements the logic of Gaffer queries
 */
public class GafferQueryService implements QueryService {

    @Override
    public String create(QueryRequest queryRequest, QueryContext queryContext) {
        return null;
    }

    @Override
    public ResultBatch next(String queryId, QueryContext queryContext) {
        return null;
    }

    @Override
    public ResultBatch run(QueryRequest queryRequest, QueryContext queryContext) {
        return null;
    }
}

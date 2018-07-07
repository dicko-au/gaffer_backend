package custom.backend.spi;

import custom.backend.stubs.QueryRequest;
import custom.backend.stubs.ResultBatch;

public interface QueryService {
    // TODO Fix Futures
//    Future<String> create(final QueryRequest queryRequest, final QueryContext context);
    String create(final QueryRequest queryRequest, final QueryContext queryContext);

//    Future<ResultBatch> next(final String queryId, final QueryContext queryContext);
    ResultBatch next(final String queryId, final QueryContext queryContext);

//    Future<ResultBatch> run(final QueryRequest queryRequest, final QueryContext queryContext);
    ResultBatch run(final QueryRequest queryRequest, final QueryContext queryContext);
}

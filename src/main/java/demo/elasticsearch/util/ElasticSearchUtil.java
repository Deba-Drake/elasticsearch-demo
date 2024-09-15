package demo.elasticsearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;
import java.util.function.Supplier;

public class ElasticSearchUtil
{
    public static Supplier<Query> supplier()
    {
        return ()->Query.of(q->q.matchAll(matchAllQuery()));
    }

    public static MatchAllQuery matchAllQuery()
    {
        val matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }
}

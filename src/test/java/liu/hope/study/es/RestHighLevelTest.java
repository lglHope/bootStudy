package liu.hope.study.es;

import com.google.common.collect.Maps;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class RestHighLevelTest {

    RestHighLevelClient restHighLevelClient;

    {
        // 连接rest接口
        //参数 ：地址，端口，协议
        HttpHost httpHost = new HttpHost("127.0.0.1", 9200);
        //rest构建器
        RestClientBuilder builder = RestClient.builder(httpHost);
        //高级客户端对象
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    public void addDocumentTest() {
        // 封装请求对象
        IndexRequest indexRequest = new IndexRequest("user", "user", "3L");
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "xiaohong");
        map.put("age", 17);
        map.put("email", "nishidashabi.com");
        indexRequest.source(map);
        IndexResponse response = null;
        try {
            //获取执行结果
            response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            int status = response.status().getStatus();
            assert status == 0;
            System.out.println(status);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void findAll() {
        //封装查询请求
        SearchRequest searchRequest = new SearchRequest("user");
        //设置查询类型，可以不设置查询全部
//        searchRequest.searchType("user");
        //相当于query
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(matchAllQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits searchHits = response.getHits();
            System.out.println(searchHits.getTotalHits());
            SearchHit[] hits = searchHits.getHits();
            Stream.of(hits).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void findByAge() {
        SearchRequest searchRequest = new SearchRequest("user");
//        searchRequest.searchType("term");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("age", 17);
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits responseHits = searchResponse.getHits();
            System.out.println(responseHits.getTotalHits());
            Arrays.stream(responseHits.getHits()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void nativeQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        QueryBuilder queryBuilder = QueryBuilders.termQuery("age", 17);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(queryBuilder);
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name", "t"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("age").from(10).to(20));
        NativeSearchQuery nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(PageRequest.of(1,10))
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC))
                .withHighlightBuilder(new HighlightBuilder().field("name"))
                .build();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder
        searchRequest.source(searchSourceBuilder);
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    public static void main(String[] args) {
        RestHighLevelTest restHighLevelTest = new RestHighLevelTest();
//        restHighLevelTest.addDocumentTest();
//        restHighLevelTest.findAll();
        restHighLevelTest.findByAge();
    }

}

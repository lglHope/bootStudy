package liu.hope.study.es;


/**
 * 1.1。Spring Data Elasticsearch 4.0的新功能
 * 使用Spring 5.2。
 *
 * 升级到Elasticsearch 7.6.2。
 *
 * 弃用TransportClient用法。
 *
 * 实现大多数可用于索引映射的映射类型。
 *
 * 移除Jackson ObjectMapper，现在使用MappingElasticsearchConverter
 *
 * 清除*Operations接口中的API，对方法进行分组和重命名，以使其与Elasticsearch API匹配，弃用旧方法，并与其他Spring Data模块保持一致。
 *
 * 引入SearchHit<T>类以表示找到的文档以及该文档的相关结果元数据（即sortValues）。
 *
 * 引入SearchHits<T>类以表示整个搜索结果以及完整搜索结果的元数据（即max_score）。
 *
 * 引入SearchPage<T>类以表示包含SearchHits<T>实例的分页结果。
 *
 * GeoDistanceOrder能够根据地理距离进行分类的课程介绍
 *
 * 实施审核支持
 *
 * 生命周期实体回调的实现
 */
public class MyESTest {

}

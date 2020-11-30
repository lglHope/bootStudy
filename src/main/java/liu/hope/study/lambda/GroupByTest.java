package liu.hope.study.lambda;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class GroupByTest {
    static List<Map<String, String>> data = new ArrayList<>();

    static {
        for (int i = 0; i < 5; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", "name" + i);
            map.put("age", "age" + i);
            map.put("sex", i%2 == 0 ? "nan" : "nv");
            data.add(map);
        }
    }

    public static void main(String[] args) {
        // 单级分组
        Map<String, List<Map<String, String>>> sexList = data.stream().collect(Collectors.groupingBy(m -> m.get("sex")));
        System.out.println(JSONObject.toJSON(sexList).toString());
        // {"nv":[{"name":"name1","age":"age1","sex":"nv"},{"name":"name3","age":"age3","sex":"nv"}],
        // "nan":[{"name":"name0","age":"age0","sex":"nan"},{"name":"name2","age":"age2","sex":"nan"},{"name":"name4","age":"age4","sex":"nan"}]}

//        data.stream().collect(Collectors.groupingBy(m -> m.get("sex")), Collectors.groupingBy(item -> {
//            if (Objects.equals("nan", item.get("")))
//        }))
        // 多级分组
        Map<String, Map<String, List<Map<String, String>>>> collect = data.stream().collect(Collectors.groupingBy(m -> m.get("sex"), Collectors.groupingBy(item -> {
            if (Objects.equals("nan", item.get("sex"))) {
                return "hate";
            } else {
                return "love";
            }
        })));
        System.out.println(JSONObject.toJSON(collect).toString());
        // {"nv":{"love":[{"name":"name1","age":"age1","sex":"nv"},{"name":"name3","age":"age3","sex":"nv"}]},
        // "nan":{"hate":[{"name":"name0","age":"age0","sex":"nan"},{"name":"name2","age":"age2","sex":"nan"},{"name":"name4","age":"age4","sex":"nan"}]}}


        // 按子组收集数据
        // 求总数
        Map<String, Long> collect1 = data.stream().collect(Collectors.groupingBy(m -> m.get("sex"), Collectors.counting()));
        System.out.println(JSONObject.toJSON(collect1).toString());
        // {"nv":2,"nan":3}

        // 求和
        Map<String, Long> collect2 = data.stream().collect(Collectors.groupingBy(m -> m.get("sex"), Collectors.summingLong(item -> item.get("age").length())));
        System.out.println(JSONObject.toJSON(collect2).toString());
        // {"nv":8,"nan":12}

        // 把收集器的结果转换为另一种类型
        // Map<String, Product> prodMap = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Product::getNum)), Optional::get)));
        //
        ////{"啤酒":{"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15},"零食":{"category":"零食","id":3,"name":"月饼","num":3,"price":30}}

        // 联合其他收集器
        // Map<String, Set<String>> prodMap = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toSet())));
        //
        ////{"啤酒":["青岛啤酒","百威啤酒"],"零食":["面包","饼干","月饼"]}
    }
}

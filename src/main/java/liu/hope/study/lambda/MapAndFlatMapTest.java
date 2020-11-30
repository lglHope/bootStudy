package liu.hope.study.lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapAndFlatMapTest {

    static List<Map<String, Object>> data = new ArrayList<>();

    static {
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "name" + i);
            map.put("age", 17 + i);
            map.put("sex", i % 2 == 0 ? "nan" : "nv");
            data.add(map);
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<Map<String, Object>>> sexList = data.stream().collect(Collectors.groupingBy(m -> Integer.valueOf(m.get("age").toString())));
        List<Integer> ages = Lists.newArrayList(17, 18, 16, 19);
        Stream<List<Map<String, Object>>> listStream = ages.stream().map(sexList::get).filter(Objects::nonNull);
        System.out.println(listStream.mapToInt(l -> l.stream().mapToInt(m -> Integer.parseInt(m.get("age").toString())).sum()).sum());


        Stream<Map<String, Object>> mapStream = ages.stream().map(sexList::get).filter(Objects::nonNull).flatMap(Collection::stream);
        System.out.println(mapStream.mapToInt(m -> Integer.parseInt(m.get("age").toString())).sum());

    }
}

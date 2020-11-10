package liu.hope.study.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

public class GuavaMaps {


    public static void main(String[] args) {
        Map<String, Integer> maps = Maps.newHashMapWithExpectedSize(10);
        maps.put("a", 1);
        maps.put("b", 2);
        maps.put("c", 3);
        maps.put("d", 4);
        ImmutableList<Integer> immutableList = ImmutableList.of(1,2,3,4);
        ImmutableMap<String, Integer> immutableMap = ImmutableMap.copyOf(maps);
        String concat = Joiner.on(",").join(maps.entrySet());
        System.out.println(concat);
    }
}

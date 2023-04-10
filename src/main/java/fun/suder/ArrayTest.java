import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <span>Form File</span>
 * <p>Description</p>
 * <p>Copyright: Copyright (c) 2022 版权</p>
 * <p>Company:QQ 752340543</p>
 *
 * @author topsuder
 * @version v1.0.0
 * @DATE 2023/4/10-17:18
 * @Description
 * @see PACKAGE_NAME arrayDemo
 */
public class ArrayTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a,b,c");
        list.add("b,d");
        list.add("b,e,f");
        list.add("g,h");
        final List<String> merge = merge(list);
        System.out.println(merge);

    }
    public static List<String> merge(List<String> list) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String s : list) {
            String[] elements = s.split(",");
            for (String element : elements) {
                Set<String> set = map.computeIfAbsent(element, k -> new HashSet<>());
                set.addAll(Arrays.asList(elements));
            }
        }

        Map<String, Set<String>> merged = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Set<String> value = entry.getValue();

            if (value.size() > 1) {
                Set<String> mergedSet = new HashSet<>();
                for (String s : value) {
                    mergedSet.addAll(map.get(s));
                }
                merged.put(key, mergedSet);
            }
        }

        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String s : list) {
            String[] elements = s.split(",");
            Set<String> intersection = new HashSet<>();
            for (String element : elements) {
                Set<String> set = merged.get(element);
                if (set != null) {
                    intersection.addAll(set);
                }
            }
            intersection.retainAll(new HashSet<>(Arrays.asList(elements)));

            if (!intersection.isEmpty()) {
                List<String> mergeList = new ArrayList<>(intersection);
                mergeList.removeAll(visited);
                if (!mergeList.isEmpty()) {
                    visited.addAll(mergeList);
                    mergeList.add(s.replace(",", ""));
                    result.add(String.join(",", mergeList));
                }
            } else if (!visited.contains(s)) {
                visited.add(s);
                result.add(s);
            }
        }

        return result;
    }

}

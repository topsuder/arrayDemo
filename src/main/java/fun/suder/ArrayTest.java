package fun.suder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
 */
public class ArrayTest {
    public static void main(String[] args) {
        String[][] arrays = {{"a", "b", "c"},
                {"b", "d"},
                {"b", "g", "h"},
                {"y", "p"},
                {"s", "z"},
                {"z", "q"}};
        List<List<String>> mergeList = mergeArraysToList(arrays);
        System.out.println(mergeList);
    }

    /**
     * 将二维字符串数组合并成一个列表
     *
     * @param arrays 二维字符串数组
     * @return 合并后的列表
     */
    private static List<List<String>> mergeArraysToList(String[][] arrays) {
        List<List<String>> list = Arrays.stream(arrays)
                .map(Arrays::asList)
                .collect(Collectors.toList());

        List<Set<String>> mergedSets = new ArrayList<>();
        for (List<String> array : list) {
            Set<String> set = new HashSet<>(array);

            if (isSetAlreadyMerged(mergedSets, set)) {
                continue;
            }

            Set<String> mergedSet = new HashSet<>(set);
            for (Set<String> otherSet : mergedSets) {
                if (!Collections.disjoint(mergedSet, otherSet)) {
                    mergedSet.addAll(otherSet);
                }
            }
            removeMergedSetsContainingAll(mergedSets, mergedSet);
            mergedSets.add(mergedSet);
        }

        return mergedSets.stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }

    /**
     * 判断一个集合是否已经合并过了
     *
     * @param mergedSets 已合并的集合列表
     * @param set        待判断的集合
     * @return 如果已经合并过了返回 true，否则返回 false
     */
    private static boolean isSetAlreadyMerged(List<Set<String>> mergedSets, Set<String> set) {
        return mergedSets.stream()
                .anyMatch(mergedSet -> mergedSet.containsAll(set));
    }

    /**
     * 移除所有包含在给定集合中的已合并集合
     *
     * @param mergedSets 已合并的集合列表
     * @param set        给定集合
     */
    private static void removeMergedSetsContainingAll(List<Set<String>> mergedSets, Set<String> set) {
        mergedSets.removeIf(otherSet -> set.containsAll(otherSet));
    }
}

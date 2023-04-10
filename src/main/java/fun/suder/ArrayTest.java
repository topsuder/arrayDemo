package fun.suder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
 */
public class ArrayTest {
    public static void main(String[] args) {
        String[][] arrays =
                {{"a", "b", "c"}
                        , {"b", "d"}
                        , {"b", "g", "h"}
                        , {"y", "p"}
                        , {"s", "z"}
                        , {"z", "q"}};
        //转为List<List<String>>
        List<List<String>> list = new ArrayList<>();
        for (String[] array : arrays) {
            list.add(Arrays.asList(array));
        }

        List<List<String>> mergeList = new ArrayList<>();

        for (List<String> array : list) {
            List<String> jl = array;

            boolean stopFlag = false;
            for (List<String> strings : mergeList) {
                if (CollUtil.isNotEmpty(CollUtil.intersection(strings, jl))){
                    stopFlag = true;
                    break;

                }
            }
            if (stopFlag){
                continue;
            }


            for (List<String> brray : list) {


                boolean flag = false;
                for (String s : brray) {
                    if (CollUtil.contains(jl, s)) {
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    jl = (List<String>) CollUtil.union(jl, brray);
                }
            }
            mergeList.add(jl);
        }
        System.out.println(mergeList);
    }
}

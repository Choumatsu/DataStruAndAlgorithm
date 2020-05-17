package com.hcs.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgolrithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();

        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();

        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();

        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();

        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();

        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        HashSet<String> allAreas = new HashSet<>();//存放所有地区

        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        ArrayList<String> selects = new ArrayList<>();

        HashSet<String> tempSet = new HashSet<>();

        String maxKey = null;

        while (allAreas.size() != 0) {
            maxKey = null;
            //遍历broadcasts,找到maxKey
            for (String key : broadcasts.keySet()) {
                tempSet.clear();

                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求tempSet和allAreas集合的交集，交集赋给tempSet
                tempSet.retainAll(allAreas);

                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }

            if (maxKey!=null){
                selects.add(maxKey);

                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println(selects);


    }


}

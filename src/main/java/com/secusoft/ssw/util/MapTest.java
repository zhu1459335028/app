package com.secusoft.ssw.util;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapTest {

    public static void main(String[] args) {
        //这里自定义一个需要排序的map集合
//        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
//        map.put("1", new BigDecimal(1.5));
//        map.put("2", new BigDecimal(2.8));
//        map.put("3", new BigDecimal(3.6));
//        map.put("4", new BigDecimal(15.6));
//        map.put("5", new BigDecimal(-15));
//        map.put("6", new BigDecimal(0.0));
//        map.put("7", new BigDecimal(-1226));
//        int size = map.size();
//        // 通过map.entrySet()将map转换为"1.B.1.e=78"形式的list集合
//        List<Map.Entry<String, BigDecimal>> list = new ArrayList<Map.Entry<String, BigDecimal>>(size);
//        list.addAll(map.entrySet());
//        // 通过Collections.sort()排序
//        ListMapSort(list);
//        for (Map.Entry<String, BigDecimal> entry : list) {
//            // 得到排序后的键值
//
//            System.out.println(entry.getKey()+"-------"+entry.getValue());
//        }
//        List<Map.Entry<String, BigDecimal>> list2=new ArrayList<Map.Entry<String, BigDecimal>>(map.entrySet());
//        Collections.sort(list2,(o1,o2)->(o1.getValue().compareTo(o2.getValue())));
//
//        System.out.println(list.get(0).getKey()+"value"+list.get(0).getValue());
//        System.out.println(list2.get(0).getKey()+"value"+list2.get(0).getValue());

        Date day=new Date();
        DateUtils.DateToString(day,"yyyy-MM-dd");
        System.out.println(day);

        List<Date> days = getLastDays(1);
        for(Date date:days){
            System.out.println(date);
        }


    }





    private static List<Date> getLastDays(int days){
        List<Date> dates = new ArrayList<>();
        for(int i=0;i<days;i++){
            dates.add(DateUtils.subDay(new Date(),days-i-1));
        }
        return dates;
    }








    private static void ListMapSort(List<Map.Entry<String, BigDecimal>> list) {
        Collections.sort(list, new Comparator<Map.Entry<String, BigDecimal>>() {
            public int compare(Map.Entry<String, BigDecimal> o1, Map.Entry<String, BigDecimal> o2) {
                // compareTo方法 (x < y) ? -1 : ((x == y) ? 0 : 1)
                return o1.getValue().compareTo(o2.getValue());
            }
        });
    }


}

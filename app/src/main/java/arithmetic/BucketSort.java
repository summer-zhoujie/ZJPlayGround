package arithmetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BucketSort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);

        for (int j = 0; j < 10; j++) {
            Utils.myShuffle(list);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append(list.get(i) + ",");
            }
            System.out.println("zhoujie unsort = " + stringBuilder.toString());
            List<Integer> sorted = bucketSort(list);
            StringBuilder stringBuilder_sorted = new StringBuilder();
            for (int i = 0; i < sorted.size(); i++) {
                stringBuilder_sorted.append(sorted.get(i) + ",");
            }
            System.out.println("zhoujie sort = " + stringBuilder_sorted.toString());
        }
    }

    private static List<Integer> bucketSort(List<Integer> list) {

        if (list == null || list.size() <= 1) {
            return list;
        }


        // 计算分桶
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Integer integer : list) {
            max = Math.max(max, integer);
            min = Math.min(min, integer);
        }

        int bucketSzie = 5;
        int bucketNum = (max - min) / bucketSzie + 1;

        List<Integer>[] buckects = new List[bucketNum];
        //分桶
        for (Integer integer : list) {
            int buckectsIndex = (integer - min) / bucketSzie;

            if (buckects[buckectsIndex] == null) {
                buckects[buckectsIndex] = new ArrayList<>();
            }
            buckects[buckectsIndex].add(integer);
        }

        // 排序
        for (List<Integer> buckect : buckects) {
            insertSort(buckect);
        }

        // 回填
        list.clear();
        for (List<Integer> buckect : buckects) {
            if (buckect != null) {
                for (Integer integer : buckect) {
                    list.add(integer);
                }
            }
        }
        return list;
    }

    private static void insertSort(List<Integer> buckect) {

        if (buckect == null || buckect.size() <= 1) {
            return;
        }

        int[] arr = new int[buckect.size()];
        int arrInitIndex = 0;
        Iterator<Integer> iterator = buckect.iterator();
        while (iterator.hasNext()) {
            arr[arrInitIndex++] = iterator.next();
        }

        for (int i = 1; i < arr.length; i++) {
            int compareIndex = i - 1;
            int pivotValue = arr[i];
            while (compareIndex >= 0 && pivotValue < arr[compareIndex]) {
                arr[compareIndex + 1] = arr[compareIndex];
                compareIndex--;
            }
            arr[++compareIndex] = pivotValue;
        }

        buckect.clear();
        for (int i : arr) {
            buckect.add(i);
        }
    }
}

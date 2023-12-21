package arithmetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CountingSort {
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
            List<Integer> sorted = countingSort(list);
            StringBuilder stringBuilder_sorted = new StringBuilder();
            for (int i = 0; i < sorted.size(); i++) {
                stringBuilder_sorted.append(sorted.get(i) + ",");
            }
            System.out.println("zhoujie sort = " + stringBuilder_sorted.toString());
        }
    }

    private static List<Integer> countingSort(List<Integer> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }
        int[] arr = new int[list.size()];
        int arrInitIndex = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            arr[arrInitIndex++] = iterator.next();
        }

        // 拿大小
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            min = Math.min(i, min);
            max = Math.max(i, max);
        }

        int lengthK = max - min + 1;
        int[] arrK = new int[lengthK];

        // 填值
        for (int value : arr) {
            int indexOfK = value - min;
            arrK[indexOfK] += 1;
        }

        // 回填
        int arrIndexOfBackFill = 0;
        for (int indexOfK = 0; indexOfK < arrK.length; indexOfK++) {
            while (arrK[indexOfK] > 0) {
                int value = indexOfK + min;
                arr[arrIndexOfBackFill] = value;
                arrIndexOfBackFill++;
                arrK[indexOfK] -= 1;
            }
        }

        list.clear();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}

package arithmetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

/**
 * 插入排序
 */
public class InsertSort {

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
            List<Integer> sorted = sortByInsert(list);
            StringBuilder stringBuilder_sorted = new StringBuilder();
            for (int i = 0; i < sorted.size(); i++) {
                stringBuilder_sorted.append(sorted.get(i) + ",");
            }
            System.out.println("zhoujie sort = " + stringBuilder_sorted.toString());
        }
    }

    private static List<Integer> sortByInsert(List<Integer> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }
        LinkedList<Integer> result = new LinkedList<>();
        result.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            int curCompareIndex = i - 1;
            while (curCompareIndex >= 0 && list.get(i) < result.get(curCompareIndex)) {
                curCompareIndex--;
            }
            result.add(++curCompareIndex, list.get(i));
        }
        list.clear();
        list.addAll(result);
        return list;
    }

    public static final class ShellSort {
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
                List<Integer> sorted = sortByShell(list);
                StringBuilder stringBuilder_sorted = new StringBuilder();
                for (int i = 0; i < sorted.size(); i++) {
                    stringBuilder_sorted.append(sorted.get(i) + ",");
                }
                System.out.println("zhoujie sort = " + stringBuilder_sorted.toString());
            }
        }

        private static List<Integer> sortByShell(List<Integer> list) {
            if (list == null || list.size() <= 1) {
                return list;
            }

            int[] arr = new int[list.size()];
            Iterator<Integer> iterator = list.iterator();
            int arrInitIndex = 0;
            while (iterator.hasNext()) {
                arr[arrInitIndex++] = iterator.next();
            }
            for (int step = arr.length / 2; step > 0; step /= 2) {
                // 计算单个组
                for (int startIndex = 0; startIndex <= step; startIndex++) {
                    for (int i = startIndex + step; i < arr.length; i += step) {
                        int holePos = i;
                        int holeValue = arr[holePos];
                        while (holePos > startIndex && holeValue < arr[holePos - step]) {
                            arr[holePos] = arr[holePos - step];
                            holePos -= step;
                        }
                        arr[holePos] = holeValue;
                    }
                }
            }

            list.clear();
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
            }

            return list;
        }
    }
}

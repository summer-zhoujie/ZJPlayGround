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
}

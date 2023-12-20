package arithmetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 快速排序
 */
public class QuickSort {

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
            List<Integer> sorted = sortByQuick(list);
            StringBuilder stringBuilder_sorted = new StringBuilder();
            for (int i = 0; i < sorted.size(); i++) {
                stringBuilder_sorted.append(sorted.get(i) + ",");
            }
            System.out.println("zhoujie sort = " + stringBuilder_sorted.toString());
        }
    }

    private static List<Integer> sortByQuick(List<Integer> list) {

        Integer[] array = new Integer[list.size()];
        int arrayIndex = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            array[arrayIndex++] = iterator.next();
        }

        doSrotQuick(array, 0, array.length - 1);

        List<Integer> newResult = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            newResult.add(array[i]);
        }
        return newResult;
    }

    private static void doSrotQuick(Integer[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int start = startIndex;
        int end = endIndex;
        int pivot = array[start];
        int pivotIndex = start;

        while (start < end) {
            while (start < end && pivotIndex == start) {
                if (array[end] < pivot) {
                    array[pivotIndex] = array[end];
                    pivotIndex = end;
                } else {
                    end--;
                }
            }

            while (start < end && pivotIndex == end) {
                if (array[start] > pivot) {
                    array[pivotIndex] = array[start];
                    pivotIndex = start;
                } else {
                    start++;
                }
            }

        }
        array[start] = pivot;
        doSrotQuick(array, startIndex, start - 1);
        doSrotQuick(array, start + 1, endIndex);
    }
}

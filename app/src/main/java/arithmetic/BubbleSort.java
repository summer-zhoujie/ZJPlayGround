package arithmetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 冒泡排序
 */
public class BubbleSort {
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
            List<Integer> sorted = bubbleSort(list);
            StringBuilder stringBuilder_sorted = new StringBuilder();
            for (int i = 0; i < sorted.size(); i++) {
                stringBuilder_sorted.append(sorted.get(i) + ",");
            }
            System.out.println("zhoujie sort = " + stringBuilder_sorted.toString());
        }
    }

    private static List<Integer> bubbleSort(List<Integer> list) {

        Integer[] array = new Integer[list.size()];
        int arrayIndex = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            array[arrayIndex++] = iterator.next();
        }
        boolean isSwap;
        Integer temp = null;
        for (int i = array.length - 1; i > 0; i--) {
            isSwap = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    isSwap = true;
                }
            }

            if (!isSwap) {
                break;
            }
        }

        List<Integer> newResult = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            newResult.add(array[i]);
        }
        return newResult;
    }
}

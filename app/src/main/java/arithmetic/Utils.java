package arithmetic;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

public class Utils {
    public static void myShuffle(List<?> list) {
        Random random = new Random();
        if (list.size() <= 5 || list instanceof RandomAccess) {
            for (int i = list.size(); i > 1; i--) {
                int cur = i - 1;
                int swapPos = random.nextInt(i);
                final List finalList = list;
                finalList.set(cur, finalList.set(swapPos, finalList.get(cur)));
            }
        } else {
            Object[] array = new Object[list.size()];
            Iterator<?> iterator = list.iterator();
            int j = 0;
            while (iterator.hasNext()) {
                array[j++] = iterator.next();
            }

            for (int i = array.length; i > 1; i--) {
                int cur = i - 1;
                int swapPos = random.nextInt(i);
                Object temp = array[swapPos];
                array[swapPos] = array[cur];
                array[cur] = temp;
            }

            final List finalList = list;
            finalList.clear();
            for (Object o : array) {
                finalList.add(o);
            }
        }
    }
}

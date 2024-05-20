package arithmetic;

import android.content.Context;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.playground.kttest.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Stack;

public class LeetCode {

    public static void main(String[] args) {
//        long l = new Solution_1969().minNonZeroProduct(3);
//        System.out.println("count: " + l);

        /**
         * ["FrequencyTracker[]","hasFrequency[1]","add[3]","hasFrequency[1]","hasFrequency[1]","add[6]","add[2]","add[6]","deleteOne[6]","deleteOne[6]","hasFrequency[2]","add[2]","hasFrequency[2]","hasFrequency[1]"]
         */
//        FrequencyTracker frequencyTracker = new FrequencyTracker();
//        frequencyTracker.hasFrequency(1);
//        frequencyTracker.add(3);
//        frequencyTracker.hasFrequency(1);
//        frequencyTracker.hasFrequency(1);
//        frequencyTracker.add(6);
//        frequencyTracker.add(2);
//        frequencyTracker.add(6);
//        frequencyTracker.deleteOne(6);
//        frequencyTracker.deleteOne(6);
//        frequencyTracker.hasFrequency(2);
//        frequencyTracker.add(2);
//        frequencyTracker.hasFrequency(2);
//        frequencyTracker.hasFrequency(1);

//        int[][] grid = new int[][]{{3, 4, 2, 1}, {4, 2, 3, 1}, {2, 1, 0, 0}, {2, 4, 0, 0}};
//        int i = new Solution_2617().minimumVisitedCells_planB(grid);
//        System.out.println(i);

//        int amount_518 = 5;
//        int[] coins_518 = new int[]{1, 2, 5};
//        int change = new Solution_518().change(amount_518, coins_518);
//        System.out.println(change);

//        Graph graph = new Graph(4, new int[][]{{0, 2, 5}, {0, 1, 2}, {1, 2, 1}, {3, 0, 3}});
//        System.out.println(graph.shortestPath(3, 2));
//        System.out.println(graph.shortestPath(0, 3));
//        graph.addEdge(new int[]{1, 3, 4});
//        System.out.println(graph.shortestPath(0, 3));

//        int i = new Solution_2580().countWays(new int[][]{{57, 92}, {139, 210}, {306, 345}, {411, 442}, {533, 589}, {672, 676}, {801, 831}, {937, 940}, {996, 1052}, {1113, 1156}, {1214, 1258}, {1440, 1441}, {1507, 1529}, {1613, 1659}, {1773, 1814}, {1826, 1859}, {2002, 2019}, {2117, 2173}, {2223, 2296}, {2335, 2348}, {2429, 2532}, {2640, 2644}, {2669, 2676}, {2786, 2885}, {2923, 2942}, {3035, 3102}, {3177, 3249}, {3310, 3339}, {3450, 3454}, {3587, 3620}, {3725, 3744}, {3847, 3858}, {3901, 3993}, {4100, 4112}, {4206, 4217}, {4250, 4289}, {4374, 4446}, {4510, 4591}, {4675, 4706}, {4732, 4768}, {4905, 4906}, {5005, 5073}, {5133, 5142}, {5245, 5309}, {5352, 5377}, {5460, 5517}, {5569, 5602}, {5740, 5791}, {5823, 5888}, {6036, 6042}, {6096, 6114}, {6217, 6262}, {6374, 6394}, {6420, 6511}, {6564, 6587}, {6742, 6743}, {6797, 6877}, {6909, 6985}, {7042, 7117}, {7141, 7144}, {7276, 7323}, {7400, 7456}, {7505, 7557}, {7690, 7720}, {7787, 7800}, {7870, 7880}, {8013, 8031}, {8114, 8224}, {8272, 8328}, {8418, 8435}, {8493, 8537}, {8600, 8704}, {8766, 8812}, {8839, 8853}, {9032, 9036}, {9108, 9189}, {9222, 9291}, {9344, 9361}, {9448, 9502}, {9615, 9673}, {9690, 9800}, {9837, 9868}, {85, 96}, {145, 202}, {254, 304}, {372, 411}, {534, 551}, {629, 692}, {727, 787}, {861, 944}, {1041, 1084}, {1133, 1174}, {1260, 1307}, {1339, 1358}, {1478, 1548}, {1580, 1618}, {1694, 1814}, {1848, 1891}, {1936, 1990}, {2058, 2130}});
//        System.out.println(i);

//        int i = new Solution_2009().minOperations(new int[]{4, 2, 5, 5,3,3});
//        System.out.println(i);

//        // 新建了一个银行,里面记录了每个用户的存款
//        HashMap<User, Integer> bank = new HashMap<>();
//        // jack在银行存了10元
//        User jack = new User("jack", 15);
//        print("jack = " + jack.hashCode());
//        bank.put(jack, 10);
//        // jack来取钱, 结果发现银行显示余额0元
//        User jack1 = new User("jack", 15);
//        print("jack1 = " + jack1.hashCode());
//        Integer balance = bank.get(jack1);
//        System.out.println(balance);

//        int[] failed = {-1, 11, 1, 8, 3, 9, 5, 4, 7, 14, 1, 10, 10, 3, 3, 7, 11, 0, 6, 7, 11, 10, 11, 6, 7, 6, 8, 9, 6, 6, 1, 9, 10, 12, 8, 5, 1, 11, 7, 9, 5, 1, 6, 7, 13, 1, 3};
//        int[] success = {-1, 21, 17, 43, 10, 42, 7, 13, 29, 44, 17, 31, 39, 10, 10, 29, 32, 0, 40, 23, 12, 39, 12, 40, 25, 35, 15, 38, 40, 40, 17, 24, 5, 1, 19, 14, 17, 21, 25, 24, 14, 17, 40, 25, 37, 17, 10};
//        print(failed[17]);
//        print(success[17]);
//        Solution_1766 solution1766 = new Solution_1766();
//        int[] coprimes = solution1766.getCoprimes(new int[]{9,16,30,23,33,35,9,47,39,46,16,38,5,49,21,44,17,1,6,37,49,15,23,46,38,9,27,3,24,1,14,17,12,23,43,38,12,4,8,17,11,18,26,22,49,14,9}, new int[][]{{17,0},{30,17},{41,30},{10,30},{13,10},{7,13},{6,7},{45,10},{2,10},{14,2},{40,14},{28,40},{29,40},{8,29},{15,29},{26,15},{23,40},{19,23},{34,19},{18,23},{42,18},{5,42},{32,5},{16,32},{35,14},{25,35},{43,25},{3,43},{36,25},{38,36},{27,38},{24,36},{31,24},{11,31},{39,24},{12,39},{20,12},{22,12},{21,39},{1,21},{33,1},{37,1},{44,37},{9,44},{46,2},{4,46}});
//        print(Arrays.toString(coprimes));

//        MyHashMap myHashMap = new MyHashMap();
//        myHashMap.put(1, 1);
//        myHashMap.put(2, 2);

//        Solution_924 solution924 = new Solution_924();
////        int i = solution924.minMalwareSpread(new int[][]{{1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 1}}, new int[]{1, 3, 0});
//        int i = solution924.minMalwareSpread(new int[][]{{1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}}, new int[]{2, 1});
//        print(i);

//        Solution_928 solution928 = new Solution_928();
////        int i = solution928.minMalwareSpread(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, new int[]{0, 1});
//        int i = solution928.minMalwareSpread(new int[][]{{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}}, new int[]{1, 6, 35, 5, 27, 32});
//        print(i);

//        int[] originalArray = new Solution_2007().findOriginalArray(new int[]{1, 3, 4, 2, 6, 8});
//        print(Arrays.toString(originalArray));

//        Solution_377 solution377 = new Solution_377();
//        int i = solution377.combinationSum4(new int[]{1, 2, 3}, 4);
//        print(i);

//        int i = new Solution_1052().maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3);
//        print(i);

//        SnapshotArray snapshotArray = new SnapshotArray(1);
//        snapshotArray.set(0, 4);
//        snapshotArray.set(0, 16);
//        snapshotArray.set(0, 13);
//        snapshotArray.snap();
//        snapshotArray.get(0, 0);
//        String s = new Solution_1017().baseNeg2(2);
//        print(s);

//        int i = new Solution_1463().cherryPickup(new int[][]{{3, 1, 1}, {2, 5, 1}, {1, 5, 5}, {2, 1, 1}});
//        print(i);

//        int minimumTime = new Solution_2589().findMinimumTime(new int[][]{{2, 3, 1}, {4, 5, 1}, {1, 5, 2}});
//        print(minimumTime);

//        int i = new Solution_826().maxProfitAssignment(new int[]{13, 37, 58}, new int[]{4, 90, 96}, new int[]{34, 73, 45});
//        print(i);

        int i = new Solution_1542().longestAwesome("3242415");
        print(i);
    }

    /**
     * 1542. 找出最长的超赞子字符串
     */
    static class Solution_1542 {
        public int longestAwesome(String s) {

            int m = s.length();
            int[] pos = new int[1 << 10];
            Arrays.fill(pos, m);
            pos[0] = -1;
            int countInfo = 0;
            int res = 1;
            for (int i = 0; i < m; i++) {
                countInfo ^= 1 << (s.charAt(i) - '0');
                //奇数
                for (int j = 0; j < 10; j++) {
                    res = Math.max(res, i - pos[countInfo ^ (1 << j)]);
                }
                //偶数
                res = Math.max(res, i - pos[countInfo]);
                if (pos[countInfo] == m) {
                    pos[countInfo] = i;
                }
            }
            return res;
        }
    }

    /**
     * 826. 安排工作以达到最大收益
     */
    static class Solution_826 {
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
            sort(0, difficulty.length - 1, difficulty, profit);
            Arrays.sort(worker);
            int j = 0;
            int curMaxProfit = 0;
            int res = 0;
            for (int i = 0; i < difficulty.length && j < worker.length; i++) {
                while (j < worker.length && difficulty[i] > worker[j]) {
                    res += curMaxProfit;
                    j++;
                }
                curMaxProfit = Math.max(curMaxProfit, profit[i]);
            }

            for (int i = j; i < worker.length; i++) {
                res += curMaxProfit;
            }
            return res;
        }

        private void sort(int start, int end, int[] difficulty, int[] profit) {
            if (start >= end) {
                return;
            }
            int pivotIndex = start;
            int pivot = difficulty[pivotIndex];
            int pivot_profit = profit[pivotIndex];
            int left = start;
            int right = end;
            while (left < right) {
                while (left == pivotIndex && left < right) {
                    if (difficulty[right] < pivot) {
                        difficulty[pivotIndex] = difficulty[right];
                        profit[pivotIndex] = profit[right];
                        pivotIndex = right;
                        break;
                    }
                    right--;
                }
                while (right == pivotIndex && left < right) {
                    if (difficulty[left] > pivot) {
                        difficulty[pivotIndex] = difficulty[left];
                        profit[pivotIndex] = profit[left];
                        pivotIndex = left;
                        break;
                    }
                    left++;
                }
            }
            difficulty[left] = pivot;
            profit[left] = pivot_profit;

            sort(start, left, difficulty, profit);
            sort(left + 1, end, difficulty, profit);
        }
    }

    /**
     * 2589. 完成所有任务的最少时间
     */
    static class Solution_2589 {
        public int findMinimumTime(int[][] tasks) {
            // 按照end的大小进行排序
            sort(0, tasks.length - 1, tasks);
            boolean[] records = new boolean[2001];
            int res = 0;
            // 按顺序遍历,并记录CPU累计运行的时间点
            for (int[] task : tasks) {
                int leftCount = task[2];
                for (int i = task[0]; i <= task[1]; i++) {
                    if (records[i]) {
                        leftCount--;
                    }
                }
                if (leftCount > 0) {
                    for (int i = task[1]; i >= task[0]; i--) {
                        if (!records[i]) {
                            leftCount--;
                            records[i] = true;
                            res++;
                        }
                        if (leftCount <= 0) {
                            break;
                        }
                    }
                }

            }
            return res;
        }

        private void sort(int start, int end, int[][] arr) {
            if (start >= end) {
                return;
            }
            int pivotIndex = start;
            int[] pivot = arr[pivotIndex];
            int left = start;
            int right = end;
            while (left < right) {
                while (pivotIndex == left && left < right) {
                    if (arr[right][1] < pivot[1]) {
                        arr[pivotIndex] = arr[right];
                        pivotIndex = right;
                        break;
                    } else {
                        right--;
                    }
                }
                while (pivotIndex == right && left < right) {
                    if (arr[left][1] > pivot[1]) {
                        arr[pivotIndex] = arr[left];
                        pivotIndex = left;
                        break;
                    } else {
                        left++;
                    }
                }
            }
            arr[left] = pivot;
            sort(start, left, arr);
            sort(left + 1, end, arr);
        }
    }

    /**
     * 2244. 完成所有任务需要的最少轮数
     */
    class Solution_2244 {
        public int minimumRounds(int[] tasks) {
            HashMap<Integer, Integer> hashMapCount = new HashMap<>();
            for (int task : tasks) {
                Integer oldValue = hashMapCount.get(task);
                int newCount = (oldValue == null ? 0 : oldValue) + 1;
                hashMapCount.put(task, newCount);
            }
            int result = 0;
            for (Integer key : hashMapCount.keySet()) {
                Integer value = hashMapCount.get(key);
                if (value == null) {
                    continue;
                }
                if (value == 1) {
                    return -1;
                }
                result = result + value / 3 + (value % 3 != 0 ? 1 : 0);
            }
            return result;
        }
    }

    /**
     * 994. 腐烂的橘子
     */
    class Solution_994 {
        public int orangesRotting(int[][] grid) {
            List<int[]> list = new ArrayList<>();
            int m = grid.length;
            int n = grid[0].length;
            int goodCount = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        goodCount++;
                    } else if (grid[i][j] == 2) {
                        list.add(new int[]{i, j});
                    }
                }
            }
            if (goodCount == 0) {
                return 0;
            }
            if (list.isEmpty()) {
                return -1;
            }
            int res = 0;
            while (goodCount > 0 && !list.isEmpty()) {
                List<int[]> newList = new ArrayList<>();
                for (int[] pos : list) {
                    int i = pos[0];
                    int j = pos[1];
                    if (badOk(i - 1, j, m, n, grid)) {
                        newList.add(new int[]{i - 1, j});
                        goodCount--;
                    }
                    if (badOk(i + 1, j, m, n, grid)) {
                        newList.add(new int[]{i + 1, j});
                        goodCount--;
                    }
                    if (badOk(i, j - 1, m, n, grid)) {
                        newList.add(new int[]{i, j - 1});
                        goodCount--;
                    }
                    if (badOk(i, j + 1, m, n, grid)) {
                        newList.add(new int[]{i, j + 1});
                        goodCount--;
                    }
                }
                list = newList;
                res++;
            }
            return goodCount == 0 ? res : -1;
        }

        private boolean badOk(int i, int j, int m, int n, int[][] grid) {
            if (i >= 0 && i < m && j >= 0 && j < n) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 2;
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 2391. 收集垃圾的最少总时间
     */
    class Solution_2391 {
        public int garbageCollection(String[] garbage, int[] travel) {
            int res = 0;
            int[] carMaxDistances = new int[3];
            int curTravelTime = 0;
            for (int i = 0; i < garbage.length; i++) {
                if (i == 0) {
                    res += garbage[i].length();
                } else {
                    curTravelTime += travel[i - 1];
                    res += garbage[i].length();
                    for (int j = 0; j < garbage[i].length(); j++) {
                        char c = garbage[i].charAt(j);
                        if (c == 'M') {
                            carMaxDistances[0] = curTravelTime;
                        } else if (c == 'P') {
                            carMaxDistances[1] = curTravelTime;
                        } else if (c == 'G') {
                            carMaxDistances[2] = curTravelTime;
                        }
                    }
                }
            }
            for (int carMaxInstance : carMaxDistances) {
                res += carMaxInstance;
            }
            return res;
        }
    }

    /**
     * 2960. 统计已测试设备
     */
    class Solution_2960 {
        public int countTestedDevices(int[] batteryPercentages) {
            int res = 0;
            int cut = 0;
            for (int batteryPercentage : batteryPercentages) {
                int realPercent = Math.max(0, batteryPercentage - cut);
                if (realPercent > 0) {
                    cut++;
                    res++;
                }
            }
            return res;
        }
    }

    /**
     * 2105. 给植物浇水
     */
    class Solution {
        public int minimumRefill(int[] plants, int capacityA, int capacityB) {
            int aLeft = capacityA;
            int bLeft = capacityB;
            int res = 0;
            int l = 0;
            int r = plants.length - 1;
            while (l <= r) {
                if (l == r) {
                    if (aLeft >= bLeft) {
                        if (aLeft >= plants[l]) {
                            aLeft -= plants[l];
                        } else {
                            res++;
                            aLeft = capacityA - plants[l];
                        }
                    } else {
                        if (bLeft >= plants[r]) {
                            bLeft -= plants[r];
                        } else {
                            res++;
                            bLeft = capacityB - plants[r];
                        }
                    }
                } else {
                    if (aLeft >= plants[l]) {
                        aLeft -= plants[l];
                    } else {
                        res++;
                        aLeft = capacityA - plants[l];
                    }
                    if (bLeft >= plants[r]) {
                        bLeft -= plants[r];
                    } else {
                        res++;
                        bLeft = capacityB - plants[r];
                    }
                }
                l++;
                r--;
            }
            return res;
        }
    }

    /**
     * 2079. 给植物浇水
     */
    static class Solution_2079 {
        public int wateringPlants(int[] plants, int capacity) {
            int curLeft = capacity;
            int res = 0;
            for (int i = 0; i < plants.length; i++) {
                if (curLeft < plants[i]) {
                    res += i * 2;
                    curLeft = capacity;
                }

                res++;
                curLeft -= plants[i];
            }
            return res;
        }
    }

    /**
     * 1463. 摘樱桃 II
     */
    static class Solution_1463 {
        /**
         * f(t,i1,j1,i2,j2), 其中i1==i2==t
         * f(t,j1,j2), 其中i1==i2==t
         * 左边机器人上一步可能的点位(t-1,j1-1)  (t-1,j1)  (t-1,j1+1)
         * 右边机器人上一步可能的点位(t-1,j2-1)  (t-1,j2)  (t-1,j2+1)
         * f(t,j1,j2) =
         * Max{
         * f(t-1,j1-1,j2-1), f(t-1,j1-1,j2), f(t-1,j1-1,j2+1),
         * f(t-1,j1,j2-1), f(t-1,j1,j2), f(t-1,j1,j2+1),
         * f(t-1,j1+1,j2-1), f(t-1,j1+1,j2), f(t-1,j1+1,j2+1)
         * } +
         * // 当前这一步获取的樱桃值
         * {
         * if(j1==j2) return grid[t,j1];
         * else return grid[t,j1]+grid[t,j2];
         * }
         * <p>
         * 初始化临界状态(假设grid的size是m*n且m,n>=2)
         * f(0,0,n-1) = grid[0,0]+grid[0,n-1]
         *
         * @param grid
         * @return
         */
        public int cherryPickup(int[][] grid) {
            int max = 0;
            int m = grid.length;
            int n = grid[0].length;
            // 初始化临界状态
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put(generateKey(0, 0, n - 1), grid[0][0] + grid[0][n - 1]);
            // 开始dp
            for (int t = 1; t < m; t++) {
                for (int j1 = 0; j1 <= t; j1++) {
                    for (int j2 = Math.max(j1, n - 1 - t); j2 < n; j2++) {
                        int curMax = 0;

                        int lastJ1Start = 0;
                        int lastJ1End = t - 1;
                        int lastJ2Start = n - 1 - (t - 1);
                        int lastJ2End = n - 1;

                        curMax = Math.max(curMax, culItemValue(t - 1, j1 - 1, j2 - 1, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));
                        curMax = Math.max(curMax, culItemValue(t - 1, j1 - 1, j2, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));
                        curMax = Math.max(curMax, culItemValue(t - 1, j1 - 1, j2 + 1, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));

                        curMax = Math.max(curMax, culItemValue(t - 1, j1, j2 - 1, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));
                        curMax = Math.max(curMax, culItemValue(t - 1, j1, j2, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));
                        curMax = Math.max(curMax, culItemValue(t - 1, j1, j2 + 1, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));

                        curMax = Math.max(curMax, culItemValue(t - 1, j1 + 1, j2 - 1, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));
                        curMax = Math.max(curMax, culItemValue(t - 1, j1 + 1, j2, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));
                        curMax = Math.max(curMax, culItemValue(t - 1, j1 + 1, j2 + 1, lastJ1Start, lastJ1End, lastJ2Start, lastJ2End, hashMap));

                        curMax += grid[t][j1] + (j1 == j2 ? 0 : grid[t][j2]);
                        hashMap.put(generateKey(t, j1, j2), curMax);

                        if (t == m - 1) {
                            max = Math.max(curMax, max);
                        }
                    }
                }
            }

            return max;
        }

        private int culItemValue(int t, int j1, int j2, int lastJ1Start, int lastJ1End, int lastJ2Start, int lastJ2End, HashMap<String, Integer> hashMap) {
            boolean isValid_j1 = j1 >= lastJ1Start && j1 <= lastJ1End;
            boolean isValid_j2 = j2 >= lastJ2Start && j2 <= lastJ2End;
            if (isValid_j1 && isValid_j2) {
                Integer integer = hashMap.get(generateKey(t, j1, j2));
                if (integer != null) {
                    return integer;
                }
            }
            return -1;
        }

        private String generateKey(int t, int j1, int j2) {
            return t + "," + j1 + "," + j2;
        }
    }

    /**
     * 2798. 满足目标工作时长的员工数目
     */
    class Solution_2798 {
        public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
            int res = 0;
            for (int hour : hours) {
                if (hour >= target) res++;
            }
            return res;
        }
    }

    /**
     * 1329. 将矩阵按对角线排序
     */
    static class Solution_1329 {
        public int[][] diagonalSort(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            for (int startM = 0; startM < m - 1; startM++) {
                int startN = 0;
                // 插入排序
                int i = startM + 1;
                int j = startN + 1;
                while (i < m && j < n) {
                    int cur = mat[i][j];
                    int l = i - 1;
                    int k = j - 1;
                    while (l >= startM && k >= startN && cur < mat[l][k]) {
                        mat[l + 1][k + 1] = mat[l][k];
                        l--;
                        k--;
                    }
                    mat[l + 1][k + 1] = cur;
                    i++;
                    j++;
                }
            }
            for (int startN = 1; startN < n - 1; startN++) {
                int startM = 0;
                // 插入排序
                int i = startM + 1;
                int j = startN + 1;
                while (i < m && j < n) {
                    int cur = mat[i][j];
                    int l = i - 1;
                    int k = j - 1;
                    while (l >= startM && k >= startN && cur < mat[l][k]) {
                        mat[l + 1][k + 1] = mat[l][k];
                        l--;
                        k--;
                    }
                    mat[l + 1][k + 1] = cur;
                    i++;
                    j++;
                }
            }
            return mat;
        }
    }

    /**
     * 1017. 负二进制转换
     */
    static class Solution_1017 {
        public String baseNeg2(int n) {
            StringBuilder result = new StringBuilder();
            while (n != 0) {
                if (n % -2 == 0) {
                    result.insert(0, "0");
                } else {
                    n = n - 1;
                    result.insert(0, "1");
                }
                n /= -2;
            }
            return result.toString();
        }
    }

    /**
     * 1146. 快照数组
     */
    static class SnapshotArray {
        HashMap<Integer, List<int[]>> history = new HashMap<>();
        int snap = 0;

        public SnapshotArray(int length) {

        }

        public void set(int index, int val) {
            List<int[]> ints = history.get(index);
            if (ints == null) {
                ints = new ArrayList<>();
                history.put(index, ints);
            }
            if (ints.size() != 0 && ints.get(ints.size() - 1)[0] == snap) {
                ints.get(ints.size() - 1)[1] = val;
            } else {
                ints.add(new int[]{snap, val});
            }
        }

        public int snap() {
            return snap++;
        }

        public int get(int index, int snap_id) {
            List<int[]> ints = history.get(index);
            if (ints == null || ints.size() == 0) {
                return 0;
            }
            int searchResult = binarySearch(ints, snap_id);
            if (searchResult < 0) {
                return 0;
            }
            return ints.get(searchResult)[1];
        }

        private int binarySearch(List<int[]> ints, int target) {
            int left = -1;
            int right = ints.size() - 1;
            while (left < right) {
                int mid = left + 1 + (right - left - 1) / 2;
                if (ints.get(mid)[0] <= target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }

            return left;
        }
    }

    /**
     * 2739. 总行驶距离
     */
    static class Solution_2739 {
        public int distanceTraveled(int mainTank, int additionalTank) {
            int sumTank = mainTank;

            for (int i = mainTank; i >= 5; ) {
                int add = i / 5;
                if (additionalTank <= 0) {
                    break;
                }
                if (additionalTank - add < 0) {
                    add = additionalTank;
                }
                additionalTank -= add;
                int nextI = add + sumTank % 5;
                sumTank = sumTank + add;
                i = nextI;
            }
            return sumTank * 10;
        }
    }

    /**
     * 2385. 感染二叉树需要的总时间
     */
    static class Solution_2385 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        int maxDeep = 0;

        public int amountOfTime(TreeNode root, int start) {
            List<Integer>[] g = new List[1_00000 + 1];
            dsf(root, g, null);
            maxDeep = 0;
            findMaxDeep(start, start, g, 0);
            return maxDeep;
        }

        private void findMaxDeep(int start, int exclude, List<Integer>[] g, int deep) {
            if (g[start] != null) {
                boolean isLeafNode = true;
                for (Integer next : g[start]) {
                    if (next != exclude) {
                        isLeafNode = false;
                        findMaxDeep(next, start, g, deep + 1);
                    }
                }

                if (isLeafNode) {
                    maxDeep = Math.max(maxDeep, deep);
                }
            }
        }

        private void dsf(TreeNode root, List<Integer>[] g, TreeNode parent) {
            if (root == null) {
                return;
            }

            if (parent != null) {
                if (g[root.val] == null) {
                    g[root.val] = new ArrayList<>();
                }
                g[root.val].add(parent.val);
                if (g[parent.val] == null) {
                    g[parent.val] = new ArrayList<>();
                }
                g[parent.val].add(root.val);
            }
            dsf(root.left, g, root);
            dsf(root.right, g, root);
        }
    }

    static class Solution_2385_planB {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        int result = -1;

        public int amountOfTime(TreeNode root, int start) {
            dfs(root, start);
            return result;
        }

        private int[] dfs(TreeNode root, int start) {
            if (root == null) {
                return new int[]{0, 0};
            }
            int[] leftSearch = dfs(root.left, start);
            int[] rightSearch = dfs(root.right, start);
            int leftL = leftSearch[0];
            int leftFound = leftSearch[1];
            int rightL = rightSearch[0];
            int rightFound = rightSearch[1];
            if (root.val == start) {
                result = Math.max(leftL, rightL);
                return new int[]{1, 1};
            } else if (leftFound == 1 || rightFound == 1) {
                result = Math.max(result, leftL + rightL);
                return new int[]{(leftFound == 1 ? leftL : rightL) + 1, 1};
            } else {
                return new int[]{Math.max(leftL, rightL) + 1, 0};
            }
        }


    }

    /**
     * 1052. 爱生气的书店老板
     */
    static class Solution_1052 {
        public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
            //滑动窗口
            int[] s = new int[2];
            int sMax = 0;
            for (int i = 0; i < customers.length; i++) {
                s[grumpy[i]] += customers[i];
                if (i < minutes - 1) {
                    continue;
                }
                sMax = Math.max(sMax, s[1]);
                s[1] -= grumpy[i - minutes + 1] == 1 ? customers[i - minutes + 1] : 0;
            }
            return s[0] + sMax;
        }
    }

    /**
     * 377. 组合总和 Ⅳ
     */
    static class Solution_377 {
        /**
         * 状态转移方程f(target) = f(target-nums[0])+...+f(target-nums[lenght-1])
         */
        public int combinationSum4(int[] nums, int target) {
            int[] f = new int[target + 1];
            f[0] = 1;
            for (int i = 1; i < target + 1; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] <= i) {
                        f[i] += f[i - nums[j]];
                    }
                }
            }
            return f[target];
        }
    }

    /**
     * 2007. 从双倍数组中还原原数组
     */
    static class Solution_2007 {
        public int[] findOriginalArray(int[] changed) {
            int n = changed.length;
            if (n % 2 != 0) {
                return new int[]{};
            }

            boolean[] isVisit = new boolean[n];
            Arrays.fill(isVisit, false);

            Arrays.sort(changed);

            int[] result = new int[n / 2];

            int j = 1;
            int k = 0;
            for (int i = 0; i < n; i++) {
                if (!isVisit[i]) {
                    j = Math.max(j, i + 1);
                    int target = changed[i] * 2;
                    while (j < n && changed[j] != target) {
                        j++;
                    }
                    if (j == n) {
                        return new int[]{};
                    }

                    isVisit[j++] = true;
                    result[k++] = changed[i];
                }
            }
            return result;
        }
    }

    /**
     * 928. 尽量减少恶意软件的传播 II
     */
    static class Solution_928 {

        int n = 301;
        boolean[] isVisit = new boolean[n];
        boolean[] isInitial = new boolean[n];
        int size = 0;
        int nodeId = -1;

        public int minMalwareSpread(int[][] graph, int[] initial) {
            Arrays.fill(isVisit, false);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < initial.length; i++) {
                min = Math.min(min, initial[i]);
                isInitial[initial[i]] = true;
            }

            int[] result = new int[n];
            Arrays.fill(result, 0);

            for (int i = 0; i < graph.length; i++) {
                if (!isVisit[i] && !isInitial[i]) {
                    size = 0;
                    nodeId = -1;
                    dsp(i, graph);
                    if (nodeId >= 0) {
                        result[nodeId] += size;
                    }
                }
            }
            size = 0;
            nodeId = -1;
            for (int i : initial) {
                if (result[i] > size) {
                    size = result[i];
                    nodeId = i;
                } else if (result[i] == size) {
                    nodeId = Math.min(nodeId, i);
                }
            }
            return nodeId < 0 ? min : nodeId;
        }

        private void dsp(int i, int[][] graph) {
            isVisit[i] = true;
            size++;
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 1) {
                    if (isInitial[j]) {
                        if (nodeId != -2 && nodeId != j) {
                            nodeId = nodeId == -1 ? j : -2;
                        }
                    } else if (!isVisit[j]) {
                        dsp(j, graph);
                    }
                }
            }
        }


    }

    /**
     * 924. 尽量减少恶意软件的传播
     */
    static class Solution_924 {
        public int minMalwareSpread(int[][] graph, int[] initial) {
            UnionSearch unionSearch = new UnionSearch(301);
            for (int i = 0; i < graph.length; i++) {
                for (int j = i + 1; j < graph[i].length; j++) {
                    if (graph[i][j] == 1) {
                        unionSearch.put(i, j);
                    }
                }
            }
            HashMap<Integer, int[]> result = new HashMap<>();
            for (int i = 0; i < initial.length; i++) {
                int root = unionSearch.getRoot(initial[i]);
                int[] ints = result.get(root);
                if (ints == null) {
                    result.put(root, new int[]{initial[i], 1, unionSearch.getSize(root)});
                } else {
                    ints[0] = Math.min(initial[i], ints[0]);
                    ints[1] += 1;
                }
            }

            int[] res = null;
            for (int[] value : result.values()) {
                if (res == null) {
                    res = value;
                } else {
                    if (res[1] == 1 && value[1] == 1) {
                        if (value[2] == res[2]) {
                            res = value[0] < res[0] ? value : res;
                        } else {
                            res = value[2] > res[2] ? value : res;
                        }
                    } else if (res[1] == 1 && value[1] != 1) {
                        //do nothing
                    } else if (res[1] != 1 && value[1] == 1) {
                        res = value;
                    } else if (res[1] != 1 && value[1] != 1) {
                        res = value[0] < res[0] ? value : res;
                    }
                }
            }
            return res[0];
        }

        static class UnionSearch {
            int[] parent = null;
            int[] size = null;

            UnionSearch(int n) {
                parent = new int[n];
                for (int i = 0; i < parent.length; i++) {
                    parent[i] = i;
                }
                size = new int[n];
                for (int i = 0; i < size.length; i++) {
                    size[i] = 1;
                }
            }

            public void put(int i, int j) {
                int rootI = getRoot(i);
                int rootJ = getRoot(j);
                if (rootI != rootJ) {
                    if (size[rootJ] > size[rootI]) {
                        parent[rootI] = rootJ;
                        size[rootJ] += size[rootI];
                    } else {
                        parent[rootJ] = rootI;
                        size[rootI] += size[rootJ];
                    }
                }

            }

            public int getRoot(int i) {
                if (parent[i] == i) {
                    return parent[i];
                }

                parent[i] = getRoot(parent[i]);
                return parent[i];
            }

            public int getSize(int i) {
                return size[i];
            }
        }
    }

    /**
     * 706. 设计哈希映射
     */
    static class MyHashMap {

        private static final int MAX_TABLE_LENGTH = 1 << 30;
        private static final int INIT_TABLE_SIZE = 16;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;

        class Node {
            int value;
            int key;
            Node next;

            public Node(int key, int value) {
                this.value = value;
                this.key = key;
            }
        }

        private float loadFactor = DEFAULT_LOAD_FACTOR;
        private Node[] table = null;
        private int threshold = 0;

        private int size = 0;

        private int hash(int key) {
            int hasCode = Objects.hash(key);
            return (hasCode << 16) ^ hasCode;
        }

        public MyHashMap() {

        }

        public void put(int key, int value) {
            Node nodeNew = new Node(key, value);
            if (table == null) {
                resize();
            }

            int index = (table.length - 1) & hash(key);
            Node oldNode = table[index];

            if (oldNode == null) {
                table[index] = nodeNew;
            } else {
                while (true) {
                    if (oldNode.key == key) {
                        oldNode.value = value;
                        break;
                    }
                    if (oldNode.next == null) {
                        oldNode.next = nodeNew;
                        break;
                    }
                    oldNode = oldNode.next;
                }
            }
            if (++size > threshold) {
                resize();
            }
        }

        private void resize() {
            if (table == null) {
                table = new Node[INIT_TABLE_SIZE];
                threshold = (int) (INIT_TABLE_SIZE * loadFactor);
            } else {
                if (table.length >= MAX_TABLE_LENGTH) {
                    threshold = Integer.MAX_VALUE;
                } else {
                    int newCap = table.length << 1;
                    Node[] newTable = new Node[newCap];
                    for (Node node : table) {
                        if (node == null) {
                            continue;
                        }
                        int newIndex = (newCap - 1) & hash(node.key);
                        if (node.next == null) {
                            newTable[newIndex] = node;
                        } else {
                            while (node != null) {
                                Node next = node.next;
                                node.next = null;
                                newIndex = (newCap - 1) & hash(node.key);
                                Node oldNode = newTable[newIndex];

                                if (oldNode == null) {
                                    newTable[newIndex] = node;
                                } else {
                                    while (true) {
                                        if (oldNode.key == node.key) {
                                            oldNode.value = node.value;
                                            break;
                                        }
                                        if (oldNode.next == null) {
                                            oldNode.next = node;
                                            break;
                                        }
                                        oldNode = oldNode.next;
                                    }
                                }
                                node = next;
                            }
                        }
                    }
                    table = newTable;
                    threshold = threshold << 1;
                }
            }
        }

        public int get(int key) {
            if (table == null) {
                return -1;
            }
            int hash = hash(key);
            int index = (table.length - 1) & hash;
            Node node = table[index];
            while (node != null) {
                if (node.key == key) {
                    return node.value;
                }
                node = node.next;
            }
            return -1;
        }

        public void remove(int key) {
            if (table == null) {
                return;
            }
            int hash = hash(key);
            int index = (table.length - 1) & hash;
            Node node = table[index];
            Node preNode = node;
            while (node != null) {
                if (node.key == key) {
                    if (preNode == node) {
                        table[index] = node.next;
                    } else {
                        preNode.next = node.next;
                    }
                    size--;
                    break;
                }
                preNode = node;
                node = node.next;
            }
        }
    }

    /**
     *
     */
    static class Solution_2923 {
        public int findChampion(int[][] grid) {
            int n = grid.length;
            int max = 0;
            for (int i = 1; i < n; i++) {
                max = grid[i][max] == 1 ? i : max;
            }
            return max;
        }
    }

    /**
     * 1766. 互质树
     */
    static class Solution_1766 {
        public int[] getCoprimes(int[] nums, int[][] edges) {
            List<Integer>[] gcds = new List[51];
            for (int i = 0; i < gcds.length; i++) {
                gcds[i] = new ArrayList<>();
            }
            for (int i = 1; i < gcds.length; i++) {
                for (int j = 1; j < gcds.length; j++) {
                    if (gcd(i, j) == 1) {
                        gcds[i].add(j);
                    }
                }
            }
            int n = nums.length;
            List<Integer>[] g = new List[n];
            for (int i = 0; i < g.length; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
            }
            int[] result = new int[n];
            for (int i = 0; i < result.length; i++) {
                result[i] = -1;
            }
            dsf(result, 0, 0, 1, new HashMap<Integer, int[]>(), g, nums, gcds);
            return result;
        }

        private void dsf(int[] result, int i, int exclude, int deep, HashMap<Integer, int[]> path, List<Integer>[] g, int[] nums, List<Integer>[] gcds) {
            // 求最大公约数==1的
            int maxDep = 0;
            int targetIndex = -1;
            for (Integer integer : gcds[nums[i]]) {
                int[] ints = path.get(integer);
                if (ints == null) {
                    continue;
                }
                int curDeep = ints[0];
                int index = ints[1];
                if (curDeep > maxDep) {
                    maxDep = curDeep;
                    targetIndex = index;
                }
            }
            result[i] = targetIndex;

            int[] oldValue = path.get(nums[i]);
            path.put(nums[i], new int[]{deep, i});

            for (Integer k : g[i]) {
                if (k != exclude) {
                    dsf(result, k, i, deep + 1, path, g, nums, gcds);
                }
            }
            path.put(nums[i], oldValue);
        }

        private int gcd(int a, int b) {
            if (a < 0 || b < 0) {
                return -1;
            }
            while (b > 0) {
                int t = b;
                b = a % b;
                a = t;
            }
            return a;
        }
    }

    /**
     * 1702. 修改后的最大二进制字符串
     */
    static class Solution_1702 {
        public String maximumBinaryString(String binary) {
            int zeroIndex = -1;
            for (int i = 0; i < binary.length(); i++) {
                if (binary.charAt(i) == '0') {
                    if (zeroIndex == -1) {
                        zeroIndex = i;
                    } else {
                        zeroIndex++;
                    }
                }
            }

            if (zeroIndex == -1) {
                return binary;
            }

            String replace = binary.replace("0", "1");
            return replace.substring(0, zeroIndex) + "0" + replace.substring(zeroIndex + 1);
        }
    }

    /**
     * 2529. 正整数和负整数的最大计数
     */
    static class Solution_2529 {
        public int maximumCount(int[] nums) {
            //二分找到第一个大于等于0的数组坐标
            int neg = binarySearch(nums, 0);
            int pos = nums.length - binarySearch(nums, 1);
            return Math.max(neg, pos);
        }

        private int binarySearch(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static class User {
        private String name;
        private Integer age;

        User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int hashCode() {
            int hashCode = name.hashCode();
            hashCode = hashCode * 31 + age.hashCode();
            return hashCode;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof User) {
                if (obj == this) {
                    return true;
                }

                return ((User) obj).name.equals(this.name)
                        && ((User) obj).age == this.age;
            }
            return false;
        }
    }


    /**
     * 2009. 使数组连续的最少操作数
     */
    static class Solution_2009 {
        public int minOperations(int[] nums) {
            Arrays.sort(nums);

            int n = nums.length;
            int m = 1;
            for (int i = 1; i < n; i++) {
                if (nums[i] != nums[i - 1]) {
                    nums[m++] = nums[i]; // 原地去重
                }
            }

            int result = 0;
            int left = 0;
            for (int i = 0; i < m; i++) {
                // nums[i], [num[i]-(n-1),nums[i]]
                while (nums[left] < nums[i] - n + 1) {
                    left++;
                }
                result = Math.max(result, i - left + 1);
            }
            return n - result;
        }
    }

    /**
     * 1600. 王位继承顺序
     */
    class ThroneInheritance_1600 {
        class Person {
            public Person(String name) {
                this.name = name;
            }

            String name;
            boolean isDead = false;
            List<Person> childs = null;
        }

        private Person king = null;
        private HashMap<String, Person> mapPerson = new HashMap<>();

        public ThroneInheritance_1600(String kingName) {
            king = new Person(kingName);
            mapPerson.put(kingName, king);
        }

        public void birth(String parentName, String childName) {
            Person person = mapPerson.get(parentName);
            if (person.childs == null) {
                person.childs = new ArrayList<>();
            }
            Person newChild = new Person(childName);
            person.childs.add(newChild);
            mapPerson.put(childName, newChild);
        }

        public void death(String name) {
            Person person = mapPerson.get(name);
            person.isDead = true;
        }

        public List<String> getInheritanceOrder() {
            List<String> result = new ArrayList<>();
            display(result, king);
            return result;
        }

        private void display(List<String> result, Person person) {
            if (!person.isDead) {
                result.add(person.name);
            }
            if (person.childs != null) {
                for (Person child : person.childs) {
                    display(result, child);
                }
            }
        }
    }

    /**
     * 1379. 找出克隆二叉树中的相同节点
     */
    static class Solution_1379 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            ArrayList<TreeNode> arrayList = new ArrayList<>();
            arrayList.add(cloned);
            while (!arrayList.isEmpty()) {
                TreeNode remove = arrayList.remove(0);
                if (remove.val == target.val) {
                    return remove;
                }
                if (remove.right != null) {
                    arrayList.add(remove.right);
                }
                if (remove.left != null) {
                    arrayList.add(remove.left);
                }
            }
            return null;
        }
    }

    /**
     * 107. 二叉树的层序遍历 II
     */
    static class Solution_107 {
        //Definition for a binary tree node.
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            List<TreeNode> nodes = new ArrayList<>();
            nodes.add(root);
            while (!nodes.isEmpty()) {
                int size = nodes.size();
                ArrayList<Integer> ints = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    TreeNode remove = nodes.remove(0);
                    ints.add(remove.val);
                    if (remove.left != null) {
                        nodes.add(remove.left);
                    }
                    if (remove.right != null) {
                        nodes.add(remove.right);
                    }
                }
                result.add(0, ints);
            }
            return result;
        }
    }

    /**
     * 894. 所有可能的真二叉树
     */
    static class Solution_894 {

        //Definition for a binary tree node.
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }


        public List<TreeNode> allPossibleFBT(int n) {
            ArrayList<TreeNode> treeNodes = new ArrayList<>();
            if (n % 2 == 0) {
                return treeNodes;
            }
            if (n == 1) {
                treeNodes.add(new TreeNode(0));
                return treeNodes;
            }
            for (int i = 1; i <= n - 2; i += 2) {
                TreeNode node = null;
                List<TreeNode> treeNodesLeft = allPossibleFBT(i);
                List<TreeNode> treeNodesRight = allPossibleFBT(n - i - 1);
                for (TreeNode leftNode : treeNodesLeft) {
                    for (TreeNode rightNode : treeNodesRight) {
                        node = new TreeNode(0, leftNode, rightNode);
                        treeNodes.add(node);
                    }
                }
            }
            return treeNodes;
        }
    }

    /**
     * 2810. 故障键盘
     */
    static class Solution_2810 {
        public String finalString(String s) {
            ArrayList<Character> result = new ArrayList<>();
            boolean insert2Last = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'i') {
                    insert2Last = !insert2Last;
                    continue;
                }
                if (insert2Last) {
                    result.add(c);
                } else {
                    result.add(0, c);
                }
            }
            StringBuilder sb = new StringBuilder();
            if (insert2Last) {
                for (int i = 0; i < result.size(); i++) {
                    sb.append(result.get(i));
                }
            } else {
                for (int i = result.size() - 1; i >= 0; i--) {
                    sb.append(result.get(i));
                }
            }
            return sb.toString();
        }
    }

    /**
     * 2908. 元素和最小的山形三元组 I
     */
    static class Solution_2908 {
        public int minimumSum(int[] nums) {
            int n = nums.length;
            int[] leftMin = new int[n];
            leftMin[0] = Integer.MAX_VALUE;
            int[] rightMin = new int[n];
            rightMin[n - 1] = Integer.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                leftMin[i] = Math.min(nums[i - 1], leftMin[i - 1]);
            }
            for (int i = n - 2; i >= 0; i--) {
                rightMin[i] = Math.min(rightMin[i + 1], nums[i + 1]);
            }
            int result = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (leftMin[i] < nums[i] && rightMin[i] < nums[i]) {
                    result = Math.min(result, leftMin[i] + nums[i] + rightMin[i]);
                }
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }

    /**
     * 1997. 访问完所有房间的第一天
     */
    static class Solution_1997 {
        public int firstDayBeenInAllRooms(int[] nextVisit) {
            int mod = 1_0000_0000_7;
            int n = nextVisit.length;
            long[] s = new long[n];
            for (int i = 1; i < n; i++) {
                int j = nextVisit[i - 1];
                s[i] = (s[i - 1] + s[i - 1] - s[j] + 2 + mod) % mod;
            }
            return (int) s[n - 1];
        }
    }

    /**
     * 2580. 统计将重叠区间合并成组的方案数
     */
    static class Solution_2580 {
        public int countWays(int[][] ranges) {
            int mod = 1_0000_0000_7;
            // 对左边界冒泡排序
            for (int i = ranges.length - 1; i > 0; i--) {
                boolean hasSwap = false;
                for (int j = 0; j < i; j++) {
                    if (ranges[j][0] > ranges[j + 1][0]) {
                        int[] temp = ranges[j];
                        ranges[j] = ranges[j + 1];
                        ranges[j + 1] = temp;
                        hasSwap = true;
                    }
                }
                if (!hasSwap) {
                    break;
                }
            }
            int result = 1;
            int curRight = -1;
            for (int i = 0; i < ranges.length; i++) {
                if (ranges[i][0] > curRight) {
                    result = (result * 2) % mod;
                }
                curRight = Math.max(curRight, ranges[i][1]);
            }
            return result;
        }

        public int fastPow(int i, int x, int mod) {
            long result = 1;
            while (x > 0) {
                if ((x & 1) == 1) {
                    result = result * i % mod;
                }
                i *= i;
                x >>= 1;
            }
            return (int) result;
        }
    }

    /**
     * 2642. 设计可以求最短路径的图类
     */
    static class Graph {


        int[][] map;
        private int n;
        private final int MAX = Integer.MAX_VALUE / 2;

        public Graph(int n, int[][] edges) {
            this.n = n;
            map = new int[n][n];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = i == j ? 0 : MAX;
                }
            }
            for (int[] edge : edges) {
                addEdge(edge);
            }
        }

        public void addEdge(int[] edge) {
            map[edge[0]][edge[1]] = edge[2];
        }

        public int shortestPath(int node1, int node2) {
            int[] dis = new int[n];
            for (int i = 0; i < dis.length; i++) {
                if (i == node1) {
                    dis[i] = 0;
                    continue;
                }

                dis[i] = MAX;
            }

            boolean[] hasSearched = new boolean[n];
            for (int i = 0; i < hasSearched.length; i++) {
                hasSearched[i] = false;
            }

            int x;
            while (true) {
                x = -1;
                for (int i = 0; i < n; i++) {
                    if (!hasSearched[i] && (x < 0 || dis[i] < dis[x])) {
                        x = i;
                    }
                }

                if (x < 0 || dis[x] == MAX) {
                    return -1;
                }

                if (x == node2) {
                    return dis[node2];
                }

                hasSearched[x] = true;
                for (int i = 0; i < n; i++) {
                    dis[i] = Math.min(dis[i], dis[x] + map[x][i]);
                }
            }
        }
    }


    /**
     * 518. 零钱兑换 II
     */
    static class Solution_518 {
        public int change(int amount, int[] coins) {
            int length = coins.length;
            int[][] dp = new int[length][amount + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    dp[i][j] = -1;
                }
            }
            return dfs(length - 1, amount, coins, dp);
        }

        private int dfs(int i, int amount, int[] coins, int[][] dp) {
            if (i < 0) {
                return amount == 0 ? 1 : 0;
            }
            if (dp[i][amount] != -1) {
                return dp[i][amount];
            }
            if (amount < coins[i]) {
                return dp[i][amount] = dfs(i - 1, amount, coins, dp);
            }
            return dp[i][amount] = dfs(i - 1, amount, coins, dp) + dfs(i, amount - coins[i], coins, dp);
        }
    }


    static class Solution_2617 {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public int minimumVisitedCells_planB(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            PriorityQueue<int[]>[] cols = new PriorityQueue[n];
            for (int i = 0; i < cols.length; i++) {
                cols[i] = new PriorityQueue<>((Comparator<int[]>) (n1, n2) -> {
                    return n1[0] - n2[0];
                });
            }
            PriorityQueue<int[]> row = new PriorityQueue<>((Comparator<int[]>) (n1, n2) -> {
                return n1[0] - n2[0];
            });
            int result = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                row.clear();
                for (int j = 0; j < n; j++) {
                    result = Integer.MAX_VALUE;
                    while (!row.isEmpty() && j > row.peek()[1]) {
                        row.poll();
                    }
                    if (!row.isEmpty()) {
                        result = row.peek()[0] + 1;
                    }
                    PriorityQueue<int[]> col = cols[j];
                    while (!col.isEmpty() && i > col.peek()[1]) {
                        col.poll();
                    }
                    if (!col.isEmpty()) {
                        result = Math.min(result, col.peek()[0] + 1);
                    }
                    if (i == 0 && j == 0) {
                        result = 1;
                    } else {
                        result = result == Integer.MAX_VALUE ? -1 : result;
                    }

                    if (result > 0) {
                        col.offer(new int[]{result, grid[i][j] + i});
                        row.offer(new int[]{result, grid[i][j] + j});
                    }
                }
            }
            return result;
        }

        public int minimumVisitedCells(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            return dsf(m - 1, n - 1, 1, grid, Integer.MAX_VALUE);
        }

        private int dsf(int m, int n, int curNodeCount, int[][] grid, int curMinCount) {
            if (m == 0 && n == 0) {
                return curNodeCount;
            }

            if (curNodeCount >= curMinCount) {
                return -1;
            }

            int minCount = curMinCount;
            for (int j = 0; j < n; j++) {
                if (grid[m][j] + j >= n) {
                    int dsf = dsf(m, j, curNodeCount + 1, grid, minCount);
                    if (dsf > 0) {
                        minCount = Math.min(dsf, minCount);
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                if (grid[i][n] + i >= m) {
                    int dsf = dsf(i, n, curNodeCount + 1, grid, minCount);
                    if (dsf > 0) {
                        minCount = Math.min(dsf, minCount);
                    }
                }
            }
            return minCount == Integer.MAX_VALUE ? -1 : minCount;
        }
    }


    /**
     * 2671. 频率跟踪器
     */
    static class FrequencyTracker {
        private final int N = 1_00001;
        private int[] frequencys = null;
        private HashMap<Integer, Integer> frequencysMap = new HashMap<>();

        public FrequencyTracker() {
            frequencys = new int[N];
            Arrays.fill(frequencys, 0);
        }

        public void add(int number) {
            Integer integer = frequencysMap.get(frequencys[number]);
            if (integer != null && integer > 1) {
                frequencysMap.put(frequencys[number], integer - 1);
            } else {
                frequencysMap.remove(frequencys[number]);
            }
            frequencys[number] += 1;
            Integer integerNew = frequencysMap.get(frequencys[number]);
            if (integerNew != null) {
                frequencysMap.put(frequencys[number], integerNew + 1);
            } else {
                frequencysMap.put(frequencys[number], 1);
            }
        }

        public void deleteOne(int number) {
            Integer integer = frequencysMap.get(frequencys[number]);
            if (integer != null && integer > 1) {
                frequencysMap.put(frequencys[number], integer - 1);
            } else {
                frequencysMap.remove(frequencys[number]);
            }
            frequencys[number] = Math.max(0, frequencys[number] - 1);
            Integer integerNew = frequencysMap.get(frequencys[number]);
            if (integerNew != null) {
                frequencysMap.put(frequencys[number], integerNew + 1);
            } else {
                frequencysMap.put(frequencys[number], 1);
            }
        }

        public boolean hasFrequency(int frequency) {
            Integer integer = frequencysMap.get(frequency);
            return integer != null && integer > 0;
        }
    }

    /**
     * 1969. 数组元素的最小非零乘积
     * <p>
     * <p>
     * 输入：p = 3
     * 输出：1512
     * 解释：nums = [001, 010, 011, 100, 101, 110, 111]
     * - 第一次操作中，我们交换第二个和第五个元素最左边的数位。
     * - 结果数组为 [001, 110, 011, 100, 001, 110, 111] 。
     * - 第二次操作中，我们交换第三个和第四个元素中间的数位。
     * - 结果数组为 [001, 110, 001, 110, 001, 110, 111] 。
     * 数组乘积 1 * 6 * 1 * 6 * 1 * 6 * 7 = 1512 是最小乘积。
     */
    static class Solution_1969 {
        public int minNonZeroProduct(int p) {
            int mod = 1_0000_0000_7;
            long pow_2_p = fastPow(2, p, mod) % mod;
            long pow_2_p_1 = pow_2_p / 2;
            return (int) ((pow_2_p - 1) * fastPow(pow_2_p - 2, pow_2_p_1 - 1, mod) % mod);
        }

        public long fastPow(long x, long t, int mod) {
            long res = 1;
            while (t > 0) {
                if ((t & 1) == 1) {
                    res = res * x % mod;
                }
                x = x * x % mod;
                t = t >> 1;
            }
            return res;
        }
    }

    /**
     * 1793. 好子数组的最大分数
     */
    static class Solution_1793 {
        public int maximumScore(int[] nums, int k) {
            // 单调栈
            int n = nums.length;
            int[] left = new int[n];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                while (!stack.isEmpty() && x <= nums[stack.peek()]) {
                    stack.pop();
                }
                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }

            stack.clear();
            int[] right = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                int x = nums[i];
                while (!stack.isEmpty() && x <= nums[stack.peek()]) {
                    stack.pop();
                }
                right[i] = stack.isEmpty() ? n : stack.peek();
                stack.push(i);
            }

            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int h = nums[i];
                int l = left[i];
                int r = right[i];
                if (l < k && k < r) {
                    maxScore = Math.max(maxScore, h * (r - l - 1));
                }
            }
            return maxScore;
        }
    }

    /**
     * 2316. 统计无向图中无法互相到达点对数
     */
    static class Solution_2316 {
        public long countPairs(int n, int[][] edges) {

            // 遍历得出路径拓扑
            ArrayList<Integer>[] G = new ArrayList[n];
            for (int i = 0; i < G.length; i++) {
                G[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                int x = edge[0];
                int y = edge[1];
                G[x].add(y);
                G[y].add(x);
            }

            // 记录哪些点还没有被遍历
            HashSet<Integer> unVisitor = new HashSet<>();
            for (int i = 0; i < n; i++) {
                unVisitor.add(i);
            }

            long result = 0;
            long sumPreAll = 0;

            // 挨个遍历得到每个独立子树
            for (int i = 0; i < n; i++) {
                if (unVisitor.contains(i)) {
                    int size = dsf(unVisitor, i, G);
                    result += sumPreAll * size;
                    sumPreAll += size;
                }
            }
            return result;
        }

        private int dsf(HashSet<Integer> unVisitor, int i, ArrayList<Integer>[] g) {
            unVisitor.remove(i);
            int count = 1;
            for (Integer integer : g[i]) {
                if (unVisitor.contains(integer)) {
                    count += dsf(unVisitor, integer, g);
                }
            }
            return count;
        }

        public long countPairs_planB(int n, int[][] edges) {
            UnionSearch unionSearch = new UnionSearch(n);
            for (int i = 0; i < edges.length; i++) {
                unionSearch.union(edges[i][0], edges[i][1]);
            }
            long result = 0;
            for (int i = 0; i < n; i++) {
                result += n - unionSearch.getSize(unionSearch.findRoot(i));
            }
            return result / 2;
        }

        // 并查集实现
        static class UnionSearch {
            int[] parents;
            int[] sizes;

            public UnionSearch(int n) {
                this.parents = new int[n];
                for (int i = 0; i < parents.length; i++) {
                    parents[i] = i;
                }
                sizes = new int[n];
                for (int i = 0; i < sizes.length; i++) {
                    sizes[i] = 1;
                }
            }

            public int findRoot(int x) {
                // 自己是自己的Root直接返回
                if (parents[x] == x) {
                    return x;
                }
                // 对parent结构压缩
                else {
                    parents[x] = findRoot(parents[x]);
                    return parents[x];
                }
            }

            public void union(int x, int y) {
                int rx = findRoot(x);
                int ry = findRoot(y);
                if (rx != ry) {
                    // 少的,往多的合并
                    if (sizes[ry] < sizes[rx]) {
                        parents[ry] = rx;
                        sizes[rx] += sizes[ry];
                    } else {
                        parents[rx] = ry;
                        sizes[ry] += sizes[rx];
                    }
                }
            }

            public int getSize(int x) {
                return sizes[x];
            }
        }
    }

    /**
     * 303. 区域和检索 - 数组不可变
     */
    static class NumArray {

        private long[] sum = null;

        public NumArray(int[] nums) {
            sum = new long[nums.length];

            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    sum[i] = nums[i];
                } else {
                    sum[i] = nums[i] + sum[i - 1];
                }
            }
        }

        public int sumRange(int left, int right) {
            if (left - 1 >= 0) {
                return (int) (sum[right] - sum[left - 1]);
            }
            return (int) sum[right];
        }
    }

    /**
     * 2312. 卖木头块
     */
    static class Solution_2312 {
        public long sellingWood(int m, int n, int[][] prices) {
            long[][] result = new long[m + 1][n + 1];
            for (int i = 0; i < prices.length; i++) {
                int[] priceItem = prices[i];
                int x = priceItem[0];
                int y = priceItem[1];
                result[x][y] = priceItem[2];
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int k = 1; k < j; k++) {
                        result[i][j] = Math.max(result[i][j], result[i][k] + result[i][j - k]);
                    }
                    for (int k = 1; k < i; k++) {
                        result[i][j] = Math.max(result[i][j], result[k][j] + result[i - k][j]);
                    }
                }
            }
            return result[m][n];
        }
    }

    /**
     * 2386. 找出数组的第 K 大和
     */
    static class Solution_2386 {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public long kSum(int[] nums, int k) {

            long maxSum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    maxSum += nums[i];
                } else {
                    nums[i] = -nums[i];
                }
            }

            // 对nums排序,小的放前面 (排序方式:冒泡)
            for (int i = nums.length - 1; i >= 0; i--) {
                boolean hasSwap = false;
                for (int j = 0; j < i; j++) {
                    if (nums[j] > nums[j + 1]) {
                        swap(nums, j, j + 1);
                        hasSwap = true;
                    }
                }
                if (!hasSwap) {
                    break;
                }
            }

            PriorityQueue<Pair<Long, Integer>> priorityQueue = new PriorityQueue<Pair<Long, Integer>>((Comparator<Pair<Long, Integer>>) (n1, n2) -> {
                if (n1.first < n2.first) {
                    return -1;
                } else if (n1.first > n2.first) {
                    return 1;
                } else {
                    return 0;
                }
            });

            priorityQueue.offer(new Pair<>(0L, 0));
            while (--k > 0) {
                Pair<Long, Integer> poll = priorityQueue.poll();
                Long sum = poll.first;
                int index = poll.second;
                if (index < nums.length) {
                    priorityQueue.offer(new Pair<>(sum + nums[index], index + 1));
                    if (index > 0) {
                        priorityQueue.offer(new Pair<>(sum + nums[index] - nums[index - 1], index + 1));
                    }
                }
            }
            return maxSum - priorityQueue.poll().first;
        }

        private void swap(int[] nums, int j, int i) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }

        public static class Pair<F, S> {
            public final F first;
            public final S second;

            /**
             * Constructor for a Pair.
             *
             * @param first  the first object in the Pair
             * @param second the second object in the pair
             */
            public Pair(F first, S second) {
                this.first = first;
                this.second = second;
            }
        }
    }

    /**
     * 2834. 找出美丽数组的最小和
     */
    static class Solution_2834 {
        public int minimumPossibleSum(int n, int target) {
            int mod = 1_0000_0000_7;
            long result = 0;
            if (target / 2 >= n) {
                return (int) (((1L + n) * n / 2L) % mod);
            }

            int m = target / 2;
            result = ((1L + m) * m / 2L);
            long right = (((long) target + target + n - m - 1) * (n - m) / 2) % mod;
            result = (result + right) % mod;
            return (int) result;
        }
    }

    /**
     * 2731. 移动机器人
     */
    static class Solution_2731 {
        public int sumDistance(int[] nums, String s, int d) {
            int length = nums.length;
            long[] newPos = new long[length];
            for (int i = 0; i < length; i++) {
                int addNum = s.charAt(i) == 'R' ? 1 : -1;
                newPos[i] = addNum * d + nums[i];
            }
            // 插入排序
            for (int i = 1; i < length; i++) {
                int cur = i;
                long targetValue = newPos[cur];
                while (cur > 0 && targetValue < newPos[cur - 1]) {
                    newPos[cur] = newPos[cur - 1];
                    cur--;
                }
                newPos[cur] = targetValue;
            }

            long result = 0;
            for (int i = 1; i < length; i++) {
                result += (newPos[i] - newPos[i - 1]) * i % 1_0000_0000_7 * (length - 1 - i + 1) % 1_0000_0000_7;
                result %= 1_0000_0000_7;
            }
            return (int) result;
        }
    }

    /**
     * 2575. 找出字符串的可整除数组
     */
    static class Solution_2575 {
        public int[] divisibilityArray(String word, int m) {
            int n = word.length();
            int[] result = new int[n];
            long remainder = 0;
            for (int i = 0; i < n; i++) {
                int charNum = Integer.parseInt(String.valueOf(word.charAt(i)));
                long curNum = charNum + remainder * 10;
                remainder = curNum % m;
                result[i] = remainder == 0 ? 1 : 0;
            }
            return result;
        }
    }

    /**
     * 2578. 最小和分割
     */
    static class Solution_2578 {
        public int splitNum(int num) {
            String numStr = num + "";
            int length = numStr.length();
            List<Integer> sortNumItem = new ArrayList<>(length);
            // 插入排序
            for (int i = 0; i < length; i++) {
                int j = Integer.parseInt(String.valueOf(numStr.charAt(i)));
                if (sortNumItem.isEmpty()) {
                    sortNumItem.add(j);
                } else {
                    for (int k = sortNumItem.size() - 1; k >= 0; k--) {
                        if (sortNumItem.get(k) <= j) {
                            sortNumItem.add(k + 1, j);
                            break;
                        } else if (k == 0) {
                            sortNumItem.add(0, j);
                        }
                    }
                }
            }

            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < sortNumItem.size(); i++) {
                if (i % 2 == 0) {
                    num1Str.append(sortNumItem.get(i));
                } else {
                    num2Str.append(sortNumItem.get(i));
                }
            }
            int i = Integer.parseInt(num1Str.toString()) + Integer.parseInt(num2Str.toString());
            return i;
        }


        public int splitNum_planB(int num) {
            String numStr = num + "";
            int length = numStr.length();
            int[] cnt = new int[10];
            for (int i = 0; i < length; i++) {
                int item = num % 10;
                cnt[item]++;
                num = num / 10;
            }
            int cntIndex = 0;
            int num1 = 0;
            int num2 = 0;
            for (int i = 0; i < length; i++) {
                int curNum = -1;
                for (int j = cntIndex; j < cnt.length; j++) {
                    if (cnt[j] > 0) {
                        curNum = j;
                        cnt[j] -= 1;
                        break;
                    }
                }
                if (curNum < 0) {
                    break;
                }
                if (i % 2 == 0) {
                    num1 = num1 * 10 + curNum;
                } else {
                    num2 = num2 * 10 + curNum;
                }
            }
            return num1 + num2;
        }
    }

    /**
     * 2917. 找出数组中的 K-or 值
     */
    static class Solution_2917 {
        /**
         * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
         * <p>
         * nums 中的 K-or 是一个满足以下条件的非负整数：
         * <p>
         * 只有在 nums 中，至少存在 k 个元素的第 i 位值为 1 ，那么 K-or 中的第 i 位的值才是 1 。
         * 返回 nums 的 K-or 值。
         * <p>
         * 注意 ：对于整数 x ，如果 (2i AND x) == 2i ，则 x 中的第 i 位值为 1 ，其中 AND 为按位与运算符。
         */

        public int findKOr(int[] nums, int k) {
            int result = 0;
            int curCount = 0;
            int curBit = 1;
            for (int j = 0; j < 32; j++) {
                curCount = 0;
                for (int num : nums) {
                    if ((num & curBit) == curBit) {
                        curCount++;
                    }
                }
                if (curCount >= k) {
                    result += curBit;
                }
                curBit = curBit << 1;
            }
            return result;
        }

    }

    static class Solution_1976 {

        public int countPaths_PlanB(int n, int[][] roads) {
            //定义邻接矩阵
            long[][] g = new long[n][n];
            //初始化邻接矩阵
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g[i][j] = Long.MAX_VALUE / 2;
                }
            }
            for (int[] road : roads) {
                int i = road[0];
                int j = road[1];
                int time = road[2];
                g[i][j] = time;
                g[j][i] = time;
            }

            // 0到数组下标节点的距离
            long[] dis = new long[n];
            for (int i = 1; i < dis.length; i++) {
                dis[i] = Long.MAX_VALUE / 2;
            }
            dis[0] = 0;

            // 0到数组下标节点的路径数量
            int[] pathCount = new int[n];
            pathCount[0] = 1;

            // 标记下标节点是否移动到S分组中, Dijkstra算法分为S和T两组
            boolean[] isInSGroup = new boolean[n];

            while (true) {
                int x = -1;
                for (int i = 0; i < n; i++) {
                    if (!isInSGroup[i] && (x < 0 || dis[i] < dis[x])) {
                        x = i;
                    }
                }

                if (x == n - 1) {
                    return pathCount[n - 1];
                }

                isInSGroup[x] = true;
                for (int j = 0; j < n; j++) {
                    long newDis = dis[x] + g[x][j];

                    if (newDis < dis[j]) {
                        dis[j] = newDis;
                        pathCount[j] = pathCount[x];
                    } else if (newDis == dis[j]) {
                        pathCount[j] = (pathCount[j] + pathCount[x]) % 1_0000_0000_7;
                    }
                }
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public int countPaths_PlanC(int n, int[][] roads) {
            // 邻接关系网
            List<int[]>[] g = new ArrayList[n];
            for (int i = 0; i < g.length; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] road : roads) {
                int i = road[0];
                int j = road[1];
                int dis = road[2];
                g[i].add(new int[]{j, dis});
                g[j].add(new int[]{i, dis});
            }

            // 路径数量记录
            int[] pathCount = new int[n];
            pathCount[0] = 1;

            // dis距离记录
            long[] dis = new long[n];
            for (int i = 1; i < dis.length; i++) {
                dis[i] = Long.MAX_VALUE / 2;
            }
            dis[0] = 0;

            // 查找最小dis值对应的节点node
            PriorityQueue<Long[]> minNodes = new PriorityQueue<>((ajq1, ajq2) -> {
                return Long.compare(ajq1[0], ajq2[0]);
            });
            minNodes.offer(new Long[]{0L, 0L});

            while (!minNodes.isEmpty()) {
                Long[] poll = minNodes.poll();
                long xdis = poll[0];
                int x = Math.toIntExact(poll[1]);

                if (xdis > dis[x]) {
                    continue;
                }

                for (int[] gg : g[x]) {
                    int y = gg[0];
                    int yDis = gg[1];
                    long newYDis = xdis + yDis;
                    if (newYDis < dis[y]) {
                        dis[y] = newYDis;
                        pathCount[y] = pathCount[x];
                        minNodes.offer(new Long[]{newYDis, (long) y});
                    } else if (newYDis == dis[y]) {
                        pathCount[y] = (pathCount[y] + pathCount[x]) % 1_0000_0000_7;
                    }
                }
            }
            return (int) pathCount[n - 1];
        }

        public int countPaths(int n, int[][] roads) {

            List<Integer>[] nodes = new List[n];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new ArrayList<>();
            }
            for (int[] road : roads) {
                int i = road[0];
                int j = road[1];
                int time = road[2];
                nodes[i].add(j);
                nodes[i].add(time);
                nodes[j].add(i);
                nodes[j].add(time);
            }

            int initRoadCount = 0;
            int initRoadTime = Integer.MAX_VALUE;
            int[] result = new int[]{initRoadCount, initRoadTime};
            HashSet<Integer> paths = new HashSet<>();
            dfs(nodes, 0, n - 1, ",-1,", result, 0);
            return result[0];
        }

        private void dfs(List<Integer>[] nodes, int start, int target, String paths, int[] result, int curTime) {
            System.out.println("start=" + start + ", target=" + target);
            if (start == target) {
                if (curTime < result[1]) {
                    result[0] = 1;
                    result[1] = curTime;
                } else if (curTime == result[1]) {
                    result[0] += 1;
                }
                System.out.println("return!");
                return;
            }
            List<Integer> node = nodes[start];
            for (int i = 0; i < node.size() - 1; i += 2) {
                int nextNode = node.get(i);
                int nextNodeTime = node.get(i + 1);
                if (!paths.contains("," + nextNode + ",")) {
                    dfs(nodes, nextNode, target, paths + (nextNode + ","), result, curTime + nextNodeTime);
                }
            }
        }
    }

    static class Solution_901 {
        class StockSpanner {
            private ArrayList<Integer> prices = new ArrayList<>();
            private HashMap<Integer, Integer> pricesIndex2Span = new HashMap<>();

            public StockSpanner() {

            }

            public int next(int price) {
                prices.add(price);
                //计算当前span值
                int span = 1;
                int curIndex = prices.size() - 2;
                while (curIndex >= 0) {
                    if (price >= prices.get(curIndex)) {
                        Integer spanOfCurIndex = pricesIndex2Span.get(curIndex);
                        span += spanOfCurIndex;
                        curIndex -= spanOfCurIndex;
                    } else {
                        curIndex = -1;
                    }
                }
                pricesIndex2Span.put(prices.size() - 1, span);
                return span;
            }
        }
    }

    static class Solution_232 {
        class MyQueue {
            private Stack<Integer> stack1 = new Stack<Integer>();
            private Stack<Integer> stack2 = new Stack<Integer>();

            public MyQueue() {

            }

            public void push(int x) {
                stack2.push(x);
            }

            public int pop() {
                if (stack1.size() <= 0) {
                    while (!stack2.isEmpty()) {
                        stack1.push(stack2.pop());
                    }
                }
                return stack1.pop();
            }

            public int peek() {
                if (stack1.size() <= 0) {
                    while (!stack2.isEmpty()) {
                        stack1.push(stack2.pop());
                    }
                }
                return stack1.peek();
            }

            public boolean empty() {
                return stack1.empty() && stack2.empty();
            }
        }
    }

    /**
     * 2369. 检查数组是否存在有效划分
     */
    static class Solution_2369 {
        // 使用动态规划的解决方案
        // 长度位n的数组是否存在有效划分的结果依赖
        // 长度n-2的数组划分结果
        // || 长度n-3的数组划分结果
        public boolean validPartition(int[] nums) {
            int n = nums.length + 1;
            boolean[] cacheArr = new boolean[n];
            cacheArr[0] = true;
            for (int i = 1; i < n; i++) {
                if (i - 2 >= 0 && cacheArr[i - 2] && nums[i - 1] == nums[i - 2]) {
                    cacheArr[i] = true;
                } else if (i - 3 >= 0 && cacheArr[i - 3] && nums[i - 1] == nums[i - 2] && nums[i - 1] == nums[i - 3]) {
                    cacheArr[i] = true;
                } else if (i - 3 >= 0 && cacheArr[i - 3] && nums[i - 1] == nums[i - 2] + 1 && nums[i - 1] == nums[i - 3] + 2) {
                    cacheArr[i] = true;
                }
            }
            return cacheArr[nums.length];
        }

        // 超时了
        public boolean validPartition_planB(int[] nums) {
            // 2个相同 3个相同 3连续
            return dfs(nums, 0);
        }

        private boolean dfs(int[] nums, int startIndex) {
            if (startIndex == nums.length) {
                // 一组有效遍历结束
                return true;
            }
            if (startIndex == nums.length - 1 || startIndex < 0) {
                return false;
            }
            if (nums[startIndex] == nums[startIndex + 1]) {
                System.out.println("[" + nums[startIndex] + "," + nums[startIndex + 1] + "]");
                // 找到2个相同,继续往下
                if (dfs(nums, startIndex + 2)) {
                    return true;
                }
            }

            if (startIndex + 2 < nums.length && nums[startIndex] == nums[startIndex + 1] && nums[startIndex] == nums[startIndex + 2]) {
                System.out.println("[" + nums[startIndex] + "," + nums[startIndex + 1] + "," + nums[startIndex + 2] + "]");
                // 找到3个相同,继续往下
                if (dfs(nums, startIndex + 3)) {
                    return true;
                }
            }

            if (startIndex + 2 < nums.length && nums[startIndex] + 1 == nums[startIndex + 1] && nums[startIndex] + 2 == nums[startIndex + 2]) {
                System.out.println("[" + nums[startIndex] + "," + nums[startIndex + 1] + "," + nums[startIndex + 2] + "]");
                //找到三个连续,继续往下
                if (dfs(nums, startIndex + 3)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 2581. 统计可能的树根数目
     */
    static class Solution_2581 {

        public int rootCount(int[][] edges, int[][] guesses, int k) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int[] guess : guesses) {
                map.put(guess[0] + "," + guess[1], 1);
            }
            int n = edges.length + 1;

            List<Integer>[] G = new List[n];
            for (int i = 0; i < G.length; i++) {
                G[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {
                int i = edge[0];
                int j = edge[1];
                G[i].add(j);
                G[j].add(i);
            }

            int result = 0;

            // 广度遍历获取动态规划第一步结果,初始节点为0时
            int kCount = df_search(G, 0, -1, map);
            if (kCount >= k) {
                result++;
            }

            // 从0节点开始进行广度偏移
            result += df_move(G, 0, -1, map, kCount, k);

            return result;
        }

        private int df_move(List<Integer>[] g, int start, int exclude, HashMap<String, Integer> map, int curKCount, int k) {
            int result = 0;
            for (Integer i : g[start]) {
                if (i != exclude) {
                    String oldKey = start + "," + i;
                    String newKey = i + "," + start;
                    boolean containsOldKey = map.containsKey(oldKey);
                    boolean containsNewKey = map.containsKey(newKey);
                    int newKCount = curKCount;
                    if (containsOldKey && !containsNewKey) {
                        newKCount -= 1;
                    } else if (!containsOldKey && containsNewKey) {
                        newKCount += 1;
                    }
                    if (newKCount >= k) {
                        result += 1;
                    }
                    result += df_move(g, i, start, map, newKCount, k);
                }
            }
            return result;
        }

        private int df_search(List<Integer>[] g, int start, int exclude, HashMap<String, Integer> map) {
            int result = 0;
            for (Integer i : g[start]) {
                if (i != exclude) {
                    String key = start + "," + i;
                    if (map.get(key) != null) {
                        result += 1;
                    }
                    result += df_search(g, i, start, map);
                }
            }
            return result;
        }

        /**
         * 时间上不合格
         *
         * @param edges
         * @param guesses
         * @param k
         * @return
         */
        public int rootCount_PlanB(int[][] edges, int[][] guesses, int k) {
            int n = edges.length + 1;

            List<Integer>[] G = new List[n];
            for (int i = 0; i < G.length; i++) {
                G[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {
                int i = edge[0];
                int j = edge[1];
                G[i].add(j);
                G[j].add(i);
            }

            int result = 0;
            int[] floor = new int[n];
            for (int i = 0; i < n; i++) {
                int count = 0;
                //构建节点层数关系
                bfs(G, i, -1, floor, 1);
                for (int[] guess : guesses) {
                    if (floor[guess[0]] < floor[guess[1]]) {
                        count++;
                        if (count >= k) {
                            result++;
                            break;
                        }
                    }
                }
            }

            return result;
        }

        private void bfs(List<Integer>[] g, int root, int exclude, int[] floor, int curFloor) {
            floor[root] = curFloor;
            for (Integer i : g[root]) {
                if (i != exclude) {
                    bfs(g, i, root, floor, curFloor + 1);
                }
            }
        }


    }

    /**
     * 2673. 使二叉树所有路径值相等的最小代价
     * 中等
     * 相关标签
     * 相关企业
     * 提示
     * 给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。
     * <p>
     * 树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。
     * <p>
     * 你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。
     * <p>
     * 注意：
     * <p>
     * 满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个子节点，且所有叶子节点距离根节点距离相同。
     * 路径值 指的是路径上所有节点的值之和。
     * <p>
     * <p>
     * 输入：n = 7, cost = [1,5,2,2,3,3,1]
     * 输出：6
     * 解释：我们执行以下的增加操作：
     * - 将节点 4 的值增加一次。
     * - 将节点 3 的值增加三次。
     * - 将节点 7 的值增加两次。
     * 从根到叶子的每一条路径值都为 9 。
     * 总共增加次数为 1 + 3 + 2 = 6 。
     * 这是最小的答案。
     */
    static class Solution_2673 {
        public int minIncrements(int n, int[] cost) {
            int result = 0;
            // 计算二叉树层数, 满二叉树层数一定是整数(层数从1开始)
            int floor = (int) (Math.log(n + 1) / Math.log(2));

            // 按层数倒序遍历,广度优先
            for (int curFloor = floor; curFloor > 1; curFloor--) {

                // 计算当前层第一个节点编号
                int i = ((int) Math.pow(2, curFloor - 1));
                int curFloorCount = i;
                for (int j = 0; j < curFloorCount / 2; j++) {
                    int indexLeft = i + 2 * j - 1;
                    int indexRight = i + 2 * j;
                    int parentIndex = (indexRight) / 2 - 1;
                    int abs = Math.abs(cost[indexLeft] - cost[indexRight]);
                    result += abs;
                    cost[parentIndex] += Math.max(cost[indexLeft], cost[indexRight]);
                }
            }
            return result;
        }
    }

    /**
     * 204. 计数质数
     * 尝试过
     * 中等
     * 相关标签
     * 相关企业
     * 提示
     * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 示例 2：
     * <p>
     * 输入：n = 0
     * 输出：0
     * 示例 3：
     * <p>
     * 输入：n = 1
     * 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= n <= 5 * 106
     */
    static class Solution_204 {
        public int countPrimes(int n) {

            // 埃氏筛
            int[] arr = new int[n];
            Arrays.fill(arr, 1);
            int res = 0;
            for (int i = 2; i < n; i++) {
                if (arr[i] == 1) {
                    res++;
                    if ((long) i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            System.out.println("j=" + j + ",n=" + n);
                            arr[j] = 0;
                        }
                    }
                }
            }
            return res;
        }
    }

    /**
     * 2867. 统计树中的合法路径数目
     * 困难
     * 相关标签
     * 相关企业
     * 提示
     * 给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。
     * <p>
     * 请你返回树中的 合法路径数目 。
     * <p>
     * 如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。
     * <p>
     * 注意：
     * <p>
     * 路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
     * 路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。
     */
    static class Solution_2867 {
        int N = 100001;
        boolean[] prime = new boolean[N];

        {
            Arrays.fill(prime, true);
            prime[1] = false;
            for (int i = 2; i * i < N; i++) {
                if (prime[i]) {
                    for (int j = i * i; j < N; j += i) {
                        prime[j] = false;
                    }
                }
            }
        }

        public long countPaths(int n, int[][] edges) {


            // 分析每个节点的第一层通路,存储在G中
            List<Integer>[] G = new ArrayList[n + 1];
            for (int i = 0; i < G.length; i++) {
                G[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                int i = edge[0];
                int j = edge[1];
                G[i].add(j);
                G[j].add(i);
            }


            long result = 0;
            int cur = 0;
            List<Integer> seachArr = new ArrayList<>();
            // 存储计算的每个子树的节点数量
            int[] count = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!prime[i]) {
                    continue;
                }

                cur = 0;
                // 开始遍历这个素数的每个子树
                for (Integer j : G[i]) {
                    if (prime[j]) {
                        continue;
                    }

                    if (count[j] == 0) {
                        seachArr.clear();
                        deepFirstSearch(G, j, seachArr, i);
                        for (Integer node : seachArr) {
                            count[node] = seachArr.size();
                        }
                    }
                    result += count[j] * cur;
                    cur += count[j];
                }

                result += cur;
            }
            return result;
        }

        private void deepFirstSearch(List<Integer>[] G, Integer i, List<Integer> seachArr, int pre) {
            seachArr.add(i);
            for (Integer j : G[i]) {
                if (j != pre && !prime[j]) {
                    deepFirstSearch(G, j, seachArr, i);
                }
            }
        }

        public boolean isPrime(int value) {
            if (value <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(value); i++) {
                if (value % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 2. 两数相加
     * 中等
     * 相关标签
     * 相关企业
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    class Solution_2 {

        public class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(0);
            ListNode p = result;
            ListNode p1 = l1;
            ListNode p2 = l2;
            while (p1 != null || p2 != null) {
                int p1Value = p1 == null ? 0 : p1.val;
                int p2Value = p2 == null ? 0 : p2.val;
                int addValue = p.val + p1Value + p2Value;
                p.val = addValue % 10;
                int nextLevelNum = addValue / 10;
                p1 = p1 == null ? null : p1.next;
                p2 = p2 == null ? null : p2.next;
                if (nextLevelNum > 0 || p1 != null || p2 != null) {
                    p.next = new ListNode(nextLevelNum);
                }
                p = p.next;
            }

            return result;
        }
    }

    /**
     * 938. 二叉搜索树的范围和
     * 简单
     * 相关标签
     * 相关企业
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     */
    class Solution_938 {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public int rangeSumBST(TreeNode root, int low, int high) {
            return rangeSumBST_Recursive(root, low, high);
        }

        public int rangeSumBST_Recursive(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }
            if (root.val < low) {
                return rangeSumBST_Recursive(root.right, low, high);
            } else if (root.val <= high) {
                int left = rangeSumBST_Recursive(root.left, low, high);
                int right = rangeSumBST_Recursive(root.right, low, high);
                return root.val + left + right;
            } else {
                return rangeSumBST_Recursive(root.left, low, high);
            }
        }
    }

    /**
     * 2583. 二叉树中的第 K 大层和
     * 中等
     * 相关标签
     * 相关企业
     * 提示
     * 给你一棵二叉树的根节点 root 和一个正整数 k 。
     * <p>
     * 树中的 层和 是指 同一层 上节点值的总和。
     * <p>
     * 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。
     * <p>
     * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
     */
    class kthLargestLevelSum_2583 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public long kthLargestLevelSum(TreeNode root, int k) {
            HashMap<Integer, Long> result = new HashMap<>();

            kthLargestLevelSum_Recursive(root, 1, result);
            if (result.get(k - 1) == null) {
                return -1;
            }

            // 插入排序
            long[] sortArr = new long[k];
            for (Long value : result.values()) {
                if (value == null) {
                    continue;
                }
                int index = k - 1;
                while (index >= 0 && value > sortArr[index]) {
                    if (index < k - 1) {
                        //do swap
                        sortArr[index + 1] = sortArr[index];
                    }
                    index--;
                }
                index++;
                if (index < k) {
                    sortArr[index] = value;
                }
            }
            return sortArr[k - 1];
        }

        public void kthLargestLevelSum_Recursive(TreeNode root, int curFloor, HashMap<Integer, Long> result) {
            if (root == null) {
                return;
            }
            int curIndex = curFloor - 1;
            Long integer = result.get(curIndex);
            long preValue = integer == null ? 0 : integer;
            result.put(curIndex, preValue + root.val);
            kthLargestLevelSum_Recursive(root.left, curFloor + 1, result);
            kthLargestLevelSum_Recursive(root.right, curFloor + 1, result);
        }
    }

    /**
     * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
     * <p>
     * 如果存在多个答案，您可以返回其中 任何 一个。
     */
    class BuildTree_899 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            HashMap<Integer, Integer> valueMapIndexOfPostorder = new HashMap<>();
            for (int i = 0; i < postorder.length; i++) {
                valueMapIndexOfPostorder.put(postorder[i], i);
            }
            return constructFromPrePostRecursive(valueMapIndexOfPostorder, preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
        }

        private TreeNode constructFromPrePostRecursive(HashMap<Integer, Integer> valueMapIndexOfPostorder, int[] preorder, int[] postorder, int preorder_left, int preorder_right, int postorder_left, int postorder_right) {
            if (preorder_left > preorder_right || postorder_left > postorder_right) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[preorder_left]);
            // 还有左树
            if (preorder_left + 1 <= preorder_right) {
                int leftTreeLength = valueMapIndexOfPostorder.get(preorder[preorder_left + 1]) - postorder_left + 1;
                root.left = constructFromPrePostRecursive(valueMapIndexOfPostorder, preorder, postorder, preorder_left + 1, preorder_left + leftTreeLength, postorder_left, postorder_left + leftTreeLength - 1);

                // 还有右树
                if (preorder_left + leftTreeLength + 1 <= preorder_right) {
                    root.right = constructFromPrePostRecursive(valueMapIndexOfPostorder, preorder, postorder, preorder_left + leftTreeLength + 1, preorder_right, postorder_left + leftTreeLength, postorder_right - 1);
                }
            }

            return root;
        }
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     */
    class BuildTree_106 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public TreeNode buildTree_106(int[] inorder, int[] postorder) {
            if (inorder == null || inorder.length == 0) {
                return null;
            }
            HashMap<Integer, Integer> valueIndexInInorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                valueIndexInInorderMap.put(inorder[i], i);
            }
            return buildTreeNodeRecursive(valueIndexInInorderMap, inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        }

        public TreeNode buildTreeNodeRecursive(HashMap<Integer, Integer> valueIndexInInorderMap, int[] inorder, int[] postorder, int inorderLeft, int inorderRight, int postorderLeft, int postorderRight) {
            if (inorderLeft > inorderRight || postorderLeft > postorderRight) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorderRight]);
            int indexOfRootInInorder = valueIndexInInorderMap.get(root.val);
            int leftTreeLength = indexOfRootInInorder - inorderLeft;
            root.left = buildTreeNodeRecursive(valueIndexInInorderMap, inorder, postorder, inorderLeft, indexOfRootInInorder - 1, postorderLeft, postorderLeft + leftTreeLength - 1);
            root.right = buildTreeNodeRecursive(valueIndexInInorderMap, inorder, postorder, indexOfRootInInorder + 1, inorderRight, postorderLeft + leftTreeLength, postorderRight - 1);
            return root;
        }
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     */
    class BuildTree_105 {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        // preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        public TreeNode buildTree_105(int[] preorder, int[] inorder) {
            HashMap<Integer, Integer> inOrderValueIndexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inOrderValueIndexMap.put(inorder[i], i);
            }
            return buildTreeRecursive(inOrderValueIndexMap, preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        public TreeNode buildTreeRecursive(HashMap<Integer, Integer> inOrderValueIndexMap, int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
            TreeNode root = new TreeNode(preorder[preorder_left]);
            int indexOfRootInInOrderArr = inOrderValueIndexMap.get(root.val);
            int leftTreeLength = indexOfRootInInOrderArr - inorder_left;
            if (leftTreeLength > 0) {
                root.left = buildTreeRecursive(inOrderValueIndexMap, preorder, inorder, preorder_left + 1, preorder_left + 1 + leftTreeLength - 1, inorder_left, indexOfRootInInOrderArr - 1);
            }
            int rightTreeLength = inorder_right - indexOfRootInInOrderArr;
            if (rightTreeLength > 0) {
                root.right = buildTreeRecursive(inOrderValueIndexMap, preorder, inorder, preorder_left + 1 + leftTreeLength - 1 + 1, preorder_right, indexOfRootInInOrderArr + 1, inorder_right);
            }
            return root;
        }

        public TreeNode buildTree_105_planB(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length <= 0) {
                return null;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode result = new TreeNode(preorder[0]);
            stack.push(result);
            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int newValue = preorder[i];
                TreeNode peek = stack.peek();
                if (peek.val != inorder[inorderIndex]) {
                    TreeNode cur = new TreeNode(newValue);
                    peek.left = cur;
                    stack.push(cur);
                    continue;
                }

                TreeNode lastPop = null;
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    lastPop = stack.pop();
                    inorderIndex++;
                }
                TreeNode newValueNode = new TreeNode(newValue);
                lastPop.right = newValueNode;
                stack.push(newValueNode);
            }
            return result;
        }
    }

    /**
     * 9. 回文数
     * 简单
     * 相关标签
     * 相关企业
     * 提示
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 例如，121 是回文，而 123 不是。
     *
     * @param x
     * @return
     */
    public boolean isPalindrome_9(int x) {
        String s = x + "";
        int distance = s.length() / 2;
        for (int i = 0; i < distance; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome_9_planB(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) {
            return false;
        }

        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }

        return x == reverseNum || x == reverseNum / 10;
    }

    /**
     * 589. N 叉树的前序遍历
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     */
    class BinaryTreePreOrderTraversal_589 {
        // Definition for a Node.
        class Node {
            public int val;
            public List<Node> children;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }

        public List<Integer> preorder2(Node root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            HashMap<Node, Integer> record = new HashMap<>();
            Stack<Node> stack = new Stack<>();
            Node cur = root;
            while (true) {
                stack.add(cur);
                result.add(cur.val);
                if (cur.children != null && cur.children.size() > 0) {
                    cur = cur.children.get(0);
                    record.put(cur, 0);
                } else {
                    while (true) {
                        if (stack.size() == 1) {
                            return result;
                        }
                        Node pop = stack.pop();
                        Node peek = stack.peek();
                        Integer recordIndex = record.get(pop);
                        recordIndex = recordIndex == null ? -1 : recordIndex;
                        record.remove(pop);
                        int nextIndex = recordIndex + 1;
                        if (peek.children != null && peek.children.size() > nextIndex) {
                            cur = peek.children.get(nextIndex);
                            record.put(cur, nextIndex);
                            break;
                        }
                    }
                }
            }
        }

        public List<Integer> preorder(Node root) {
            List<Integer> result = new ArrayList<>();
            preOrderRecursive(root, result);
            return result;
        }

        public void preOrderRecursive(Node node, List<Integer> result) {
            if (node == null) {
                return;
            }
            result.add(node.val);
            if (node.children != null && node.children.size() > 0) {
                for (int i = 0; i < node.children.size(); i++) {
                    preOrderRecursive(node.children.get(i), result);
                }
            }
        }
    }

    /**
     * 590. N 叉树的后序遍历
     * 简单
     * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     */
    class BinaryTreePostOrderTraversal_590 {

        // Definition for a Node.
        class Node {
            public int val;
            public List<Node> children;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }

        class Solution {
            // 方案1
            public List<Integer> postorder(Node root) {
                ArrayList<Integer> result = new ArrayList<>();
                if (root == null) {
                    return result;
                }
                Stack<Node> stack = new Stack<>();
                Node cur = root;
                while (true) {
                    stack.add(cur);
                    if (cur.children != null && cur.children.size() > 0) {
                        cur = cur.children.get(0);
                    } else {
                        while (true) {
                            result.add(cur.val);
                            if (stack.size() == 1) {
                                return result;
                            }
                            Node pop = stack.pop();
                            Node peek = stack.peek();
                            int i = peek.children.indexOf(pop);
                            if (i < peek.children.size() - 1) {
                                cur = peek.children.get(i + 1);
                                break;
                            }
                            cur = peek;
                        }
                    }
                }
            }


            // 方案二
            public List<Integer> postorder2(Node root) {
                ArrayList<Integer> result = new ArrayList<>();
                if (root == null) {
                    return result;
                }
                postOrderRecursive(root, result);
                return result;
            }

            public void postOrderRecursive(Node root, ArrayList<Integer> result) {
                if (root.children != null && root.children.size() > 0) {
                    for (int i = 0; i < root.children.size(); i++) {
                        postOrderRecursive(root.children.get(i), result);
                    }
                }
                result.add(root.val);
            }
        }
    }
}

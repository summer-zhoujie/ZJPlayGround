package com.example.playground;

public class Holiday2024 {
    public static void main(String[] args) {
        String mHolidayInfo = "2023.12.30,2023.12.31,"
                // 1 月
                + generateHoliday(1, 1, 6, 7, 13, 14, 20, 21, 27, 28)
                // 2 月
                + generateHoliday(2, 3, 10, 11, 12, 13, 14, 15, 16, 17, 24, 25)
                // 3 月
                + generateHoliday(3, 2, 3, 9, 10, 16, 17, 23, 24, 30, 31)
                // 4 月
                + generateHoliday(4, 4, 5, 6, 13, 14, 20, 21, 27)
                // 5 月
                + generateHoliday(5, 1, 2, 3, 4, 5, 12, 18, 19, 25, 26)
                // 6 月
                + generateHoliday(6, 1, 2, 8, 9, 10, 15, 16, 22, 23, 29, 30)
                // 7 月
                + generateHoliday(7, 6, 7, 13, 14, 20, 21, 27, 28)
                // 8 月
                + generateHoliday(8, 3, 4, 10, 11, 17, 18, 24, 25, 31)
                // 9 月
                + generateHoliday(9, 1, 7, 8, 15, 16, 17, 21, 22, 28)
                // 10 月
                + generateHoliday(10, 1, 2, 3, 4, 5, 6, 7, 13, 19, 20, 26, 27)
                // 11 月
                + generateHoliday(11, 2, 3, 9, 10, 16, 17, 23, 24, 30)
                // 12 月
                + generateHoliday(12, 1, 7, 8, 14, 15, 21, 22, 28, 29);
        System.out.println(mHolidayInfo);
    }

    // 2024 年节假日
    private static String generateHoliday(int month, int... days) {
        String holiday = "";
        for (int day : days) {
            holiday = holiday.concat("2024")
                    .concat(".").concat(String.valueOf(month))
                    .concat(".").concat(String.valueOf(day))
                    .concat(",");
        }
        return holiday;
    }
}

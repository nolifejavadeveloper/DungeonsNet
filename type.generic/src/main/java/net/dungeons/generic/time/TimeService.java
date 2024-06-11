package net.dungeons.generic.time;

public class TimeService {
    private TimeService() {}
    private static String getDayNumber() {
        return SkyblockTime.getDay() + getOrdinalSuffix(SkyblockTime.getDay());
    }

    private static String getMonth() {
        return switch (SkyblockTime.getMonth()) {
            case 1 -> "Early Spring";
            case 2 -> "Spring";
            case 3 -> "Late Spring";
            case 4 -> "Early Summer";
            case 5 -> "Summer";
            case 6 -> "Late Summer";
            case 7 -> "Early Autumn ";
            case 8 -> "Autumn";
            case 9 -> "Late Autumn";
            case 10 -> "Early Winter";
            case 11 -> "Winter";
            case 12 -> "Late Winter";
            default -> "Error";
        };
    }

    private static boolean isDay() {
        return (SkyblockTime.getHour() >=  6 && SkyblockTime.getHour() <= 19);
    }

    private static String getOrdinalSuffix(int day) {
        return switch ((day % 10)) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }

    public static String getTimeLine() {
        boolean isPm = false;
        int newHour = SkyblockTime.getHour();
        if (SkyblockTime.getHour() > 12) {
            //is pm
            isPm = true;
            newHour -=12;
        }

        String time = String.format("%s:%s%s", newHour, SkyblockTime.getMinute(), isPm ? "pm" : "am");
        String icon = " " + (isDay() ? "&e☀" : "&b☽");

        return "&7" + time + icon;
    }

    public static String getDateAndSeasonLine() {
        return "&7" + getMonth() + " " + getDayNumber();
    }

}

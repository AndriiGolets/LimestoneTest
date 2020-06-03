package site.golets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LimestoneDigital {

    public static final int CHAR_0 = 48;
    public static final int CHAR_8 = 56;

    public static void main(String[] args) {
        System.out.println(daysOfWeekSort(2));
        System.out.println(daysOfWeekSort(56));
        System.out.println(daysOfWeekSort(24));
        System.out.println(daysOfWeekSort(123));
        System.out.println(daysOfWeekSort(135));
        System.out.println(daysOfWeekSort(125));
        System.out.println(daysOfWeekSort(12367));
        System.out.println(daysOfWeekSort(134567));
        System.out.println(daysOfWeekSort(133334567));
    }

    public static String daysOfWeekSort(Integer unsortedDays) {

        Set<String> orderedSet = unsortedDays.toString()
                .chars().boxed()
                .filter(i -> checkInputValues(i))
                .map(i -> String.valueOf(i - CHAR_0)).collect(Collectors.toCollection(TreeSet::new));

        return groupConsequent(orderedSet).stream()
                .map(s -> groupWithDash(s))
                .reduce((a, b) -> a + "," + b).orElse("Error");
    }

    private static List<StringBuffer> groupConsequent(Set<String> orderedSet) {
        List<StringBuffer> consequentGroups = new ArrayList<>();

        int last = 0;
        for (String s : orderedSet) {
            int si = Integer.parseInt(s);
            if (last == 0 || si - last != 1) {
                consequentGroups.add(new StringBuffer(s));
            } else {
                consequentGroups.get(consequentGroups.size() - 1).append(s);
            }
            last = si;
        }
        return consequentGroups;
    }

    private static String groupWithDash(StringBuffer s) {
        return s.length() > 1 ? s.substring(0, 1) + "-" + s.substring(s.length() - 1) : s.toString();
    }

    private static boolean checkInputValues(Integer i) {
        if (!(i > CHAR_0 && i < CHAR_8)) {
            throw new RuntimeException("Input Int have to contain numbers from 1 to 7 only");
        }
        return true;
    }

}

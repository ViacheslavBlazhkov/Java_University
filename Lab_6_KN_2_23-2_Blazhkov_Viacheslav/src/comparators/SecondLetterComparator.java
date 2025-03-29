package comparators;

import java.util.Comparator;

public class SecondLetterComparator implements Comparator<String> {
    public int compare(String str1, String str2) {
        String str1letter = Character.toString(str1.charAt(1));
        String str2letter = Character.toString(str2.charAt(1));
        return str1letter.compareTo(str2letter);
    }
}

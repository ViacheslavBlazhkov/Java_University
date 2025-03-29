import comparators.SecondLetterComparator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Terminal {
    public static void main(String[] args) {
        task7();
    }

    public static void task1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> transformedNumbers = numbers.stream()
                .map(n -> n * n)
                .toList();
        System.out.println("Transformed numbers: " + transformedNumbers);
    }

    public static void task2() {
        int[] numbers = {10, 20, 30, 40, 50};
        int max = Arrays.stream(numbers)
                .max()
                .getAsInt();
        System.out.println("Maximum number: " + max);
    }

    public static void task3() {
        List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve");
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("J"))
                .toList();
        System.out.println("Filtered names: " + filteredNames);
    }

    public static void task4() {
        List<String> list1 = Arrays.asList("John", "Jane", "Adam");
        List<String> list2 = Arrays.asList("Adam", "Eve", "John");
        List<String> mergedList = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .toList();
        System.out.println("Merged list without duplicates: " + mergedList);
    }

    public static void task5() {
        List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve");
        List<String> sortedNames = names.stream()
                .sorted(new SecondLetterComparator())
                .toList();
        System.out.println("Sorted names: " + sortedNames);
    }

    public static void task6() {
        List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve", "Adolf");
        Map<String, List<String>> namesByFirstLetter = names.stream()
                .collect(
                        Collectors.groupingBy(name -> Character.toString(name.charAt(0)))
                );
        for (Map.Entry<String, List<String>> item : namesByFirstLetter.entrySet()) {
            System.out.println(item.getKey());
            for (String name : item.getValue()) {
                System.out.println(name);
            }
        }
    }

    public static void task7() {
        String text = "Lorem ipsum dolor sit amet";
        String[] words = text.split(" ");
        long count = Arrays.stream(words).count();
        System.out.println("Count of words: " + count);
    }

    public static void task8() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 100, 25, -10);
        OptionalDouble avg = numbers.stream()
                .mapToInt(Integer::intValue)
                .average();
        if (avg.isPresent()) {
            System.out.println("Avg: " + avg.getAsDouble());
        }
    }
}

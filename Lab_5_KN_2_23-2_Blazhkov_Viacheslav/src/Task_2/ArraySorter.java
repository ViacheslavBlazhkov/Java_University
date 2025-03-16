package Task_2;

import java.util.Arrays;
import java.util.Random;

public class ArraySorter {
    public static void main(String[] args) {
        int[] array = new int[100000];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(1000);
        }

        int middle = array.length / 2;
        int[] firstHalf = Arrays.copyOfRange(array, 0, middle);
        int[] secondHalf = Arrays.copyOfRange(array, middle, array.length);

        Runnable firstHalfSorter = () -> {
            Arrays.sort(firstHalf);
        };
        Runnable secondHalfSorter = () -> {
            Arrays.sort(secondHalf);
        };

        Thread firstHalfThread = new Thread(firstHalfSorter);
        Thread secondHalfThread = new Thread(secondHalfSorter);

        firstHalfThread.start();
        secondHalfThread.start();

        try {
            firstHalfThread.join();
            secondHalfThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] sortedArray = new int[array.length];
        int i = 0, j = 0, k = 0;

        while (i < firstHalf.length && j < secondHalf.length) {
            if (firstHalf[i] < secondHalf[j]) {
                sortedArray[k++] = firstHalf[i++];
            } else {
                sortedArray[k++] = secondHalf[j++];
            }
        }

        while (i < firstHalf.length) {
            sortedArray[k++] = firstHalf[i++];
        }
        while (j < secondHalf.length) {
            sortedArray[k++] = secondHalf[j++];
        }

        System.out.println(Arrays.toString(sortedArray));
    }
}

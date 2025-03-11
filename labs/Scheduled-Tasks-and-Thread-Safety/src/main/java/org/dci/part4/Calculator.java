package org.dci.part4;

import java.util.concurrent.Callable;

public class Calculator implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int[] arrayOfNumbers = generateRandomArray();
        int sum = 0;
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            sum += arrayOfNumbers[i];
        }
        return sum;
    }

    private int[] generateRandomArray() {
        int[] randomArray = new int[10];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = (int) (Math.random() * 100);
        }
        return randomArray;
    }
}

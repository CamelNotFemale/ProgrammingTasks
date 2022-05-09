package com.github.camelnotfemale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void sort(int[] array, int low, int high) {
        if (high <= low) return;

        int mid = (low+high)/2;
        sort(array, low, mid);
        sort(array, mid+1, high);
        merge(array, low, mid, high);
    }
    public static void merge(int[] array, int low, int mid, int high) {
        int leftArray[] = new int[mid - low + 1];
        int rightArray[] = new int[high - mid];

        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = low; i < high + 1; i++) {
            // Переписываем наименьшие элементы из отсортированных массивов в исходный
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                // Копируем оставшиеся элементы из левого массива.
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                // Копируем оставшиеся элементы из правого массива.
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array;

        if (args.length > 0) {
            array = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        }
        else {
            System.out.println("Enter an array of integers on one line separated by a space:");
            Scanner in = new Scanner(System.in);
            array = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        sort(array, 0, array.length-1);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}

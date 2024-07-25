package org.example;

public class Main {
    public static void main(String[] args) {
        char[] nums = {'v', 't', 'c', 'e', 'a','f'};

        BubbleSort obj = new BubbleSort();
        obj.sort(nums);

        for (char num : nums) {
            System.out.print(num + " ");
        }
    }
}
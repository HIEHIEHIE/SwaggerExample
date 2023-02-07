package io.transwarp.controller;

public class test {
    public static void main(String args[]) {
        int[] nums = {4 ,3 ,2 ,1 ,9 ,8 ,10 ,7};
        selectSort(nums);
        //mpSort(nums);
        //quickSort(nums, 0, nums.length - 1);
        for(int i : nums) System.out.print(i + " ");
    }

    public static void mpSort(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] > nums[j]) swap(nums, i, j);
            }
        }
    }

    public static void selectSort(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int minIndex = i;
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }
    public static void quickSort(int[] nums, int start, int end) {
        if(start > end) return;
        int flagValue = nums[end];
        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[left] <= flagValue) left++;
            while (left < right && nums[right] >= flagValue) right--;
            if(left < right) swap(nums, left, right);
        }
        swap(nums, left, end);
        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
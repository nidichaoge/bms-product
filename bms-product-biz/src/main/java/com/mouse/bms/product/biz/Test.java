package com.mouse.bms.product.biz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Test
 * @date : 2019/5/10 18:32
 * @description :
 */
public class Test {

    public static void main(String[] args) {
//        function1();
        try {
            hello();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void function1() {
        List<String> result = new ArrayList<>();
        List<String> result1 = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] split = str.split(" ");
        if (0 >= split.length) {
            return;
        }
        int a = 1;
        result.add(split[0]);
        for (int i = 1; i < split.length; i = (int) (Math.pow(2, ++a) - 1)) {
            for (int j = (int) (Math.pow(2, ++a) - 1); j < (int) (Math.pow(2, a++) - 1); j++) {
                result1.add(split[j]);
            }
            Collections.reverse(result1);
            result.addAll(result1);
        }
        System.out.println(result);
    }

    private static void hello() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<Integer> list = new ArrayList<>();
        String a = "我";
        Method add = list.getClass().getMethod("add", Object.class);
//        Arrays.stream(methods).forEach(System.out::println);
        Object invoke = add.invoke(list, a);
        System.out.println(list.get(0));
//        list.stream().forEach(System.out::println);
//        list.add("a");
    }

    private void test(){
        AtomicInteger atomicInteger = new AtomicInteger();
        try {
            atomicInteger.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


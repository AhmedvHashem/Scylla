package com.hashem;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.management.monitor.StringMonitor;
import javax.naming.StringRefAddr;

public class Main {

    public static String testString = "Hello World!";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        StringMonitor stringMonitor = new StringMonitor();
        StringRefAddr stringRefAddr = new StringRefAddr("test", testString);
        WeakReference<String> weakReference = new WeakReference<>(testString);
        PhantomReference<String> phantomReference = new PhantomReference<>(testString, null);
        AtomicReference<String> atomiCReference = new AtomicReference<>(testString);

        // Caching behavior
        Long i1 = 127L;
        Long i2 = 127L;
        System.out.println(i1 == i2);  // true, due to caching

        Long i3 = 128L;
        Long i4 = 128L;
        System.out.println(i3 == i4);  // false, outside cache range

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        StringBuffer stringBuffer = new StringBuffer();
    }
}


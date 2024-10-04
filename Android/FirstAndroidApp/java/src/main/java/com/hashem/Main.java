package com.hashem;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.http.HttpClient;
import java.util.Optional;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World! Java");

//        String name = "Bob";
//        name.getBytes();
//
//        int x = 10;
//        Integer y = 10;
//        y.doubleValue();

//        HttpClient client = HttpClient.newBuilder().build();
//
//        IntStream.range(5, 10).forEach(System.out::println);
//
//        Optional<String> optional = Optional.empty();

//        String a = "hello";
//        String b = "hello";
//        System.out.println(a == b);  // true, because the contents of a and b are the same
//        System.out.println(a.equals(b));
//
//        String c = null;
//        System.out.println(a == c);  // false, a is not null and c is null
//        System.out.println(a.equals(c));
//
//        String d = null;
//        System.out.println(c == d);  // true, both are null
//        System.out.println(c.equals(d));
//        System.gc();
//        System.runFinalization();


//        ObjectForReference strongReference = new ObjectForReference();
//        strongReference.print();
//
//        SoftReference<ObjectForReference> softReference = new SoftReference<>(strongReference);
//        WeakReference<ObjectForReference> weakReference = new WeakReference<>(strongReference);
//        PhantomReference<ObjectForReference> phantomReference = new PhantomReference<>(strongReference, null);
//
//        strongReference = null;
//        softReference.get().print();
//        weakReference.get().print();
////        phantomReference.get().print();
//
//        System.gc();

        Integer a = Integer.valueOf(1);
        Integer b = Integer.valueOf(1);
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}

class ObjectForReference
{
    public void print()
    {
        System.out.println("I am a live!");
    }

    protected void finalize()
    {
        System.out.println("I am dead!");
    }
}

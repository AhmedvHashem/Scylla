public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world! Java");

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

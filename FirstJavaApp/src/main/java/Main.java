public class Main {
    public static void main(String[] args) {
        Dog aDog = new Dog("Max");
        Dog oldDog = aDog;

        foo(aDog);
        // when foo(...) returns, the name of the dog has been changed to "Fifi"
        System.out.println(aDog.getName().equals("Max")); // true
        System.out.println(aDog.getName().equals("Fifi")); // false
        // but it is still the same dog:
        System.out.println(aDog == oldDog); // true
    }

    public static void foo(Dog d) {
        System.out.println(d.getName().equals("Max")); // true
        // this changes the name of d to be "Fifi"
        d = new Dog("Fifi");
        System.out.println(d.getName().equals("Fifi")); // true
    }

    public static class Dog {
        private String name;

        public Dog(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
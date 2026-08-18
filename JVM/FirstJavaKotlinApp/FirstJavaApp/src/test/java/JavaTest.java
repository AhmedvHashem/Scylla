import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

interface Vehicle {
    int getId();
}

record Car(int id) implements Vehicle {
    @Override
    public int getId() {
        return id;
    }
}

record Truck(int id) implements Vehicle {
    @Override
    public int getId() {
        return id;
    }
}

public class JavaTest {

    @Test
    void test() {
        Car myCar = new Car(1);
        Truck myTruck = new Truck(1);

        var isEqual1 = myCar.equals(myTruck);
//        var isEqual2 = myCar == myTruck;
        assertFalse(isEqual1);
//        assertFalse(isEqual2);
        assertTrue(areTheyEqual(new Car(1), new Car(1)));
    }


    static <T extends Vehicle> boolean areTheyEqual(T a, T b) {
        return a == b;
    }
}

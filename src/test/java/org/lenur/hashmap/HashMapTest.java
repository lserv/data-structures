package org.lenur.hashmap;

import org.junit.Assert;
import org.junit.Test;
import org.lenur.Car;

public class HashMapTest {
    private static final Car FIRST_CAR = new Car("Audi", "black");
    private static final Car SECOND_CAR = new Car("Bmw", "red");
    private static final Car THIRD_CAR = new Car("Mercedes", "grey");

    private static final Car SAME_FIRST_CAR = new Car("Audi", "black");
    private static final Car SAME_SECOND_CAR = new Car("Bmw", "red");
    private static final Car SAME_THIRD_CAR = new Car("Mercedes", "grey");

    private static final HashCodeOne firstPlane = new HashCodeOne("FirstCode", "FirstInn");
    private static final HashCodeOne secondPlane = new HashCodeOne("SecondCode", "SecondInn");
    private static final HashCodeOne thirdPlane = new HashCodeOne("ThirdCode", "ThirdInn");

    private static final HashCodeZero firstZero = new HashCodeZero("FirstCode", "FirstInn");
    private static final HashCodeZero secondZero = new HashCodeZero("SecondCode", "SecondInn");
    private static final HashCodeZero thirdZero = new HashCodeZero("ThirdCode", "ThirdInn");

    @Test
    public void getKeyFromEmptyHashMap() {
        Map<Car, Integer> hashMap = new HashMap<>();
        Assert.assertNull(hashMap.get(FIRST_CAR));
        Assert.assertEquals(0, hashMap.getSize());
    }

    @Test
    public void putAndGet() {
        Map<Car, Integer> hashMap = new HashMap<>();
        hashMap.put(FIRST_CAR, 3);
        hashMap.put(SECOND_CAR, 5);
        hashMap.put(THIRD_CAR, 1);

        Assert.assertEquals(hashMap.getSize(), 3, hashMap.getSize());

        Integer firstActualValue = hashMap.get(FIRST_CAR);
        Integer secondActualValue = hashMap.get(SECOND_CAR);
        Integer thirdActualValue = hashMap.get(THIRD_CAR);
        Assert.assertEquals(Integer.valueOf(3), firstActualValue);
        Assert.assertEquals(Integer.valueOf(5), secondActualValue);
        Assert.assertEquals(Integer.valueOf(1), thirdActualValue);
    }

    @Test
    public void putTheSameElement() {
        Map<Car, Integer> hashMap = new HashMap<>();
        hashMap.put(FIRST_CAR, 3);
        hashMap.put(SECOND_CAR, 5);
        hashMap.put(THIRD_CAR, 1);
        hashMap.put(SAME_FIRST_CAR, 3);
        hashMap.put(SAME_SECOND_CAR, 5);
        hashMap.put(SAME_THIRD_CAR, 1);

        Assert.assertEquals(3, hashMap.getSize());

        Integer firstActualValue = hashMap.get(FIRST_CAR);
        Integer secondActualValue = hashMap.get(SECOND_CAR);
        Integer thirdActualValue = hashMap.get(THIRD_CAR);
        Assert.assertEquals(Integer.valueOf(3), firstActualValue);
        Assert.assertEquals(Integer.valueOf(5), secondActualValue);
        Assert.assertEquals(Integer.valueOf(1), thirdActualValue);
    }

    @Test
    public void putAndGetByNullKey() {
        Map<Car, Integer> hashMap = new HashMap<>();

        hashMap.put(null, 3);
        Assert.assertEquals(Integer.valueOf(3), hashMap.get(null));
        Assert.assertEquals(1, hashMap.getSize());

        hashMap.put(null, 5);
        Assert.assertEquals(Integer.valueOf(5), hashMap.get(null));
        Assert.assertEquals(1, hashMap.getSize());
    }

    @Test
    public void putAndGetWithCollision() {
        Map<HashCodeOne, Integer> hashMap = new HashMap<>();
        hashMap.put(firstPlane, 3);
        hashMap.put(secondPlane, 5);
        hashMap.put(thirdPlane, 1);

        Assert.assertEquals(3, hashMap.getSize());

        Assert.assertEquals(Integer.valueOf(3), hashMap.get(firstPlane));
        Assert.assertEquals(Integer.valueOf(5), hashMap.get(secondPlane));
        Assert.assertEquals(Integer.valueOf(1), hashMap.get(thirdPlane));
    }

    @Test
    public void putAndGetByNullKeyWithCollision() {
        Map<HashCodeZero, Integer> hashMap = new HashMap<>();
        hashMap.put(firstZero, 3);
        hashMap.put(null, 4);
        hashMap.put(secondZero, 5);
        hashMap.put(null, 10);
        hashMap.put(thirdZero, 1);

        Assert.assertEquals(4, hashMap.getSize());

        Assert.assertEquals(Integer.valueOf(3), hashMap.get(firstZero));
        Assert.assertEquals(Integer.valueOf(5), hashMap.get(secondZero));
        Assert.assertEquals(Integer.valueOf(1), hashMap.get(thirdZero));
        Assert.assertEquals(Integer.valueOf(10), hashMap.get(null));
    }

    @Test
    public void putAndGetTheOverriddenValueByKey() {
        Map<Car, Integer> hashMap = new HashMap<>();

        hashMap.put(FIRST_CAR, 3);
        Assert.assertEquals(1, hashMap.getSize());
        Assert.assertEquals(Integer.valueOf(3), hashMap.get(FIRST_CAR));

        hashMap.put(FIRST_CAR, 5);
        Assert.assertEquals(1, hashMap.getSize());
        Assert.assertEquals(Integer.valueOf(5), hashMap.get(FIRST_CAR));
    }

    @Test
    public void checkTheHashMapIncrease() {
        Map<Car, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < 1000; i++) {
            Car car = new Car("model_" + i, "color_" + i);
            hashMap.put(car, i);
        }
        Assert.assertEquals(1000, hashMap.getSize());

        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(Integer.valueOf(i),
                    hashMap.get(new Car("model_" + i, "color_" + i)));
        }
    }

    @Test
    public void getSizeWithCollision() {
        Map<HashCodeOne, Integer> hashMap = new HashMap<>();
        hashMap.put(firstPlane, 3);
        hashMap.put(secondPlane, 5);
        Assert.assertEquals(2, hashMap.getSize());
    }

    @Test
    public void getSizeWithCollisionAtZeroPosition() {
        Map<HashCodeZero, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            HashCodeZero bus = new HashCodeZero("code_" + i, "inn_" + i);
            hashMap.put(bus, i);
        }

        Assert.assertEquals(1000, hashMap.getSize());
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(Integer.valueOf(i),
                    hashMap.get(new HashCodeZero("code_" + i, "inn_" + i)));
        }
    }

    @Test
    public void getSizeWithCollisionAtFirstPosition() {
        Map<HashCodeOne, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            HashCodeOne plane = new HashCodeOne("model_" + i, "color_" + i);
            hashMap.put(plane, i);
        }

        Assert.assertEquals(1000, hashMap.getSize());
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(Integer.valueOf(i),
                    hashMap.get(new HashCodeOne("model_" + i, "color_" + i)));
        }
    }
}

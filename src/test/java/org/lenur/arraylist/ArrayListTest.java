package org.lenur.arraylist;

import org.junit.Assert;
import org.junit.Test;
import org.lenur.Car;

import java.util.NoSuchElementException;

public class ArrayListTest {
    private static final Car FIRST_CAR = new Car("Audi", "black");
    private static final Car SECOND_CAR = new Car("Bmw", "red");

    @Test
    public void addElement() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Test1");
        arrayList.add(null);
        arrayList.add("Test2");
        Assert.assertEquals(3, arrayList.size());
        Assert.assertEquals("Test1", arrayList.get(0));
        Assert.assertNull(arrayList.get(1));
        Assert.assertEquals("Test2", arrayList.get(2));
    }

    @Test
    public void checkIsNotEmpty() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        Assert.assertFalse(arrayList.isEmpty());
    }

    @Test
    public void checkIsEmpty() {
        List<String> arrayList = new ArrayList<>();
        Assert.assertTrue(arrayList.isEmpty());
        arrayList.add("First");
        arrayList.remove(0);
        Assert.assertTrue(arrayList.isEmpty());
    }

    @Test
    public void addElementByIndex() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Test0");
        arrayList.add("Test1");
        Assert.assertEquals(2, arrayList.size());

        arrayList.add("Test2", 1);
        Assert.assertEquals("Test0", arrayList.get(0));

        Assert.assertEquals("Test2", arrayList.get(1));
        Assert.assertEquals("Test1", arrayList.get(2));

        arrayList.add(null, 0);
        Assert.assertEquals(4, arrayList.size());
        Assert.assertNull(arrayList.get(0));

        arrayList.add("value", 4);
        Assert.assertEquals("value", arrayList.get(4));
        Assert.assertEquals(5, arrayList.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addElementInTheNonExistentPosition() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Second", 5);
    }

    @Test
    public void addListToArrayList() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        Assert.assertEquals(2, arrayList.size());

        List<String> newArrayList = new ArrayList<>();
        newArrayList.add("First 1");
        newArrayList.add("Second 2");
        arrayList.addAll(newArrayList);

        Assert.assertEquals(4, arrayList.size());
        Assert.assertEquals("First 1", arrayList.get(2));
        Assert.assertEquals("Second 2", arrayList.get(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addElementInTheNegativePosition() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java", -1);
    }

    @Test
    public void checkingResize() {
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrayList.add("First + " + i);
        }

        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals("First + " + i, arrayList.get(i));
        }
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementByNonExistentIndex() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        Assert.assertNull(arrayList.remove(5));
        arrayList.remove(5);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeElementByNonExistentValue() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        Assert.assertNull(arrayList.remove("Public"));
    }

    @Test
    public void removeElementByValue() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        arrayList.add(null);
        Assert.assertEquals(4, arrayList.size());

        String actualResult = arrayList.remove("Java");
        Assert.assertEquals("Java", actualResult);
        Assert.assertEquals(3, arrayList.size());
        Assert.assertEquals("Private", arrayList.get(1));

        actualResult = arrayList.remove("String");
        Assert.assertEquals("String", actualResult);
        Assert.assertEquals(2, arrayList.size());
        Assert.assertEquals("Private", arrayList.get(0));

        actualResult = arrayList.remove(null);
        Assert.assertNull(actualResult);
        Assert.assertEquals(1, arrayList.size());
    }

    @Test
    public void removeObjectValueByValue() {
        List<Car> cats = new ArrayList<>();
        cats.add(FIRST_CAR);
        cats.add(SECOND_CAR);
        Assert.assertEquals(2, cats.size());

        Car actualResult = cats.remove(SECOND_CAR);
        Assert.assertEquals(SECOND_CAR, actualResult);
        Assert.assertEquals(1, cats.size());
    }

    @Test
    public void setValueInIndex() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("5");
        arrayList.add("115");
        Assert.assertEquals("115", arrayList.get(1));

        arrayList.set("511", 1);
        Assert.assertEquals(2, arrayList.size());
        Assert.assertEquals("511", arrayList.get(1));

        arrayList.set(null, 0);
        Assert.assertEquals(2, arrayList.size());
        Assert.assertNull(arrayList.get(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setValueInTheNonExistentPosition() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.set("Third", 2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setValueInTheNegativePosition() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.set("Third", -2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getElementByNonExistedIndex() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        Assert.assertNull(arrayList.get(3));
    }
}

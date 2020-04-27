package org.lenur.linkedlist;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.lenur.Car;

import java.util.Arrays;
public class LinkedListTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private static final String FIRST_ITEM = "First";
    private static final String SECOND_ITEM = "Second";
    private static final String THIRD_ITEM = "Third";
    private static final String NULL_ITEM = null;
    private static final String NEW_ITEM = "NewFirst";
    private static final String ANOTHER_NEW_ITEM = "NewSecond";

    private static final Car FIRST_CAR = new Car("Audi", "black");
    private static final Car SECOND_CAR = new Car("Bmw", "red");
    private static final Car THIRD_CAR = new Car("Mercedes", "grey");
    private static final Car THE_SAME_SECOND_CAR = new Car("Bmw", "red");

    private static final java.util.List<String> DEFAULT_LIST = new java.util.LinkedList<>(
            Arrays.asList("First", "Second", "Third", "Fourth", "Fifth", "Sixth"));

    @Test
    public void addItems() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);
        boolean added = linkedList.add(NULL_ITEM);

        Assert.assertEquals(FIRST_ITEM, linkedList.get(0));
        Assert.assertEquals(SECOND_ITEM, linkedList.get(1));
        Assert.assertEquals(THIRD_ITEM, linkedList.get(2));
        Assert.assertTrue(added);
        Assert.assertNull(linkedList.get(3));
    }

    @Test
    public void addManyData() {
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            linkedList.add("String" + i);
        }

        Assert.assertEquals(1000, linkedList.size());
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals("String" + i, linkedList.get(i));
        }
    }

    @Test
    public void addByIndexToTheTop() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM, 0);
        linkedList.add(SECOND_ITEM, 1);
        linkedList.add(THIRD_ITEM, 2);

        Assert.assertEquals(FIRST_ITEM, linkedList.get(0));
        Assert.assertEquals(SECOND_ITEM, linkedList.get(1));
        Assert.assertEquals(THIRD_ITEM, linkedList.get(2));

        exception.expect(IndexOutOfBoundsException.class);
        linkedList.add(THIRD_ITEM, 4);
    }

    @Test
    public void addByIndexToTheBottom() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM, 0);
        linkedList.add(SECOND_ITEM, 1);
        linkedList.add(THIRD_ITEM, 0);
        linkedList.add(SECOND_ITEM, 0);

        Assert.assertEquals(SECOND_ITEM, linkedList.get(0));
        Assert.assertEquals(THIRD_ITEM, linkedList.get(1));
        Assert.assertEquals(FIRST_ITEM, linkedList.get(2));
        Assert.assertEquals(SECOND_ITEM, linkedList.get(3));

        exception.expect(IndexOutOfBoundsException.class);
        linkedList.add(THIRD_ITEM, 5);
    }

    @Test
    public void addByIndexAtTheMiddle() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);
        linkedList.add(FIRST_ITEM, 2);
        linkedList.add(THIRD_ITEM, 1);

        Assert.assertEquals(FIRST_ITEM, linkedList.get(0));
        Assert.assertEquals(THIRD_ITEM, linkedList.get(1));
        Assert.assertEquals(SECOND_ITEM, linkedList.get(2));
        Assert.assertEquals(FIRST_ITEM, linkedList.get(3));
        Assert.assertEquals(THIRD_ITEM, linkedList.get(4));

        Assert.assertEquals(5, linkedList.size());
        exception.expect(IndexOutOfBoundsException.class);
        linkedList.add(THIRD_ITEM, 7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByNegativeIndex() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM, -1);
    }

    @Test
    public void addAll() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);

        String expectedLast = DEFAULT_LIST.get(DEFAULT_LIST.size() - 3);
        Assert.assertTrue(linkedList.addAll(DEFAULT_LIST));
        Assert.assertEquals(FIRST_ITEM, linkedList.get(0));
        Assert.assertEquals(SECOND_ITEM, linkedList.get(1));
        Assert.assertEquals(expectedLast, linkedList.get(DEFAULT_LIST.size() - 1));
        Assert.assertEquals(8, linkedList.size());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void getByNegativeIndex() {
        List<String> linkedList = new LinkedList<>();
        linkedList.get(-1);
    }

    @Test
    public void setByIndex() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);

        Assert.assertEquals(linkedList.set(NEW_ITEM, 0), FIRST_ITEM);
        Assert.assertEquals(linkedList.set(ANOTHER_NEW_ITEM, 1), SECOND_ITEM);

        Assert.assertEquals(NEW_ITEM, linkedList.get(0));
        Assert.assertEquals(ANOTHER_NEW_ITEM, linkedList.get(1));
        Assert.assertEquals(3, linkedList.size());

        exception.expect(IndexOutOfBoundsException.class);
        linkedList.set(NEW_ITEM, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setByNegativeIndex() {
        List<String> linkedList = new LinkedList<>();
        linkedList.set(NEW_ITEM, -1);
    }

    @Test
    public void removeByIndex() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);
        linkedList.add(NULL_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);
        linkedList.add(NULL_ITEM);

        Assert.assertEquals(FIRST_ITEM, linkedList.remove(0));
        Assert.assertEquals(SECOND_ITEM, linkedList.remove(3));
        Assert.assertEquals(THIRD_ITEM, linkedList.remove(3));
        Assert.assertEquals(NULL_ITEM, linkedList.remove(3));

        Assert.assertEquals(SECOND_ITEM, linkedList.get(0));
        Assert.assertEquals(THIRD_ITEM, linkedList.get(1));
        Assert.assertEquals(NULL_ITEM, linkedList.get(2));
        Assert.assertEquals(3, linkedList.size());

        exception.expect(IndexOutOfBoundsException.class);
        linkedList.remove(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByNegativeIndex() {
        List<String> linkedList = new LinkedList<>();
        linkedList.remove(-1);
    }

    @Test
    public void removeByItemPositive() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(NULL_ITEM);
        linkedList.add(THIRD_ITEM);
        linkedList.add(FIRST_ITEM);
        linkedList.add(NULL_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);

        Assert.assertTrue(linkedList.remove(FIRST_ITEM));
        Assert.assertTrue(linkedList.remove(NULL_ITEM));
        Assert.assertTrue(linkedList.remove(THIRD_ITEM));
        Assert.assertTrue(linkedList.remove(FIRST_ITEM));

        Assert.assertEquals(SECOND_ITEM, linkedList.get(0));
        Assert.assertEquals(NULL_ITEM, linkedList.get(1));
        Assert.assertEquals(SECOND_ITEM, linkedList.get(2));
        Assert.assertEquals(THIRD_ITEM, linkedList.get(3));
        Assert.assertEquals(4, linkedList.size());
    }

    @Test
    public void removeByItemNegative() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);

        Assert.assertFalse(linkedList.remove(NEW_ITEM));
        Assert.assertFalse(linkedList.remove(NULL_ITEM));
        Assert.assertEquals(3, linkedList.size());
    }

    @Test
    public void removeObject() {
        List<Car> cars = new LinkedList<>();
        cars.add(FIRST_CAR);
        cars.add(SECOND_CAR);
        cars.add(THIRD_CAR);

        Assert.assertEquals(3, cars.size());
        Assert.assertTrue(cars.remove(THE_SAME_SECOND_CAR));
        Assert.assertEquals(2, cars.size());
    }

    @Test
    public void size() {
        List<String> linkedList = new LinkedList<>();
        int initSize = linkedList.size();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);

        Assert.assertEquals(0, initSize);
        Assert.assertEquals(3, linkedList.size());
    }

    @Test
    public void sizeAfterSet() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);
        Assert.assertEquals(3, linkedList.size());

        linkedList.set(NEW_ITEM, 1);
        Assert.assertEquals(3, linkedList.size());
    }

    @Test
    public void sizeAfterRemove() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        linkedList.add(SECOND_ITEM);
        linkedList.add(THIRD_ITEM);
        Assert.assertEquals(3, linkedList.size());

        linkedList.remove(1);
        Assert.assertEquals(2, linkedList.size());
    }

    @Test
    public void isEmpty() {
        List<String> linkedList = new LinkedList<>();
        Assert.assertTrue(linkedList.isEmpty());

        linkedList.add(FIRST_ITEM);
        Assert.assertFalse(linkedList.isEmpty());
    }

    @Test
    public void isEmptyAfterRemove() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add(FIRST_ITEM);
        Assert.assertFalse(linkedList.isEmpty());

        linkedList.remove(0);
        Assert.assertTrue(linkedList.isEmpty());
    }
}

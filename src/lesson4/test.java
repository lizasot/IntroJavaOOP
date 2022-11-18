package lesson4;

import java.util.Arrays;
import java.util.List;

public class test<E> {
    E[] elementData;
    int size;
    private E[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, minCapacity);
    }

    private E[] grow() {
        return grow(size + 1);
    }

    private void add(E e, E[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    public test(E obj) {
        add(obj, elementData, size);
    }
}

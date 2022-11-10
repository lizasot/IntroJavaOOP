package lesson3;

import java.util.*;
import java.util.function.Consumer;

public class MyCollection<E> implements Collection {
    private Object[] elementData;
    private int size;
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOfRange(Object o, int start, int end) {
        Object[] es = elementData;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    public E get(int ind) {
        return (E) elementData[ind];
    }

    public boolean set(int ind, Object o) {
        if (ind < size) {
            elementData[ind] = o;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size)
            return (Object[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, minCapacity);
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private void add(Object e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    @Override
    public boolean add(Object o) {
        add(o, elementData, size);
        return true;
    }

    private void fastRemove(Object[] es, int i) {
        int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    @Override
    public boolean remove(Object o) {
        Object[] es = elementData;
        int size = this.size;
        int i = 0;
        found: {
            if (o == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(es[i]))
                        break found;
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    @Override
    public void clear() {
        final Object[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }

    //Сдвигает все элементы, начиная с hi на позицию lo
    private void shiftTailOverGap(Object[] es, int lo, int hi) {
        System.arraycopy(es, hi, es, lo, size - hi);
        for (int to = size, i = (size -= hi - lo); i < to; i++)
            es[i] = null;
    }

    boolean batchRemove(Collection<?> c, boolean complement,
                        int from, int end) {
        //Исключение, если объект null
        Objects.requireNonNull(c);
        Object[] es = elementData;
        int r;
        // Optimize for initial run of survivors
        for (r = from;; r++) {
            if (r == end)
                return false;
            if (c.contains(es[r]) != complement)
                break;
        }
        int w = r++;
        try {
            for (Object e; r < end; r++)
                if (c.contains(e = es[r]) == complement)
                    es[w++] = e;
        } catch (Throwable ex) {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            System.arraycopy(es, r, es, w, end - r);
            w += end - r;
            throw ex;
        } finally {
            shiftTailOverGap(es, w, end);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        return batchRemove(c, true, 0, size);
    }

    @Override
    public boolean removeAll(Collection c) {
        return batchRemove(c, false, 0, size);
    }

    @Override
    public boolean containsAll(Collection c) {
        Objects.requireNonNull(c);
        Object[] es = elementData;
        int r;
        for (r = 0; r < size; r++) {
            if (!c.contains(es[r]))
                return false;
        }
        return true;
    }

    @Override
    public Iterator iterator() {
        return new Itr();
    }
    private class Itr implements Iterator<E> {
        int cursor;
        int lastRet = -1;

        // prevent creating a synthetic constructor
        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyCollection.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            final int size = MyCollection.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] es = elementData;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                cursor = i;
                lastRet = i - 1;
            }
        }
    }

    static <E> E elementAt(Object[] es, int index) {
        return (E) es[index];
    }

    @Override
    public void forEach(Consumer action) {
        Objects.requireNonNull(action);
        final Object[] es = elementData;
        final int size = this.size;
        for (int i = 0; i < size; i++)
            action.accept(elementAt(es, i));
    }

    private static Object[] EMPTY_ELEMENTDATA = {};
    private static Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    public MyCollection(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }
    public MyCollection() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    public MyCollection(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            // replace with empty array.
            elementData = EMPTY_ELEMENTDATA;
        }
    }
}

package ecommerce;

import java.util.*;

public class Multiset<T> extends AbstractCollection<T> implements Collection<T> {

    private final HashMap<T, Integer> content;

    private int modCount = 0;

    public Multiset() {
        content = new HashMap<>();
    }

    public Multiset(Collection<T> collection) {
        content = new HashMap<>();
        this.addAll(collection);
    }

    @Override
    public Iterator<T> iterator() {
        return new MultisetIterator(content.keySet().iterator());
    }

    private class MultisetIterator implements Iterator<T> {

        int expectedModCount;

        MultisetIterator(Iterator<T> keyIterator) {
            this.keyIterator = keyIterator;
            this.lastKey = null;
            this.expectedModCount = modCount;
        }

        Iterator<T> keyIterator;

        T lastKey;

        Integer lastValue;

        @Override
        public boolean hasNext() {
            return keyIterator.hasNext()
                    || (lastKey != null && lastValue < Multiset.this.content.get(lastKey));
        }

        @Override
        public T next() {
            checkForComodification();
            if (lastKey == null || lastValue >= Multiset.this.content.get(lastKey)) {
                lastKey = keyIterator.next();
                lastValue = 1;
            } else {
                lastValue++;
            }
            return lastKey;
        }

        @Override
        public void remove() {
            checkForComodification();
            Integer arity = Multiset.this.content.get(lastKey);
            if (arity == 1) {
                keyIterator.remove();
                lastKey = null;
            } else {
                Multiset.this.content.replace(lastKey, arity - 1);
                lastValue--;
            }
        }

        private void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public int size() {
        Integer s = 0;
        for (Integer i: content.values()) {
            s += i;
        }
        return s;
    }

    @Override
    public boolean add(T t) {
        Integer qty = content.get(t);
        if (qty == null) {
            content.put(t, 1);
        } else {
            content.replace(t, qty + 1);
        }
        modCount++;
        return true;
    }

    @Override
    @SuppressWarnings("All")
    public boolean remove(Object o) {
        Integer arity = content.get(o);
        if (arity == null) {
            return false;
        } else if (arity == 1) {
            content.remove(o);
        } else {
            content.replace((T) o, arity - 1);
        }
        modCount++;
        return true;
    }

    @Override
    @SuppressWarnings("All")
    public boolean contains(Object o) {
        return content.containsKey(o);
    }

    @Override
    public void clear() {
        content.clear();
        modCount++;
    }

    public int arity(T t) {
        Integer qty = content.get(t);
        return qty == null ? 0 : qty;
    }

    public void changeArity(T t, int arity) {
        if (arity < 0) { throw new IllegalArgumentException("arity cannot be negative"); }

        if (arity == 0) {
            remove(t);
        } else {
            content.put(t, arity);
        }
        modCount++;
    }

}

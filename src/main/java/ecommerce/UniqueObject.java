package ecommerce;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public abstract class UniqueObject {

    private static final AtomicLong nextId = new AtomicLong();
    private final long id;

    public UniqueObject() {
        id = nextId.getAndIncrement();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniqueObject uo = (UniqueObject) o;
        return id == uo.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}

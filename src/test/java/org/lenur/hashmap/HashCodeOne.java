package org.lenur.hashmap;

import java.util.Objects;

public class HashCodeOne {
    private final String code;
    private final String inn;

    public HashCodeOne(String code, String inn) {
        this.code = code;
        this.inn = inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashCodeOne one = (HashCodeOne) o;
        return Objects.equals(code, one.code) &&
                Objects.equals(inn, one.inn);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

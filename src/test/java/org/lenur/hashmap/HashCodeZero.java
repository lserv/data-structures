package org.lenur.hashmap;

import java.util.Objects;

public class HashCodeZero {
    private String code;
    private String inn;

    public HashCodeZero(String code, String inn) {
        this.code = code;
        this.inn = inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashCodeZero zero = (HashCodeZero) o;
        return Objects.equals(code, zero.code) &&
                Objects.equals(inn, zero.inn);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

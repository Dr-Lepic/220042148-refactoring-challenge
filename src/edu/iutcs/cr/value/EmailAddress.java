package edu.iutcs.cr.value;

import java.io.Serializable;
import java.util.Objects;

public class EmailAddress implements Serializable {

    private final String value;

    public EmailAddress(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email is mandatory!");
        }

        String normalizedValue = value.trim();
        if (!normalizedValue.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format!");
        }

        this.value = normalizedValue;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

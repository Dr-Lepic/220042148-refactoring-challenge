package edu.iutcs.cr.value;

import java.io.Serializable;
import java.util.Objects;

public class RegistrationNumber implements Serializable {

    private final String value;

    public RegistrationNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Registration number is mandatory!");
        }

        String normalizedValue = value.trim();
        if (!normalizedValue.matches("[A-Za-z0-9-]+")) {
            throw new IllegalArgumentException("Registration number format is invalid!");
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
        if (!(o instanceof RegistrationNumber that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

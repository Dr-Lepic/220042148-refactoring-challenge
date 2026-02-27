package edu.iutcs.cr.value;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public class VehicleYear implements Serializable {

    private final int value;

    public VehicleYear(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Year is mandatory!");
        }

        String normalizedValue = value.trim();
        if (!normalizedValue.matches("\\d{4}")) {
            throw new IllegalArgumentException("Year must be a 4-digit number!");
        }

        int parsedValue = Integer.parseInt(normalizedValue);
        int currentYear = Year.now().getValue();
        if (parsedValue < 1886 || parsedValue > currentYear + 1) {
            throw new IllegalArgumentException("Year is out of valid range!");
        }

        this.value = parsedValue;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleYear that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

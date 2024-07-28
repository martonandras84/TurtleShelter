package com.martonandras.turtleshelter.infrastructure;

import com.martonandras.turtleshelter.infrastructure.validation.exception.InvalidWeightException;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Util class to convert mass values that are realistic as weight of turtles.
 *
 * Supports conversion to Gram.
 * Supported input units: Kilogram, Pound, Ounce
 * TODO - to be improved to support other units based on further requirements
 *
 */
public class MassConverter {

    private static final double conversionRateKilogramToGram = 1000.0d;
    private static final double conversionRatePoundToGram = 453.59237d;
    private static final double conversionRateOunceToGram = 28.3495231d;

    private static final String unitStringKilogram = "kg";
    private static final String unitStringGram = "g";
    private static final String unitStringPound = "lb";
    private static final String unitStringOunce = "oz";

    public static Long convertToGram(Long weight, String unit) {

        if (ObjectUtils.isEmpty(weight) && ObjectUtils.isEmpty(unit)) {
            return null;
        }

        if (unitStringGram.equals(unit)) {
            return weight;
        }

        double conversionRate;
        if (unitStringKilogram.equalsIgnoreCase(unit)) {
            conversionRate = conversionRateKilogramToGram;
        } else if (unitStringPound.equalsIgnoreCase(unit)) {
            conversionRate = conversionRatePoundToGram;
        } else if (unitStringOunce.equalsIgnoreCase(unit)) {
            conversionRate = conversionRateOunceToGram;
        } else {
            throw new InvalidWeightException(String.format(InvalidWeightException.INVALID_UNIT, unit));
        }

        return Double.valueOf(weight.doubleValue() * conversionRate).longValue();
    }
}

package com.kyle.springpractice.common.util;

import javax.persistence.AttributeConverter;

public class StringAttributeConverter implements AttributeConverter<String, String> {
    private static final String ENCRYPTION_KEY = "r4u7x!A%D*aG-KPd";

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        }

        return AES256Utils.encrypt(ENCRYPTION_KEY, attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return AES256Utils.decrypt(ENCRYPTION_KEY, dbData);
    }
}

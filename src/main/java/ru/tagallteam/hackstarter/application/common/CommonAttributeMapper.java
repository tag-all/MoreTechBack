package ru.tagallteam.hackstarter.application.common;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public interface CommonAttributeMapper<T> {
    /**
     * Значение.
     *
     * @param object объект.
     * @return значение.
     */
    T valueOf(String object);

    /**
     * Из значения.
     *
     * @param value значение.
     * @return значение.
     */
    String fromValue(Object value);

    /**
     * LongAttributeMapper.
     */
    final class LongCommonAttributeMapper implements CommonAttributeMapper<Long> {

        @Override
        public Long valueOf(String object) {
            return StringUtils.isBlank(object) ? null : Long.parseLong(object);
        }

        @Override
        public String fromValue(Object value) {
            if (Objects.isNull(value)) {
                return null;
            }
            if (!(value instanceof Long) && !(value instanceof Integer)) {
                throw new RuntimeException("value \"" + value + "\" is not correct value. required long value");
            }
            return String.valueOf(value);
        }

    }


    /**
     * StringAttributeMapper.
     */
    final class StringCommonAttributeMapper implements CommonAttributeMapper<String> {

        @Override
        public String valueOf(String object) {
            return object;
        }

        @Override
        public String fromValue(Object value) {
            if (Objects.isNull(value)) {
                return null;
            }
            if (!(value instanceof String)) {
                throw new RuntimeException("value \"" + value + "\" is not correct value. required string value");
            }
            return (String) value;
        }

    }

    /**
     * BooleanAttributeMapper.
     */
    final class BooleanCommonAttributeMapper implements CommonAttributeMapper<Boolean> {

        @Override
        public Boolean valueOf(String object) {
            return StringUtils.isBlank(object) ? null : Boolean.valueOf(object);
        }

        @Override
        public String fromValue(Object value) {
            if (Objects.isNull(value)) {
                return null;
            }
            if (!(value instanceof Boolean)) {
                throw new RuntimeException("value \"" + value + "\" is not correct value. required boolean value");
            }
            return String.valueOf(value);
        }

    }


}

package com.kyle.springpractice.practice.booleanget;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooleanGetTest {

    @DisplayName("type에 따라 get메서드가 다르다")
    @Test
    void getTest() {
        BooleanGet booleanGet = new BooleanGet(true, true);

        assertTrue(booleanGet.getClassBoolean());
        assertTrue(booleanGet.isPrimitiveBoolean());
    }

}
package com.ecommerce.productcatalogueservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorTest {

    @Autowired
    private Calculator calculator;

    @Test
    void testAddHappyCase() {
        //Arrange - create obj
        //Act - call method
        int result = calculator.add(1,2);
        //Assert - check result
        assertEquals(3, result);

    }

    @Test
    void testDivideThrowArithemeticException() {
        //Arrange - create obj
        //Act - call method
        //Assert - check result
        assertThrows(ArithmeticException.class, () -> calculator.divide(1,0));
    }
}
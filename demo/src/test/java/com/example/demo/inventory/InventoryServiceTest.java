package com.example.demo.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InventoryServiceTest {
    @Autowired
    private InventoryService inventoryService;

    @Test
    public void testInventoryService() {
        // Arrange
        Long ticketId = 1L;

        Integer before = inventoryService.getAvailableQuantity(ticketId);

        // Act
        boolean result = inventoryService.deductInventory(ticketId, 5);

        Integer after = inventoryService.getAvailableQuantity(ticketId);

        // Assert
        assertTrue(result);

        assertEquals(
                before - 5,
                after);
    }

    @Test
    void shouldReturnFalseWhenInventoryNotEnough() {

        Long ticketId = 1L;

        boolean result = inventoryService.deductInventory(
                ticketId,
                999999);

        assertFalse(result);
    }
}

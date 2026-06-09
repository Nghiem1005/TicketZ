package com.example.demo.inventory;

public interface InventoryService {
    void preloadInventory();

    Integer getAvailableQuantity(Long ticketId);

    boolean deductInventory(Long ticketId, int quantity);
}

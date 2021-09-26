package com.epam.jwd.repository.storage;

import com.epam.jwd.repository.model.order.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    Repository repository = Repository.getInstance();

    @Test
    public void addOrderTest(){
        int oldSize = repository.gerOrders().size();
        repository.addOrder(new Order(new ArrayList<>(), 10));
        assertEquals(oldSize, repository.gerOrders().size() - 1);
    }

    @Test
    public void removeOrderTest(){
        Order testOrder = new Order(new ArrayList<>(), 10);
        repository.addOrder(testOrder);
        int oldSize = repository.gerOrders().size();
        repository.removeOrder(testOrder);
        assertEquals(oldSize, repository.gerOrders().size() + 1);
    }

}
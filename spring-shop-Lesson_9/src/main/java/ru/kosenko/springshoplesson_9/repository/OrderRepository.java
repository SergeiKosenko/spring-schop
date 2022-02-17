package ru.kosenko.springshoplesson_9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kosenko.springshoplesson_9.model.Order;
import ru.kosenko.springshoplesson_9.model.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("FROM Order o WHERE o.orderStatus = :status")
  List<Order> findAllByOrderStatusEquals(@Param("status") OrderStatus status);
}

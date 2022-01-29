package day01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {
    private List<Order> orders = new ArrayList<>();


    public void saveOrder(Order order) {
        orders.add(order);
    }

    public List<Order> findOrdersByStatus(String status) {
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public long numberOfOrdersByStatus(String status) {
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .count();
    }

    public List<Order> getOrdersBetweenDates(LocalDate firstDate, LocalDate secondDate) {
        return orders.stream()
                .filter(order -> order.getOrderDate().isAfter(firstDate) && order.getOrderDate().isBefore(secondDate))
                .collect(Collectors.toList());
    }

    public boolean isExistOrderHasLessProductThen(int number) {
        return orders.stream()
                .anyMatch(order -> order.getProducts().size() < number);
    }

    public Order getOrderHasMaxProduct() {
        Optional<Order> result = orders.stream()
                .max(Comparator.comparingInt(order -> order.getProducts().size()));
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public List<Order> getOrdersHaveCategory(String category) {
        return orders.stream()
                .filter(order -> order.getProducts()
                        .stream()
                        .anyMatch(product -> product.getCategory().equals(category)))
                .collect(Collectors.toList());

    }

    public List<Product> findProductsOverPrice(int amount) {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getPrice() > amount)
                .distinct()
                .toList();

    }

    public List<Order> sortOrdersByStatusAndOrderedDate() {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getStatus).thenComparing(Order::getOrderDate))
                .toList();
    }
}

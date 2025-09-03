package br.com.mariojp.solid.srp;

public class ReceiptService {

    private final ReceiptFormatter formatter;
    private final OrderMathHandler orderMathHandler;

    public ReceiptService() {
        this.orderMathHandler = new OrderMathHandler();
        this.formatter = new ReceiptFormatter();
    }

    public String generate(Order order) {
       orderMathHandler.calculateAll(order);
       return formatter.montarTextoRecibo(order, orderMathHandler.getSubtotal(),orderMathHandler.getTax(), orderMathHandler.getTotal());
    }
}

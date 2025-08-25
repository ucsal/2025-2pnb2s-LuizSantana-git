package br.com.mariojp.solid.srp;

public class ReceiptService {

    private final TaxCalculator taxCalculator;
    private final ReceiptFormatter formatter;

    public ReceiptService() {
        this.taxCalculator = new TaxCalculator();
        this.formatter = new ReceiptFormatter();
    }

    public String generate(Order order) {
        double subtotal = 0.0;
        for (var item : order.getItems()) {
            subtotal += item.getUnitPrice() * item.getQuantity();
        }
        double tax = taxCalculator.calculateTax(subtotal); // lança se tax.rate faltar ou inválido
        double total = subtotal + tax;

        return formatter.montarTextoRecibo(order, subtotal, tax, total);
    }
}

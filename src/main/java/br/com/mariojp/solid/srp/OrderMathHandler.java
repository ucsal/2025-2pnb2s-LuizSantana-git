package br.com.mariojp.solid.srp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderMathHandler {

    private static final int SCALE = 2; // 2 casas decimais (Real)
    private static final String KEY = "tax.rate";

    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal total;

    // construtor vazio
    public OrderMathHandler() {
        this.subtotal = BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
        this.tax = BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
        this.total = BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public void calculateSubtotal(final Order order) {
        if (order == null) throw new IllegalArgumentException("order não pode ser null");

        subtotal = order.getItems().stream()
                .map(item -> convertDoubleToBigDecimal(item.getUnitPrice())
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTax() {
        BigDecimal taxRate = getConfiguredTaxRate();
        tax = subtotal.multiply(taxRate).setScale(SCALE, RoundingMode.HALF_UP);
        return tax;
    }

    public void calculateTotal() {
        BigDecimal taxValue = calculateTax();
        total = subtotal.add(taxValue).setScale(SCALE, RoundingMode.HALF_UP);
    }

    /**
     * Calcula subtotal, imposto e total em sequência.
     */
    public void calculateAll(final Order order) {
        calculateSubtotal(order);
        calculateTax();
        calculateTotal();
    }

    // === Getters ===
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    // === Utilidades internas ===
    private BigDecimal getConfiguredTaxRate() {
        String val = System.getProperty(KEY);
        if (val == null || val.isBlank()) {
            throw new IllegalStateException("tax.rate obrigatório e não definido");
        }
        try {
            double d = Double.parseDouble(val);
            if (d <= 0) throw new IllegalArgumentException("tax.rate deve ser > 0");
            return BigDecimal.valueOf(d);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato inválido para tax.rate: " + val, e);
        }
    }

    public static BigDecimal convertDoubleToBigDecimal(double value) {
        return BigDecimal.valueOf(value).setScale(SCALE, RoundingMode.HALF_UP);
    }
}

package br.com.mariojp.solid.srp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReceiptFormatter {

    private static final int SCALE = 2;

    public String montarTextoRecibo(Order order, BigDecimal subtotal, BigDecimal tax, BigDecimal total) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RECIBO ===\n");

        for (var item : order.getItems()) {
            BigDecimal itemTotal = BigDecimal.valueOf(item.getUnitPrice())
                    .multiply(BigDecimal.valueOf(item.getQuantity()))
                    .setScale(SCALE, RoundingMode.HALF_UP);

            sb.append(item.getName())
                    .append(" x").append(item.getQuantity())
                    .append(" = ").append(itemTotal).append("\n");
        }

        sb.append("Subtotal: ").append(subtotal.setScale(SCALE, RoundingMode.HALF_UP)).append("\n");
        sb.append("Tax: ").append(tax.setScale(SCALE, RoundingMode.HALF_UP)).append("\n");
        sb.append("Total: ").append(total.setScale(SCALE, RoundingMode.HALF_UP)).append("\n");

        return sb.toString();
    }
}

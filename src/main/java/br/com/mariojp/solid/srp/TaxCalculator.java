package br.com.mariojp.solid.srp;

public class TaxCalculator {

    private static final String KEY = "tax.rate";
    private final double taxRate;

    public TaxCalculator() {
        String val = System.getProperty(KEY);
        if (val == null || val.isBlank()) {
            throw new IllegalStateException("tax.rate obrigatório e não definido");
        }
        try {
            double d = Double.parseDouble(val);
            if (d <= 0) throw new IllegalArgumentException("tax.rate deve ser > 0");
            this.taxRate = d;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato inválido para tax.rate: " + val, e);
        }
    }

    public double calculateTax(double baseAmount) {
        if (baseAmount < 0) throw new IllegalArgumentException("baseAmount não pode ser negativo");
        return baseAmount * taxRate;
    }
}

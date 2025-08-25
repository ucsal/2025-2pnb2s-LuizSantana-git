package br.com.mariojp.solid.srp;

public class TaxConfigurator {

    private static final String KEY = "tax.rate";

    public void setTax(double tax) {
        if (Double.isNaN(tax) || Double.isInfinite(tax) || tax <= 0) {
            throw new IllegalArgumentException("tax deve ser > 0 e finito");
        }
        System.setProperty(KEY, Double.toString(tax));
    }
}

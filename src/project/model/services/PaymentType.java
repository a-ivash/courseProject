package project.model.services;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public enum PaymentType {
    monthly("Payment proceeds every month."),
    once("Payment proceeds only once.");
    private String paymentTypeDescription;

    PaymentType(String paymentTypeDescription) {
        this.paymentTypeDescription = paymentTypeDescription;
    }

    @Override
    public String toString() {
        return this.paymentTypeDescription;
    }
}

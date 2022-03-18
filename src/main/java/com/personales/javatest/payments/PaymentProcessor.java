package com.personales.javatest.payments;

public class PaymentProcessor {

    private PaymentGateway paymentGateway;

    public PaymentProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public boolean makePayment(double amount) {
        PaymentResponse response = paymentGateway.requestPayment(new PaymentRequets(amount));
        if(response.getStatus() == PaymentResponse.PaymentStatus.OK){
            return true;
        }else{
            return false;
        }
    }
}

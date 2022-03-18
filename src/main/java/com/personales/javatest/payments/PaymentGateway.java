package com.personales.javatest.payments;

public interface PaymentGateway {

   PaymentResponse requestPayment(PaymentRequets requets);

}

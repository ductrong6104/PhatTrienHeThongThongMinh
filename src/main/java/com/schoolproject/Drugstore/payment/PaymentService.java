package com.schoolproject.Drugstore.payment;

import java.util.Collection;

public interface PaymentService {
    public Collection<PaymentDto> getAllPayments();
    public PaymentDto getPaymentById(Integer id);
    public PaymentDto updatePayment(PaymentCreationDto paymentCreationDto, Integer id);
    public PaymentDto addPayment(PaymentCreationDto paymentCreationDto);
    public void deletePayment(Integer id);
}

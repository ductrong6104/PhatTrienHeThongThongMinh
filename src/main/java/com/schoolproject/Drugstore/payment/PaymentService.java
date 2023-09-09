package com.schoolproject.Drugstore.payment;


import com.schoolproject.Drugstore.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Collection<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Integer id){
        Optional<Payment> payment =  paymentRepository.findById(id);
        if (payment.isEmpty()){
            throw new DataNotFoundException(id, Payment.class.getSimpleName());

        }
        return paymentRepository.getReferenceById(id);
    }

    public Payment updatePayment(Payment newPayment, Integer id){
        // parameter trong map se la object ma repository tim duoc

        Payment updatePayment = paymentRepository.findById(id).map(payment ->
                {
                    payment.setName(newPayment.getName());
                    payment.setDescription(newPayment.getDescription());
                    payment.setOrders(newPayment.getOrders());
                    return paymentRepository.save(payment);
                }).orElseGet(()->{
            newPayment.setId(id);
            return paymentRepository.save(newPayment);
        });

        return updatePayment;
    }
    public Payment addPayment(Payment payment){
        return paymentRepository.save(payment);
    }

    public void deletePayment(Integer id){
        paymentRepository.deleteById(id);
    }


}

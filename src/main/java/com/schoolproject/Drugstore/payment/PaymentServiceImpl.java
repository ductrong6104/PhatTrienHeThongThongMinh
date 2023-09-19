package com.schoolproject.Drugstore.payment;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.BrandRepository;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyForRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapperDto paymentMapperDto;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapperDto paymentMapperDto, ProductCategoryRepository paymentCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository paymentDosageFormRepository, ProductSpecifyForRepository paymentSpecifyForRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMapperDto = paymentMapperDto;

    }


    @Override
    public Collection<PaymentDto> getAllPayments(){
        return paymentRepository.findAll().stream().map(payment -> paymentMapperDto.toDTO(payment)).toList();
    }

    @Override
    public PaymentDto getPaymentById(Integer id){
        Optional<Payment> payment =  paymentRepository.findById(id);
        if (payment.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return paymentMapperDto.toDTO(paymentRepository.getReferenceById(id));
    }

    @Override
    public PaymentDto updatePayment(PaymentCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        Payment convertProduct = paymentMapperDto.toPayment(newProduct);
        Payment updateProduct = paymentRepository.findById(id).map(payment ->
                {
                    payment.setDescription(newProduct.getDescription());

                    return paymentRepository.save(payment);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return paymentRepository.save(convertProduct);
        });
        return paymentMapperDto.toDTO(updateProduct);

    }

    @Override
    public PaymentDto addPayment(PaymentCreationDto paymentCreationDto){
        Payment payment = paymentMapperDto.toPayment(paymentCreationDto);
        paymentRepository.save(payment);
        return paymentMapperDto.toDTO(payment);
    }
    @Override
    public void deletePayment(Integer id){
        paymentRepository.deleteById(id);
    }


}

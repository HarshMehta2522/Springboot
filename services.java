package com.example.warehouse.service;

import com.example.warehouse.model.Product;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    /*
   Implement the business logic for the ProductService  operations in this class
   Make sure to add required annotations
    */

    @Autowired
    private ProductRepository productRepository;


    //to post all the Product details
    //created->201
    //badRequest->400
    public Object postProduct(Product product) {
        try{
            Product p=this.productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        }catch(Exception e){
                return ResponseEntity.badRequest();
        }
    
    }

    //to get all the Product details
    //ok->200
    //badRequest->400
    public Object getProduct() {
        try{
            List<Product> p=this.productRepository.findAll();
            if(p.isEmpty()){
                throw new ResponseStatusException(HttpStatus.valueOf(400));
            }
            return new ResponseEntity<>(p,HttpStatusCode.valueOf(200));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }
        
    }

    //to get the product with the value(pathVariable)va
    //ok()->200
    //badRequest()->400
    public ResponseEntity<Object> getSimilarVendor(String value) {
        List<Product> p=this.productRepository.findByVendor(value);
        try{
            if(p.isEmpty()){
                throw new ResponseStatusException(HttpStatus.valueOf(400));
            }
            return ResponseEntity.status(HttpStatus.OK).body(p);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.valueOf(400));

        }

    }


    //to update the Product with id as pathVariable and Product as object in RequestBody
    //ok->200
    //badRequest->400
    public ResponseEntity<Object> updateProduct(int id, Product product) {
        try{
            Optional<Product> p=this.productRepository.findById(id);
            if(p.isPresent()){
                Product pData=p.get();
                pData.setPrice(product.getPrice());
                pData.setStock(product.getStock());
                this.productRepository.save(pData);
                return new ResponseEntity<>(p,HttpStatusCode.valueOf(200));
            }
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.valueOf(400));

        }
       
    }

    // to delete the product by using id as PathVariable
    //ok->200
    //badRequest->400
    public ResponseEntity<Object> deleteProductById(int id) {
        try{
            Optional<Product> p=this.productRepository.findById(id);
            if(p.isEmpty())throw new ResponseStatusException(HttpStatus.valueOf(400));
            this.productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }
    }
}

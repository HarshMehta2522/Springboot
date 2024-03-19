package com.example.warehouse.controller;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    /*
  This controller would be responsible for the ProductController endpoints
  Add the required annotations and create the endpoints
   */

    @Autowired
    private ProductService productService;

    //to add the Product details using Product object
    @PostMapping("/product/add")
    private Object postProduct( @RequestBody Product product){
        return this.productService.postProduct(product);
    }

    //to get all the Product details
    @GetMapping("/product/get")
    private Object getProduct(){
        return this.productService.getProduct();
    }


    //to update the product with id as pathVariable and product as object in RequestBody
    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProduct( @PathVariable int id,@RequestBody Product product){
        return this.productService.updateProduct(id, product);
    }

    // to delete the product by using id as PathVariable
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProductById( @PathVariable int id){
        return this.productService.deleteProductById(id);
    }

    //to get the product items by vendor
    @GetMapping("/product/vendor")
    public ResponseEntity<Object> getSimilarVendor( String value){
        return this.productService.getSimilarVendor(value);
    }
}
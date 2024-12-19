package cz.sda.java.remotesk1.Invoices.controller.web;

import cz.sda.java.remotesk1.Invoices.controller.web.request.CreateProduct;
import cz.sda.java.remotesk1.Invoices.controller.web.request.UpdateProduct;
import cz.sda.java.remotesk1.Invoices.model.Product;
import cz.sda.java.remotesk1.Invoices.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    String getAllProducts(Model model) {
        model.addAttribute("products",productService.getAllProducts());
        model.addAttribute("createProduct",new Product());
        return "products";
    }

    @PostMapping("/add")
    String addProduct(@Valid CreateProduct product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("createProduct",product);
            model.addAttribute("products",productService.getAllProducts());
            return "products";
        }

        productService.addProduct(product.getName(),product.getPrice());
        return "redirect:/products/";
    }
    @GetMapping("/delete/{id}")
    String deleteProduct(@PathVariable String id, Model model) {
        productService.removeProduct(id);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    String getProductById(@PathVariable String id, Model model) {
        model.addAttribute("updateProduct", productService.getProduct(id));
        return "edit-product";
    }

//    @PostMapping("/update/{id}")
//    String updateProduct(@PathVariable String id, Product product, Model model) {
//        productService.updateProduct(product.getId(),new Product(product.getId(),product.getName(),product.getPrice()));
//        return "redirect:/products/";
//    }

    @PostMapping("/update/{id}")
    String updateProduct(@PathVariable String id, @Valid UpdateProduct product,BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            model.addAttribute("updateProduct",product);
            return "edit-product";
        }
        
        productService.updateProduct(product.getId(),new Product(product.getId(),product.getName(),product.getPrice()));
        return "redirect:/products/";
    }

}

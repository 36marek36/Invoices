package cz.sda.java.remotesk1.Invoices.controller.web;
import cz.sda.java.remotesk1.Invoices.controller.rest.request.UpdateProduct;
import cz.sda.java.remotesk1.Invoices.model.Product;
import cz.sda.java.remotesk1.Invoices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("product",Product.builder().build());
        return "products";
    }

    @PostMapping("/add")
    String addProduct(Product product, Model model) {
        productService.addProduct(product.name(),product.price());
        return "redirect:/products/";
    }
    @GetMapping("/delete/{id}")
    String deleteProduct(@PathVariable String id, Model model) {
        productService.removeProduct(id);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    String updateProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "edit-product";
    }

    @PostMapping("/update/{id}")
    String updateProduct(@PathVariable String id, UpdateProduct product, Model model) {
        productService.updateProduct(id,product);
        return "redirect:/products/";
    }

}

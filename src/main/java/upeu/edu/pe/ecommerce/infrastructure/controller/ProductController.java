package upeu.edu.pe.ecommerce.infrastructure.controller;

import java.io.IOException;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import upeu.edu.pe.ecommerce.app.service.ProductService;
import upeu.edu.pe.ecommerce.infrastructure.entity.ProductEntity;
import upeu.edu.pe.ecommerce.infrastructure.entity.UserEntity;

@Controller
@RequestMapping("/admin/products") //localhost:8080/
public class ProductController {

    private final ProductService productService;
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String create() {
        return "admin/products/create";
    }
    
    @GetMapping("/show")
    public String showProduct(Model model) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        Iterable<ProductEntity> products = productService.getProductsByUser(userEntity);
        model.addAttribute("products", products);
        return "admin/products/show";
    }
    
    @PostMapping("/saveProduct")
    public String saveProduct(ProductEntity product, MultipartFile multipartfile) throws IOException{
        log.info("nombre del product:{}", product);
        productService.saveProduct(product, multipartfile);
        return "redirect:/admin";
    }
    
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        ProductEntity product = productService.getProductById(id);
        log.info("nombre del product:{}", product);
        model.addAttribute("product", product);
        return "admin/products/edit";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

}

package upeu.edu.pe.ecommerce.infrastructure.controller;

import java.io.IOException;
import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import upeu.edu.pe.ecommerce.app.service.ProductService;
import upeu.edu.pe.ecommerce.infrastructure.entity.ProductEntity;
import upeu.edu.pe.ecommerce.infrastructure.entity.UserEntity;

@CrossOrigin(origins = "http://4200")
@RestController
@RequestMapping("api") //localhost:8080/
public class ProductControllerApi {

    private final ProductService productService;
    private final Logger log = LoggerFactory.getLogger(ProductControllerApi.class);

    public ProductControllerApi(ProductService productService) {
        this.productService = productService;
    }
    
    //Ver productos
    @GetMapping("/show")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ProductEntity> showProduct() {
        UserEntity user = new UserEntity();
        user.setId(1);
        return productService.getProductsByUser(user);
    }

    
    //crear product
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduct(@RequestBody ProductEntity product, MultipartFile multipartfile) throws IOException {
        return productService.saveProduct(product, multipartfile);
    }
    //buscar producto por Id
    @GetMapping("/product/{id}")
    public ProductEntity show(@PathVariable Integer id) {
        return productService.getProductById(id);
    }
    
    //editar un product
    @PutMapping("/product/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity editProduct(@RequestBody ProductEntity product, @PathVariable Integer id, MultipartFile multipartfile) throws IOException {
        ProductEntity productActual = productService.getProductById(id);
        productActual.setDescription(product.getDescription());
        productActual.setName(product.getName());
        productActual.setPrice(product.getPrice());
        productActual.setUserEntity(product.getUserEntity());
        return productService.saveProduct(productActual, multipartfile);
        // log.info("Product obtenido: {}", product);
        //model.addAttribute("product", product);
        //return "admin/products/edit";
    }

    //eliminar un product
    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        // return "redirect:/admin/products/show";
        return true;
    }
    

}

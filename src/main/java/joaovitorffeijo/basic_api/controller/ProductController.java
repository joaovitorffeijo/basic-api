package joaovitorffeijo.basic_api.controller;

import joaovitorffeijo.basic_api.model.common.APIResponse;
import joaovitorffeijo.basic_api.model.product.ProductDTO;
import joaovitorffeijo.basic_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/page")
    public APIResponse<ProductDTO> getProductsPage(
            @RequestParam(value = "supplierId", required = false) Long supplierId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return new APIResponse<>(HttpStatus.OK.value(), "Products founded", productService.getProductsPage(supplierId, page, size));
    }

    @GetMapping("/{id}")
    public APIResponse<ProductDTO> getProduct(@PathVariable Long id) {
        return new APIResponse<>(HttpStatus.OK.value(), "Product founded", productService.getProduct(id));
    }

    @PostMapping
    public APIResponse<ProductDTO> save(@RequestBody ProductDTO productDTO) {
        return new APIResponse<>(HttpStatus.OK.value(), "Product saved", productService.save(productDTO));
    }

    @PutMapping("/{id}")
    public APIResponse<ProductDTO> edit(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return new APIResponse<>(HttpStatus.OK.value(), "Product updated", productService.edit(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public APIResponse<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new APIResponse<>(HttpStatus.OK.value(), "Product deleted", null);
    }

    @DeleteMapping("/multiple/{idList}")
    public APIResponse<Void> deleteMultiple(@PathVariable List<Long> idList) {
        productService.deleteMultiple(idList);
        return new APIResponse<>(HttpStatus.OK.value(), "Products deleted", null);
    }
}

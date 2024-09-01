package joaovitorffeijo.basic_api.service;

import joaovitorffeijo.basic_api.model.product.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<ProductDTO> getProductsPage(Long supplierId, int page, int size);

    ProductDTO getProduct(Long id);

    ProductDTO save(ProductDTO productDTO);

    ProductDTO edit(Long id, ProductDTO productDTO);

    void delete(Long id);

    void deleteMultiple(List<Long> idList);
}

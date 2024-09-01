package joaovitorffeijo.basic_api.service.impl;

import jakarta.transaction.Transactional;
import joaovitorffeijo.basic_api.model.product.Product;
import joaovitorffeijo.basic_api.model.supplier.Supplier;
import joaovitorffeijo.basic_api.model.product.ProductDTO;
import joaovitorffeijo.basic_api.repository.ProductRepository;
import joaovitorffeijo.basic_api.repository.SupplierRepository;
import joaovitorffeijo.basic_api.service.ProductService;
import joaovitorffeijo.basic_api.util.StringUtils;
import joaovitorffeijo.basic_api.util.diff.ObjectDiffBuilder;
import joaovitorffeijo.basic_api.util.diff.ObjectDiffData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public Page<ProductDTO> getProductsPage(Long supplierId, int page, int size) {
        Supplier supplier = Optional.ofNullable(supplierId)
                .flatMap(id -> supplierRepository.findById(id))
                .orElse(null);

        return productRepository.getProductsPage(supplier, PageRequest.of(page, size))
                .map(ProductDTO::new);
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return productRepository.findById(id)
                .map(ProductDTO::new)
                .orElse(null);
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();

        validateAndSetFields(product, productDTO);

        try {
            return new ProductDTO(productRepository.save(product));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error");
        }
    }

    @Override
    @Transactional
    public ProductDTO edit(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No entity with the specified id was found");
        }

        validateAndSetFields(product, productDTO);

        try {
            return new ProductDTO(productRepository.save(product));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No entity with the specified id was found");
        }

        try {
            productRepository.delete(product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error");
        }
    }

    @Override
    @Transactional
    public void deleteMultiple(List<Long> idList) {
        for (Long id : idList) {
            delete(id);
        }
    }

    private ObjectDiffData validateAndSetFields(Product entity, ProductDTO dto) {
        var odb = new ObjectDiffBuilder(Product.class);

        if (StringUtils.isNullOrEmpty(dto.getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: Name");
        else
            odb.checkDiffAndSet("Name", entity.getName(), dto.getName(), entity::setName);

        odb.checkDiffAndSet("Price", entity.getPrice(), dto.getPrice(), entity::setPrice);

        Supplier supplier = supplierRepository.findById(dto.getSupplier().getId()).orElse(null);

        if (supplier == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplier not found");
        else
            odb.checkDiffAndSet("Supplier", entity.getSupplier(), supplier, entity::setSupplier);

        return odb.build();
    }
}

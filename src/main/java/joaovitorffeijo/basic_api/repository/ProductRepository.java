package joaovitorffeijo.basic_api.repository;

import joaovitorffeijo.basic_api.model.product.Product;
import joaovitorffeijo.basic_api.model.supplier.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, CrudRepository<Product, Long> {

    @Query("SELECT products FROM Product products " +
            "WHERE (products.supplier = ?1 OR ?1 IS NULL)")
    Page<Product> getProductsPage(Supplier supplier, Pageable pageable);
}

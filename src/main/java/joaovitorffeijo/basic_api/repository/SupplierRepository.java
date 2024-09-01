package joaovitorffeijo.basic_api.repository;

import joaovitorffeijo.basic_api.model.supplier.Supplier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long>, CrudRepository<Supplier, Long> { }

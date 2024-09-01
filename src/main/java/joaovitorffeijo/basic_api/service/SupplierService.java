package joaovitorffeijo.basic_api.service;

import joaovitorffeijo.basic_api.model.supplier.SupplierDTO;

import java.util.List;

public interface SupplierService {

    List<SupplierDTO> getSuppliers();

    SupplierDTO getSupplier(Long id);

    SupplierDTO save(SupplierDTO supplierDTO);

    SupplierDTO edit(Long id, SupplierDTO supplierDTO);

    void delete(Long id);

    void deleteMultiple(List<Long> idList);
}

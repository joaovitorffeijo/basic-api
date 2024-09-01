package joaovitorffeijo.basic_api.service.impl;

import jakarta.transaction.Transactional;
import joaovitorffeijo.basic_api.model.product.Product;
import joaovitorffeijo.basic_api.model.supplier.Supplier;
import joaovitorffeijo.basic_api.model.supplier.SupplierDTO;
import joaovitorffeijo.basic_api.repository.SupplierRepository;
import joaovitorffeijo.basic_api.service.SupplierService;
import joaovitorffeijo.basic_api.util.StringUtils;
import joaovitorffeijo.basic_api.util.diff.ObjectDiffBuilder;
import joaovitorffeijo.basic_api.util.diff.ObjectDiffData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("SupplierService")
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<SupplierDTO> getSuppliers() {
        return StreamSupport.stream(supplierRepository.findAll().spliterator(), false)
                .map(SupplierDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getSupplier(Long id) {
        return supplierRepository.findById(id)
                .map(SupplierDTO::new)
                .orElse(null);
    }

    @Override
    @Transactional
    public SupplierDTO save(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();

        validateAndSetFields(supplier, supplierDTO);

        try {
            return new SupplierDTO(supplierRepository.save(supplier));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error");
        }
    }

    @Override
    @Transactional
    public SupplierDTO edit(Long id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);

        if (supplier == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No entity with the specified id was found");
        }

        validateAndSetFields(supplier, supplierDTO);

        try {
            return new SupplierDTO(supplierRepository.save(supplier));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);

        if (supplier == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No entity with the specified id was found");
        }

        try {
            supplierRepository.delete(supplier);
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

    private ObjectDiffData validateAndSetFields(Supplier entity, SupplierDTO dto) {
        var odb = new ObjectDiffBuilder(Product.class);

        if (StringUtils.isNullOrEmpty(dto.getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: Name");
        else
            odb.checkDiffAndSet("Name", entity.getName(), dto.getName(), entity::setName);

        return odb.build();
    }
}

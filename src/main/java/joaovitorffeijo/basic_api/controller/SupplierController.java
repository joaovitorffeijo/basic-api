package joaovitorffeijo.basic_api.controller;

import joaovitorffeijo.basic_api.model.common.APIResponse;
import joaovitorffeijo.basic_api.model.supplier.SupplierDTO;
import joaovitorffeijo.basic_api.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/list")
    public APIResponse<List<SupplierDTO>> getSuppliers() {
        return new APIResponse<>(HttpStatus.OK.value(), "Suppliers found", supplierService.getSuppliers());
    }

    @GetMapping("/{id}")
    public APIResponse<SupplierDTO> getSupplier(@PathVariable Long id) {
        return new APIResponse<>(HttpStatus.OK.value(), "Supplier found", supplierService.getSupplier(id));
    }

    @PostMapping
    public APIResponse<SupplierDTO> save(@RequestBody SupplierDTO supplierDTO) {
        return new APIResponse<>(HttpStatus.OK.value(), "Supplier saved", supplierService.save(supplierDTO));
    }

    @PutMapping("/{id}")
    public APIResponse<SupplierDTO> edit(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        return new APIResponse<>(HttpStatus.OK.value(), "Supplier updated", supplierService.edit(id, supplierDTO));
    }

    @DeleteMapping("/{id}")
    public APIResponse<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return new APIResponse<>(HttpStatus.OK.value(), "Supplier deleted", null);
    }

    @DeleteMapping
    public APIResponse<Void> deleteMultiple(@RequestBody List<Long> idList) {
        supplierService.deleteMultiple(idList);
        return new APIResponse<>(HttpStatus.OK.value(), "Suppliers deleted", null);
    }
}

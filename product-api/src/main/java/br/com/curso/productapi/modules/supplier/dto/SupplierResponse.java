package br.com.curso.productapi.modules.supplier.dto;

import br.com.curso.productapi.modules.supplier.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {

    private Integer id;
    private String name;


    public static SupplierResponse of(Supplier supplier){
        var response = new SupplierResponse();
        BeanUtils.copyProperties(supplier, response);
        return response;
    }

}

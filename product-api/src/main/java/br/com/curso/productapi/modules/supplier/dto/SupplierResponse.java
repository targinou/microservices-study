package br.com.curso.productapi.modules.supplier.dto;

import br.com.curso.productapi.modules.category.dto.CategoryResponse;
import br.com.curso.productapi.modules.product.model.Product;
import br.com.curso.productapi.modules.supplier.model.Supplier;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

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

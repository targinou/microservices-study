package br.com.curso.productapi.modules.supplier.dto;

import lombok.Data;

@Data
public class SupplierRequest {

    private String name;
    private Integer quantityAvailable;
    private Integer suplierId;
    private Integer categoryId;
}

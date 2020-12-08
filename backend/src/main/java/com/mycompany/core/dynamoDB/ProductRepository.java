package com.mycompany.core.dynamoDB;

import com.mycompany.api.dynamoDB.ProductCatalog;
import com.mycompany.dto.ProductCatalogDTO;

import java.util.Optional;

public interface ProductRepository {

    ProductCatalog createItems(ProductCatalogDTO dto);
    Optional<ProductCatalog> retrieveItem(Long id);
    
}
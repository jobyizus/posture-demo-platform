package com.mycompany.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.core.dynamoDB.ProductRepository;
import com.mycompany.dto.ProductCatalogDTO;
import com.mycompany.api.dynamoDB.ProductCatalog;


@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {


    private ProductRepository repository;

    public ProductResource(ProductRepository repository) {
        this.repository = repository;
    }

   @GET
    @Path("{id}")
    public ProductCatalog ProductCatalog(@PathParam("id") Long id) {
        return repository.retrieveItem(id)
                .orElseThrow(() ->
                        new WebApplicationException("ProductCatalog not found", 404));
    }

    @POST
    public ProductCatalog create(ProductCatalogDTO dto) {
        return repository.createItems(dto);
    }



    
}
package com.mycompany.resources;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.core.dynamoDB.ProductCatalogRepository;
import com.mycompany.dto.ProductCatalogDTO;
import com.mycompany.api.dynamoDB.ProductCatalog;


@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {


    private ProductCatalogRepository repository;

    public ProductResource(ProductCatalogRepository repository) {
        this.repository = repository;
    }



  /*  @GET
    @Path("{id}")
    public ProductCatalog ProductCatalog(@PathParam("id") Long id) {
        return repository.retrieveItem(id)
                .orElseThrow(() ->
                        new WebApplicationException("ProductCatalog not found", 404));
    }*/

    @POST
    public ProductCatalog create(ProductCatalogDTO dto) {
        return repository.createItems(dto);
    }

  /*  @PUT
    @Path("{id}")
    public ProductCatalog update(@PathParam("id") Long id, ProductCatalogDTO dto) {
        return repository.updateExistingAttributeConditionally(id, dto)
                .orElseThrow(() ->
                        new WebApplicationException("ProductCatalog not found", 404));
    }*/

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        repository.deleteItem(id);
        return Response.ok().build();
    }
    
}
package com.mycompany.dto;

import java.util.List;
import java.util.Set;

public class ProductCatalogDTO {

    private Long id;
    private String title;
    private String ISBN;
    private List<String> bookAuthors;
    private String someProp;

    public ProductCatalogDTO(){

    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public List<String> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<String> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public String getSomeProp() {
        return someProp;
    }

    public void setSomeProp(String someProp) {
        this.someProp = someProp;
    }

    
    
}
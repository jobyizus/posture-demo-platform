package com.mycompany;

import javax.validation.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class EventsConfiguration extends Configuration {

    @NotEmpty
    private String dateFormat;

    public String getDateFormat() {
        return dateFormat;
    }
}

package com.mycompany;

import com.mycompany.core.DummyEventRepository;
import com.mycompany.core.EventRepository;
import com.mycompany.core.dynamoDB.ProductRepository;
import com.mycompany.core.dynamoDB.ProductRepositoryImp;
import com.mycompany.resources.EventResource;
import com.mycompany.resources.ProductResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.text.SimpleDateFormat;

public class EventsApplication extends Application<EventsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new EventsApplication().run(args);
    }

    @Override
    public String getName() {
        return "Events";
    }


    @Override
    public void run(final EventsConfiguration configuration,
                    final Environment environment) {
        environment.getObjectMapper().setDateFormat(new SimpleDateFormat(configuration.getDateFormat()));

        EventRepository repoEvent = new DummyEventRepository();
        ProductRepository repoProd = new ProductRepositoryImp();
            
        EventResource eventResource = new EventResource(repoEvent);
        ProductResource productResource = new ProductResource(repoProd);

        environment.jersey().register(eventResource);
        environment.jersey().register(productResource);

    }

}

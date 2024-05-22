package services;

import java.io.Serializable;

public abstract class ServiceDecorator extends Services implements Serializable{

    Services decoratedService;

    public ServiceDecorator(Services decoratedService) {
        this.decoratedService = decoratedService;
    }
    
    @Override
    public int getPrice() {
        return decoratedService.getPrice();
    }

    @Override
    public String toString() {
        return decoratedService.toString();
    }
}

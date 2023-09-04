package project.monitoring.grafanapractice.core.web.product.exception;

import project.monitoring.grafanapractice.common.codeandmessage.specs.product.ProductCodeAndMessage;
import project.monitoring.grafanapractice.common.exception.DomainException;

public class ProductNotFoundException extends DomainException {

    public ProductNotFoundException() {
        super(ProductCodeAndMessage.PRODUCT_NOT_FOUND);
    }
}

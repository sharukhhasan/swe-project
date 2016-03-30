package models.forms;

import models.Product;
import play.data.validation.Constraints;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Sharukh on 3/21/16.
 */
public class ProductForm {
    public static List<Product> allProducts = new ArrayList<Product>();

    public byte[] productImage;
    @Constraints.Required
    public String productName;
    @Constraints.Required
    public String productDescription;
    @Constraints.Required
    public int productQuantity;
    @Constraints.Required
    public String productManufacturer;
    @Constraints.Required
    public Double productPrice;
    @Constraints.Required
    public int productWidth;
    @Constraints.Required
    public int productHeight;
    @Constraints.Required
    public int productLength;
    @Constraints.Required
    public int productWeight;
    @Constraints.Required
    public String productColor;
    public String datePosted;

    public Product formToProduct(ProductForm productForm)
    {
        Product product = new Product();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        product.productName = productForm.productName;
        product.productDescription = productForm.productDescription;
        product.productQuantity = productForm.productQuantity;
        product.productManufacturer = productForm.productManufacturer;
        product.productPrice = productForm.productPrice;
        product.datePosted = dateFormat.format(date);
        product.productWidth = productForm.productWidth;
        product.productHeight = productForm.productHeight;
        product.productLength = productForm.productLength;
        product.productWeight = productForm.productWeight;
        product.productColor = productForm.productColor;
        return product;
    }

    public static List<Product> all()
    {
        return allProducts;
    }
}

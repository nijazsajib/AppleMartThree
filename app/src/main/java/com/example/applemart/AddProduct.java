package com.example.applemart;

class AddProduct {
    public String product_image_url;
    public String product_name;
    public String product_price;
    public String product_description;
    public String product_discount;
    public String product_available_quantity;
    public String product_unit;


    public AddProduct(){}

    public AddProduct(String imageurl, String productname, String productprice) {
    this.product_image_url =imageurl;
    this.product_name=productname;
    this.product_price=productprice;

    }

    public AddProduct(String imageurl, String productname, String productprice, String productdisc, String productavailableQuantity, String productdiscount, String productunit) {


        this.product_image_url = imageurl;
        this.product_name = productname;
        this.product_price = productprice;
        this.product_description = productdisc;
        this.product_discount = productdiscount;
        this.product_available_quantity = productavailableQuantity;
        this.product_unit = productunit;






    }


  /*  public String product_image_url;
    public String product_name;
    public String product_price;
    public AddProduct(){}

    public AddProduct(String url, String name,String price) {
        this.product_image_url = url;
        this.product_name = name;
        this.product_price = price;

    }

    public String getProduct_image_url() {
        return product_image_url;
    }
    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

*/

}

/*

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }




}
*/


/*
    public String p_image_url; //firebase image link

    public String p_name;
    public String p_description;
    public String p_price;
    public String p_id;
    public String p_discount;
    public String p_quantity;
    public String p_unit;

    public AddProduct(String p_image_url, String p_name,String p_price) {
        this.p_image_url = p_image_url;
        this.p_name = p_name;
        this.p_description = p_description;
        this.p_price = p_price;
        this.p_id = p_id;
        this.p_discount = p_discount;
        this.p_quantity = p_quantity;
        this.p_unit = p_unit;
    }

    public AddProduct() {
    }
*/

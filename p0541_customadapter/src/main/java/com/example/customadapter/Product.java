package com.example.customadapter;

//Неправильно: по феншую робити через геттери/сеттери

public class Product {
    String name;
    int picture;
    int price;
    boolean checkbox;

    Product(String _name, int _picture, int _price, boolean _checkbox) {
        name = _name;
        picture = _picture;
        price = _price;
        checkbox = _checkbox;
    }
}

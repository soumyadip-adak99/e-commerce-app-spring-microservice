INSERT INTO category (id, name, description)
VALUES (nextval('category_seq'), 'Electronics', 'Electronic gadgets and devices'),
       (nextval('category_seq'), 'Fashion', 'Clothing, footwear, and accessories'),
       (nextval('category_seq'), 'Books', 'Educational and non-fiction books'),
       (nextval('category_seq'), 'Home Appliances', 'Appliances for home use'),
       (nextval('category_seq'), 'Groceries', 'Daily household grocery items');


INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Smartphone', 'Android smartphone with 5G', 50, 19999.00,
        (SELECT id FROM category WHERE name = 'Electronics')),

       (nextval('product_seq'), 'Laptop', '15-inch laptop with SSD', 25, 55999.00,
        (SELECT id FROM category WHERE name = 'Electronics')),

       (nextval('product_seq'), 'Men T-Shirt', 'Cotton round neck t-shirt', 100, 499.00,
        (SELECT id FROM category WHERE name = 'Fashion')),

       (nextval('product_seq'), 'Women Sneakers', 'Comfortable running shoes', 40, 2499.00,
        (SELECT id FROM category WHERE name = 'Fashion')),

       (nextval('product_seq'), 'Java Programming Book', 'Complete guide to Java', 60, 799.00,
        (SELECT id FROM category WHERE name = 'Books')),

       (nextval('product_seq'), 'Software Engineering Book', 'Concepts and case studies', 30, 899.00,
        (SELECT id FROM category WHERE name = 'Books')),

       (nextval('product_seq'), 'Microwave Oven', 'Convection microwave oven', 15, 12499.00,
        (SELECT id FROM category WHERE name = 'Home Appliances')),

       (nextval('product_seq'), 'Refrigerator', 'Double door refrigerator', 10, 28999.00,
        (SELECT id FROM category WHERE name = 'Home Appliances')),

       (nextval('product_seq'), 'Basmati Rice', 'Premium quality rice (5kg)', 200, 699.00,
        (SELECT id FROM category WHERE name = 'Groceries')),

       (nextval('product_seq'), 'Cooking Oil', 'Refined sunflower oil (1L)', 150, 189.00,
        (SELECT id FROM category WHERE name = 'Groceries'));

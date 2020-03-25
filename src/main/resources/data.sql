

-- Colors
INSERT INTO boutik.colors(id, label) VALUES
(1, 'White'),
(2, 'Red'),
(3, 'Blue'),
(4, 'Green'),
(5, 'Black')

;

--Sizes
INSERT INTO boutik.sizes(id, label) VALUES
(1, 'XS'),
(2, 'S'),
(3, 'M'),
(4, 'L'),
(5, 'XL'),
(6, 'XXL')

;

--Brands
INSERT INTO boutik.brands(id, label) VALUES
(1, 'Nike'),
(2, 'Pumas')

;

--Stores
INSERT INTO boutik.stores(id, designation) VALUES
(1, 'Kiabi Paris 2eme');


--Shelves
INSERT INTO boutik.shelves(id, designation, store_id) VALUES
(1, 'Fashion', 1),
(2, 'Décalée', 1),
(3, 'Femme enceinte', 1),
(4, 'Homme', 1)

;

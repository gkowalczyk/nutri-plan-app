CREATE TABLE food_products (
                               id UUID PRIMARY KEY,
                               name VARCHAR(150) NOT NULL,
                               calories_per_100g NUMERIC(6, 2) NOT NULL,
                               protein_per_100g NUMERIC(5, 2) NOT NULL,
                               fat_per_100g NUMERIC(5, 2) NOT NULL,
                               carbs_per_100g NUMERIC(5, 2) NOT NULL
);
CREATE TABLE meal_entries (
                              id UUID PRIMARY KEY,
                              user_id VARCHAR(255) NOT NULL,
                              eaten_on DATE NOT NULL,
                              food_product_id UUID NOT NULL,
                              food_product_name VARCHAR(150) NOT NULL,
                              grams NUMERIC(8, 2) NOT NULL CHECK (grams > 0),
                              calories NUMERIC(10, 2) NOT NULL,
                              protein_grams NUMERIC(10, 2) NOT NULL,
                              fat_grams NUMERIC(10, 2) NOT NULL,
                              carbs_grams NUMERIC(10, 2) NOT NULL
);
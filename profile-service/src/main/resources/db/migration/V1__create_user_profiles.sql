CREATE TABLE user_profiles (
                               id UUID PRIMARY KEY,
                               user_id VARCHAR(255) NOT NULL,
                               display_name VARCHAR(100) NOT NULL,
                               birth_date DATE NOT NULL,
                               height_cm INTEGER NOT NULL,
                               nutrition_objective VARCHAR(255) NOT NULL,

                               CONSTRAINT uk_user_profiles_user_id UNIQUE (user_id)
);
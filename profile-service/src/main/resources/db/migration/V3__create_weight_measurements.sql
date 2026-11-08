CREATE TABLE  weight_measurements(
    id UUID primary key,
    profile_id UUID not null,
    weight_kg NUMERIC(5,2) not null,
    measured_date DATE not null,

    CONSTRAINT fk_weight_measurements_profile
    FOREIGN KEY(profile_id)
    REFERENCES user_profiles(id)
);
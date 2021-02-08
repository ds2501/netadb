CREATE TABLE geinin(
    g_id INT auto_increment PRIMARY KEY,
    combi_name VARCHAR(40) NOT NULL,
    description VARCHAR(100),
    office VARCHAR(40),
    debut VARCHAR(40),
    created_at VARCHAR(40) NOT NULL,
    updated_at VARCHAR(40) NOT NULL
);

CREATE TABLE neta(
    n_id INT auto_increment PRIMARY KEY,
    g_id INT NOT NULL,
    neta_name VARCHAR(40) NOT NULL,
    description VARCHAR(100),
    link VARCHAR(500),
    created_at VARCHAR(40) NOT NULL,
    updated_at VARCHAR(40) NOT NULL
);
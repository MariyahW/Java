CREATE TABLE IF NOT EXISTS Run (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    started_on timestamp NOT NULL,
    completed_on timestamp NOT NULL,
    miles INT NOT NULL,
    location VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

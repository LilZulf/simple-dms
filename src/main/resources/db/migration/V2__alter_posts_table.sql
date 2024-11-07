
-- Alter posts table

ALTER TABLE posts 
ADD COLUMN slug VARCHAR(255), 
ADD COLUMN is_active BOOLEAN DEFAULT TRUE, 
ADD COLUMN parent_id INT, 
ADD CONSTRAINT fk_parent_id FOREIGN KEY (parent_id) REFERENCES posts(id);



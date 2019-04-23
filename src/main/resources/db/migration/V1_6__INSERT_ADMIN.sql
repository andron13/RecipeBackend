INSERT INTO UserLogin_roles(UserLogin_id, ROLES)
VALUES (1, 'ROLE_USER'),
       (1, 'ROLE_ADMIN');

INSERT INTO UserLogin (id, username, email, password)
values (1, 'ADMIN', 'blackrailean@gmail.com', '{bcrypt}$2a$10$C86J/QRwyz8sZN8vCViGbOeUuxAZ7f1kCvt9E1T6LcS.0ibaSkrGy');
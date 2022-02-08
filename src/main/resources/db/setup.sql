
create user if not exists 'root'@'localhost' identified by 'jerrywise97';
grant all privileges on employee_db.* to 'root'@'localhost';
flush privileges;
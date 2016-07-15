### DATABASE CONNECTION ###
1. create a role:
CREATE ROLE yucky WITH SUPERUSER LOGIN CREATEDB ENCRYPT PASSWORD '1234';
2. create database:
CREATE DATABASE coffeeshop WITH OWNER yucky;
3. create tables:
run script: https://github.com/levtienbk56/CoffeeShop/blob/master/prototype/database.txt

### BUILD SERVER ###
file war: https://www.dropbox.com/s/bkgwxuvrsrialt7/CoffeeShop.war

### LOGIN ACCOUNT ###
admin: 'tienlv' password: '1111'
seller: 'seller' password: '1111'

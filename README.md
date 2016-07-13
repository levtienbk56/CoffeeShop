### DATABASE CONNECTION ###
1. create a role:
CREATE ROLE yucky WITH LOGIN PASSWORD '1234' SUPERUSER CREATEDB;
2. create database:
CREATE DATABASE coffeeshop ENCODING = 'UTF8';
3. create tables:
run script: https://github.com/levtienbk56/CoffeeShop/blob/master/prototype/database.txt

### BUILD SERVER ###
file war: https://www.dropbox.com/s/3bduaku4fhlesfi/CoffeeShop.war?dl=0

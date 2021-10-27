alter table projects add column creation_date DATE;

update projects set creation_date = '2020.01.01' where id = 1;
update projects set creation_date = '2021.10.15' where id = 2;
update projects set creation_date = '2019.07.24' where id = 3;
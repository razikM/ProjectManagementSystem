alter table developers add column salary int;

update developers
set salary = 1000
where id = 1;

update developers
set salary = 2000
where id = 2;

update developers
set salary = 3000
where id = 3;
insert into developers(id, name, age, gender) values (1, 'John Smith', 22, 'male');
insert into developers(id, name, age, gender) values (2, 'Hilary White', 21, 'female');
insert into developers(id, name, age, gender) values (3, 'Emily Brown', 24, 'diverse');

insert into projects(id, name, description) values (1, 'Landing page', 'TODO: create a landing page');
insert into projects(id, name, description) values (2, 'Web application', 'TODO: create a web application');
insert into projects(id, name) values (3, 'Search engine');

insert into companies(id, name, description) values (1, 'Sinergia', 'Energy provider');
insert into companies(id, name, description) values (2, 'LDA', 'Business solutions');
insert into companies(id, name) values (3, 'Krine');

insert into skills(id, name, level) values (1, 'Java', 'Senior');
insert into skills(id, name, level) values (2, 'C++', 'Junior');
insert into skills(id, name, level) values (3, 'C#', 'Junior');
insert into skills(id, name, level) values (4, 'JavaScript','Middle');

insert into customers(id, name, priority) values (1, 'Steven King', 10);
insert into customers(id, name, priority) values (2, 'John Trebbly', 2);
insert into customers(id, name) values (3, 'Garry Osborn');

insert into developers_projects(developer_id, project_id) values(1,1);
insert into developers_projects(developer_id, project_id) values(1,2);
insert into developers_projects(developer_id, project_id) values(1,3);
insert into developers_projects(developer_id, project_id) values(2,3);
insert into developers_projects(developer_id, project_id) values(3,2);

insert into developers_skills(developer_id, skill_id) values(1,1);
insert into developers_skills(developer_id, skill_id) values(1,2);
insert into developers_skills(developer_id, skill_id) values(2,2);
insert into developers_skills(developer_id, skill_id) values(2,4);
insert into developers_skills(developer_id, skill_id) values(3,1);
insert into developers_skills(developer_id, skill_id) values(3,2);
insert into developers_skills(developer_id, skill_id) values(3,3);

insert into companies_projects(company_id, project_id) values(1,1);
insert into companies_projects(company_id, project_id) values(1,2);
insert into companies_projects(company_id, project_id) values(1,3);
insert into companies_projects(company_id, project_id) values(2,2);
insert into companies_projects(company_id, project_id) values(3,3);

insert into customers_projects(customer_id, project_id) values(1,1);
insert into customers_projects(customer_id, project_id) values(1,2);
insert into customers_projects(customer_id, project_id) values(1,3);
insert into customers_projects(customer_id, project_id) values(2,2);
insert into customers_projects(customer_id, project_id) values(3,3);








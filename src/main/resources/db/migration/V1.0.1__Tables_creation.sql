create table developers
(
    id     int primary key,
    name   varchar(25) not null,
    age    int         not null,
    gender varchar(10)
);

create table projects
(
    id          int primary key,
    name        varchar(25) not null,
    description varchar(128)
);

create table companies
(
    id          int primary key,
    name        varchar(25) not null,
    description varchar(128)
);

create table skills
(
    id    int primary key,
    name  varchar(10) not null,
    level varchar(10) not null
);

create table customers
(
    id       int primary key,
    name     varchar(25) not null,
    priority int default 5
);

create table developers_projects
(
    developer_id int,
    foreign key (developer_id) references developers (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    project_id   int,
    foreign key (project_id) references projects (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table developers_skills
(
    developer_id int,
    foreign key (developer_id) references developers (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    skill_id     int,
    foreign key (skill_id) references skills (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table companies_projects
(
    company_id int,
    foreign key (company_id) references companies (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    project_id int,
    foreign key (project_id) references projects (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table customers_projects
(
    customer_id int,
    foreign key (customer_id) references customers (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    project_id  int,
    foreign key (project_id) references projects (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

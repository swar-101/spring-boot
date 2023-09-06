-- 👨‍🏭👩‍🏭 Employee table

--DDL
create sequence emp_id_seq;
alter sequence emp_id_seq restart with 1; 
drop sequence emp_id_seq;
--

create table emp (
	id bigint not null default nextval('emp_id_seq'),
	fname varchar(50) not null,
	lname varchar(50) not null
);
drop table emp;

--DML
select nextval('emp_id_seq');
select * from emp;


-- 🏬 Department table

--DDL
create sequence dept_id_seq;
alter sequence dept_id_seq restart with 1;
drop sequence dept_id_seq;

--

create table dept(
	id bigint not null default nextval('dept_id_seq'),
	name varchar(50) not null
);
drop table dept;

-- DML
select nextval('dept_id_seq');
select * from dept;

-- 👁
select * from emp;
select * from dept;

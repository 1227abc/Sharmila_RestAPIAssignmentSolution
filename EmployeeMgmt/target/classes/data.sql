insert into roles(role_id, name) values (default,'ADMIN');

insert into users(user_id,username,password) values (default,'admin','$2a$12$JosvHL1exqq3NtnKNCivgeeAkUHdK7w9U46dapyOoxyvQMMaRrdvO');

insert into users_roles(role_id,user_id) values (1,1);

insert into employee(emp_Id,first_name,last_name,email) values (default,'Sharmila','Ramarao','sharmila@gl.com');
insert into employee(emp_Id,first_name,last_name,email) values (default,'Priya','Krishnan','priya@gl.com');
insert into employee(emp_Id,first_name,last_name,email) values (default,'Ashwini','Ranjit','ashwini@gl.com');
insert into employee(emp_Id,first_name,last_name,email) values (default,'Vidya','Madhavan','vidya@gl.com');
insert into employee(emp_Id,first_name,last_name,email) values (default,'Surya','Prabha','surya@gl.com');
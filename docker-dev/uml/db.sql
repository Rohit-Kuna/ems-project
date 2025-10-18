CREATE TABLE "employee" (
  "emp_id" bigint PRIMARY KEY,
  "emp_name" varchar(255) NOT NULL,
  "dob" date,
  "email_id" varchar(255) UNIQUE NOT NULL,
  "salary" bigint NOT NULL DEFAULT 30000,
  "manager_id" bigint,
  "fk_dept_id" bigint NOT NULL
);


CREATE TABLE "department" (
  "dept_id" bigint PRIMARY KEY,
  "dept_name1" varchar(255)
);

CREATE TABLE "address" (
  "address_id" bigint PRIMARY KEY,
  "fk_emp_id" bigint,
  "house_no" bigint,
  "street" varchar(255),
  "zipcode" int NOT NULL
);

CREATE TABLE "project" (
  "project_id" bigint PRIMARY KEY,
  "project_name" varchar(255) UNIQUE NOT NULL,
  "project_start_date" date,
  "project_end_date" date
);

CREATE TABLE "employee_project" (
  "fk_emp_id" bigint PRIMARY KEY,
  "fk_project_id" bigint PRIMARY KEY
);

COMMENT ON COLUMN "employee"."emp_id" IS 'guid';

COMMENT ON COLUMN "employee"."fk_dept_id" IS '0 or many employees to one department';

COMMENT ON COLUMN "department"."dept_id" IS 'guid';

COMMENT ON COLUMN "department"."dept_name" IS 'use enum';

COMMENT ON COLUMN "address"."address_id" IS 'guid';

COMMENT ON COLUMN "address"."fk_emp_id" IS 'many to one';

COMMENT ON COLUMN "project"."project_id" IS 'guid';

ALTER TABLE "employee" ADD FOREIGN KEY ("manager_id") REFERENCES "employee" ("emp_id");

ALTER TABLE "employee" ADD FOREIGN KEY ("fk_dept_id") REFERENCES "department" ("dept_id");

ALTER TABLE "address" ADD FOREIGN KEY ("fk_emp_id") REFERENCES "employee" ("emp_id");

ALTER TABLE "employee_project" ADD FOREIGN KEY ("fk_emp_id") REFERENCES "employee" ("emp_id");

ALTER TABLE "employee_project" ADD FOREIGN KEY ("fk_project_id") REFERENCES "project" ("project_id");

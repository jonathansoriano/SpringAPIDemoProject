INSERT INTO university (name) VALUES
  ('University of California, Berkeley'),
  ('Massachusetts Institute of Technology'),
  ('Ohio State University'),
  ('New York University'),
  ('University of Texas at Austin'),
  ('University of Michigan'),
  ('Stanford University'),
  ('Harvard University'),
  ('Georgia Institute of Technology'),
  ('University of Florida'),
  ('University of Illinois Urbana-Champaign'),
  ('Pennsylvania State University'),
  ('University of Washington'),
  ('Purdue University');

INSERT INTO student (first_name, last_name, dob, resident_city, resident_state, university_id, grade) VALUES
-- UC Berkeley (ID: 1)
('Alice', 'Wong', '2002-03-15', 'San Francisco', 'CA', 1, 'Freshman'),
('Brian', 'Lee', '2001-07-20', 'Oakland', 'CA', 1, 'Sophomore'),
('Carmen', 'Zhou', '2003-01-05', 'San Jose', 'CA', 1, 'Junior'),
('David', 'Chen', '2000-12-10', 'Sacramento', 'CA', 1, 'Junior'),
('Emily', 'Chen', '2002-06-25', 'Fresno', 'CA', 1, 'Senior'),

-- MIT (ID: 2)
('Frank', 'Johnson', '2001-11-18', 'Boston', 'MA', 2, 'Freshman'),
('Grace', 'Smith', '2002-09-22', 'Cambridge', 'MA', 2, 'Sophomore'),
('Hannah', 'Brown', '2003-03-30', 'Worcester', 'MA', 2, 'Junior'),
('Ian', 'Taylor', '2001-08-14', 'Springfield', 'MA', 2, 'Junior'),
('Julia', 'Davis', '2000-10-05', 'Lowell', 'MA', 2, 'Senior'),

-- Ohio State (ID: 3)
('Pedro', 'Soriano', '2002-04-11', 'Columbus', 'OH', 3, 'Freshman'),
('Laura', 'Wilson', '2003-02-09', 'Cleveland', 'OH', 3, 'Freshman'),
('Mark', 'Williams', '2000-05-20', 'Columbus', 'OH', 3, 'Sophomore'),
('Michael', 'Jones', '2002-08-27', 'Cincinnati', 'OH', 3, 'Sophomore'),
('Mike', 'Hall', '2001-12-12', 'Cincinnati', 'OH', 3, 'Sophomore'),
('Nina', 'Allen', '2002-07-17', 'Toledo', 'OH', 3, 'Junior'),
('Jonathan', 'Soriano', '2000-05-11', 'Cincinnati', 'OH', 3, 'Senior'),

-- NYU (ID: 4)
('Paula', 'Lopez', '2002-01-23', 'New York', 'NY', 4, 'Freshman'),
('Quinn', 'Scott', '2001-05-06', 'Buffalo', 'NY', 4, 'Sophomore'),
('Rachel', 'Adams', '2003-03-18', 'Rochester', 'NY', 4, 'Junior'),
('Steve', 'Perez', '2000-11-29', 'Albany', 'NY', 4, 'Junior'),
('Tina', 'Morris', '2000-05-11', 'Yonkers', 'NY', 4, 'Senior'),

-- UT Austin (ID: 5)
('Jonathan', 'King', '2001-06-03', 'Austin', 'TX', 5, 'Freshman'),
('Victor', 'Green', '2002-10-12', 'Dallas', 'TX', 5, 'Sophomore'),
('Jordan', 'Powell', '2003-05-21', 'Houston', 'TX', 5, 'Sophomore'),
('Xander', 'Mitchell', '2000-08-09', 'San Antonio', 'TX', 5, 'Junior'),
('Yara', 'Diaz', '2002-02-14', 'El Paso', 'TX', 5, 'Senior'),

('Zara', 'Anderson', '2004-02-01', 'Ann Arbor', 'MI', 6, 'Freshman'),
('Liam', 'Bennett', '2003-05-22', 'Detroit', 'MI', 6, 'Sophomore'),
('Olivia', 'Carter', '2002-07-19', 'Lansing', 'MI', 6, 'Junior'),
('Noah', 'Diaz', '2000-05-11', 'Flint', 'MI', 6, 'Senior'),

('Emma', 'Foster', '2004-03-10', 'Palo Alto', 'CA', 7, 'Freshman'),
('Mason', 'Garcia', '2003-08-11', 'San Jose', 'CA', 7, 'Sophomore'),
('Ava', 'Patel', '2002-06-16', 'Fremont', 'CA', 7, 'Junior'),
('Ethan', 'Irwin', '2001-11-02', 'Sunnyvale', 'CA', 7, 'Senior'),

('Hannah', 'Soriano', '2004-01-07', 'Cambridge', 'MA', 8, 'Freshman'),
('James', 'Khan', '2003-04-25', 'Boston', 'MA', 8, 'Sophomore'),
('Isabella', 'Long', '2002-02-14', 'Quincy', 'MA', 8, 'Junior'),
('Benjamin', 'Morris', '2001-10-30', 'Somerville', 'MA', 8, 'Senior'),

('Mia', 'Hernandez', '2004-07-09', 'Atlanta', 'GA', 9, 'Freshman'),
('Lucas', 'Owens', '2003-09-12', 'Savannah', 'GA', 9, 'Sophomore'),
('Charlotte', 'Patel', '2002-05-28', 'Augusta', 'GA', 9, 'Junior'),
('Henry', 'Quinn', '2001-12-20', 'Macon', 'GA', 9, 'Senior'),

('Amelia', 'Reed', '2004-06-15', 'Tampa', 'FL', 10, 'Freshman'),
('Elijah', 'Soriano', '2003-03-08', 'Orlando', 'FL', 10, 'Sophomore'),
('Harper', 'Young', '2002-11-06', 'Miami', 'FL', 10, 'Junior'),
('Pedro', 'Smith', '2001-01-19', 'Jacksonville', 'FL', 10, 'Senior'),

('Evelyn', 'Vasquez', '2004-04-02', 'Champaign', 'IL', 11, 'Freshman'),
('Logan', 'White', '2003-07-13', 'Springfield', 'IL', 11, 'Sophomore'),
('Abigail', 'Xu', '2002-09-29', 'Peoria', 'IL', 11, 'Junior'),
('Daniel', 'Young', '2001-06-23', 'Decatur', 'IL', 11, 'Senior'),

('Ella', 'Roberts', '2004-08-17', 'State College', 'PA', 12, 'Freshman'),
('Jackson', 'Adams', '2003-10-03', 'Philadelphia', 'PA', 12, 'Sophomore'),
('Scarlett', 'Brooks', '2002-01-11', 'Pittsburgh', 'PA', 12, 'Junior'),
('Sebastian', 'Clarkson', '2001-03-27', 'Erie', 'PA', 12, 'Senior'),

('Chloe', 'Dixon', '2004-05-19', 'Seattle', 'WA', 13, 'Freshman'),
('Jack', 'Young', '2003-06-06', 'Spokane', 'WA', 13, 'Sophomore'),
('Lily', 'Fleming', '2002-12-16', 'Tacoma', 'WA', 13, 'Junior'),
('Matthew', 'Jackson', '2001-02-04', 'Everett', 'WA', 13, 'Senior'),

('Grace', 'Harvey', '2004-09-25', 'West Lafayette', 'IN', 14, 'Freshman'),
('Jayden', 'Ingram', '2003-01-31', 'Indianapolis', 'IN', 14, 'Sophomore'),
('Victoria', 'Jackson', '2002-10-22', 'Fort Wayne', 'IN', 14, 'Junior'),
('Elijah', 'Kennedy', '2001-04-17', 'Gary', 'IN', 14, 'Senior');





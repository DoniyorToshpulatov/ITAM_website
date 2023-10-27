
INSERT INTO profile
(id,attach_id,deportment_id,email,job_en,job_ru,job_uz,name_en,name_ru,name_uz,password,role,status,surname_en,surname_ru,surname_uz,visible,work_role)
VALUES (-1,null,null, 'admin@gmail.com','admin', 'admin', 'admin', 'Admin', 'Admin', 'Admin','0192023a7bbd73250516f069df18b500','ROLE_ADMIN','ACTIVE',
        'Admin','Admin','Admin',true,'EMPLOYEE')

    ON CONFLICT (id) DO NOTHING;

--TEACHER,MODERATOR,ADMIN,EDUCATION,

-- 新增课程存储过程
CREATE PROCEDURE `aphrodite-user`.`insertCourseData`()
begin
     declare
i int default 0;
    while
i < 100000 do
INSERT INTO `aphrodite-user`.course
(name, content, `type`, start_time, end_time, teacher_id, image, url, create_time, create_user, update_time, update_user)
VALUES('开学第一课', '开学第一课', '', '2022-12-01 00:00:00', '2023-03-31 23:59:59', NULL, '-', '-', now(), 1004, now(), 1004);

     set
i = i + 1;
end while;
end
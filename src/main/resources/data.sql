insert into admin (email, password) values('qwer@naver.com', '1234');

insert into member (email, password, name, position, tel, annual_count, created_at) values('member1@naver.com', '1234', '김1', 'STAFF', '01012345671', 7, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member2@naver.com', '1234', '김2', 'STAFF', '01012345672', 3, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member3@naver.com', '1234', '김3', 'STAFF', '01012345673', 10, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member4@naver.com', '1234', '김4', 'STAFF', '01012345674', 0, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member5@naver.com', '1234', '김5', 'STAFF', '01012345675', 1, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member6@naver.com', '1234', '김6', 'STAFF', '01012345676', 15, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member7@naver.com', '1234', '김7', 'STAFF', '01012345677', 15, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member8@naver.com', '1234', '김8', 'STAFF', '01012345678', 15, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member9@naver.com', '1234', '김9', 'STAFF', '01012345679', 15, now());
insert into member (email, password, name, position, tel, annual_count, created_at) values('member10@naver.com','1234', '김10', 'BOSS', '01012345610', 15, now());



/*
REQUESTED("신청 중"), - 깎고
APPROVED("승인 완료"), - 깎인 상태 그대로
CANCEL_PENDING("취소 중"), - 깎인 상태 그대로
CANCELED("취소 완료"), - 돌려주고
REJECTED("요청 반려"), - 돌려주고
CANCEL_REJECTED("취소 반려"), - 깎인 상태 그대로
COMPLETED("완료"); - 깎인 상태 그대로
*/-- 11
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (1, '2023-02-09', '2023-02-09', 'COMPLETED', '연차1-1', now(), now(), 1, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (1, '2023-09-22', '2023-09-24', 'REQUESTED', '연차1', now(), now(), 3, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (1, '2023-10-10', '2023-10-10', 'APPROVED', '연차1', now(), now(), 1, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (1, '2023-11-11', '2023-11-12', 'CANCEL_PENDING', '연차1', now(), now(), 2, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (1, '2023-05-05', '2023-05-15', 'CANCELED', '연차1', now(), now(), 11, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (1, '2023-08-15', '2023-08-15', 'CANCEL_REJECTED', '연차1', now(), now(), 1, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (1, '2023-04-05', '2023-04-09', 'REJECTED', '연차1', now(), now(), 5, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (2, DATE_ADD(NOW(), INTERVAL 5 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'APPROVED', '연차2-1', now(), now(), 4, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (2, DATE_ADD(NOW(), INTERVAL 9 DAY), DATE_ADD(NOW(), INTERVAL 11 DAY), 'REQUESTED', '연차2-2', now(), now(), 3, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (2,'2023-04-05', '2023-04-09', 'COMPLETED', '연차2-3', now(), now(), 5, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (3, DATE_ADD(NOW(), INTERVAL 2 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), 'CANCELED', '연차3-1', now(), now(), 2, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (3,'2023-03-05', '2023-03-07', 'REJECTED', '연차3-2', now(), now(), 3, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (3,'2023-02-05', '2023-02-09', 'COMPLETED', '연차3-3', now(), now(), 5, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (4, '2023-06-05', '2023-06-09', 'COMPLETED', '연차4-1', now(), now(), 5, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (4, '2023-05-05', '2023-05-06', 'COMPLETED', '연차4-2', now(), now(), 2, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (4, '2022-12-31', '2023-01-03', 'COMPLETED', '연차4-3', now(), now(), 4, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (4, '2023-07-01', '2023-07-04', 'COMPLETED', '연차4-4', now(), now(), 4, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (5, '2023-08-05', '2023-08-09', 'CANCEL_PENDING', '연차5-1', now(), now(), 5, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (5, '2023-03-05', '2023-03-09', 'CANCELED', '연차5-2', now(), now(), 5, '제목');
insert into annual (member_id, start_date, end_date, status, reason, created_at, updated_at, spent_days, summary) values (5, '2023-10-05', '2023-10-13', 'CANCEL_REJECTED', '연차5-3', now(), now(), 9, '제목');


insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (1, DATE_ADD(NOW(), INTERVAL 2 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (2, DATE_ADD(NOW(), INTERVAL 3 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (3, DATE_ADD(NOW(), INTERVAL 4 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (4, DATE_ADD(NOW(), INTERVAL 5 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (5, DATE_ADD(NOW(), INTERVAL 6 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (6, DATE_ADD(NOW(), INTERVAL 7 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (7, DATE_ADD(NOW(), INTERVAL 8 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (8, DATE_ADD(NOW(), INTERVAL 9 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (9, DATE_ADD(NOW(), INTERVAL 10 DAY), 'APPROVED', '의무 당직', now(), now());
insert into duty (member_id, duty_date, status, reason, created_at, updated_at) values (10, DATE_ADD(NOW(), INTERVAL 11 DAY), 'APPROVED', '의무 당직', now(), now());


insert into users (name, email, password)
values ('오승택', 'asd@asd', '$2a$10$iQpHuUeG54bqEbHYKGY6pOKd0/n2vqdqsAZEzs6JFvUF9R88ksD5O'),
('강민수', 'minsu@gmail.com', '$2a$10$i6gp733TWwKsE5V97J8CHO/Kc20SHq0O/7jEVbnwMmUxlLBmBXU..'),
('동언', 'kde123@gmail.com', '$2a$10$Kk1pV8zNhVpY9qMCcV8Hd.qTxCZQZW9JeLEpbArzvbVIGTyr88JVy'),
('허성은', 'sung555@naver.com', '$2a$10$Jv.8v2lt4h7IRAMgcfXijup0/epUNSv.XP.tcQq8XI1wSyI7EH6zy'),
('송어진', 'song@gmail.com', '$2a$10$Jv.8v2lt4h7IRAMgcfXijup0/epUNSv.XP.tcQq8XI1wSyI7EH6zy'),
('박현', 'hyun@gmail.com', '$2a$10$Jv.8v2lt4h7IRAMgcfXijup0/epUNSv.XP.tcQq8XI1wSyI7EH6zy');

INSERT INTO places (
    name,
    content,
    address,
    category
)
VALUES
    ('서울불고기집', '정통 한국 불고기를 맛볼 수 있는 곳', '서울시 강남구 테헤란로 123', 'KOR'),
    ('스시노미치', '신선한 초밥과 다양한 일본 요리를 제공하는 맛집', '서울시 마포구 홍대입구 456', 'JPN'),
    ('마라탕의 왕', '매운 마라탕과 마라샤브샤브 전문점', '서울시 강서구 공항대로 789', 'CHN'),
    ('피자헛', '정통 웨스턴 스타일의 피자를 즐길 수 있는 곳', '서울시 서초구 강남대로 101', 'WES'),
    ('베트남 가든', '베트남 전통 음식을 제공하는 아늑한 레스토랑', '서울시 송파구 가락로 202', 'ETC'),
    ('이탈리안 레스토랑 카르보나라', '정통 이탈리안 요리를 제공하는 고급 레스토랑', '서울시 용산구 한남동 303', 'WES'),
    ('홍콩반점', '홍콩식 짬뽕과 중국 요리가 일품인 곳', '서울시 종로구 종로 404', 'CHN'),
    ('라멘하우스', '매일 새벽에 만든 국물로 만든 진한 라멘', '서울시 강남구 삼성로 505', 'JPN'),
    ('마늘왕', '마늘 요리 전문점, 마늘을 좋아하는 사람이라면 꼭 가봐야 할 곳', '서울시 강북구 수유동 606', 'KOR'),
    ('샤브샤브의 진수', '프리미엄 샤브샤브와 다양한 야채를 즐길 수 있는 곳', '서울시 송파구 석촌호수 707', 'ETC');

INSERT INTO gatherings (
        title,
        content,
        gathering_at,
        closing_at,
        max_participant,
        state,
        talk_thema,
        talk_flag,
        user_id,
        place_id,
        user_name
    )
VALUES
    (
        '오늘 점심은 간단하게 먹고 코딩하자',
        '복잡한 건 이제 그만, 탕비실에서 간단히 한 끼 묵자. 시간 맞춰서 탕비실에서 만납시다. 서로를 알아보는 사인은 손 브이자 표시다..',
        '2025-03-10 12:00:00',
        '2025-03-10 13:00:00',
        3,
        'ING',
        '',
        'I',
        2,  -- 사용자 2번
        1,   -- 장소 1번
        '송어진'
    ),
    (
        '배고프다, 진짜 맛있는 거 먹자',
        '맛집에서 만나는 최고의 한 끼, 이건 진짜다',
        '2025-03-10 18:00:00',
        '2025-03-10 19:30:00',
        5,
        'ING',
        '입맛 살리는 맛집 투어',
        'E',
        2,  -- 사용자 2번
        2,   -- 장소 2번
        '송어진'
    );


INSERT INTO participants (gathering_id, user_id)
VALUES ('1', '1');
INSERT INTO participants (gathering_id, user_id)
VALUES ('1', '2');
INSERT INTO participants (gathering_id, user_id)
VALUES ('1', '3');
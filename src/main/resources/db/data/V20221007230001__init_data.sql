insert into file
values (nextval('file_seq'), '6cc7b0a9-8295-4756-a0d1-5a58031abc1e', 'empty',
        '.png', 'image/png', '', CURRENT_TIMESTAMP);

insert into achievement values (nextval('achievement_seq'), 1, currval('file_seq'), 'Первый вход!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '10');
insert into achievement values (nextval('achievement_seq'), 1, currval('file_seq'), 'Полная коллекция!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '100');
insert into achievement values (nextval('achievement_seq'), 2, currval('file_seq'), 'Второй уровень!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '10');
insert into achievement values (nextval('achievement_seq'), 3, currval('file_seq'), 'Третий уровень!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '5');
insert into achievement values (nextval('achievement_seq'), 4, currval('file_seq'), 'Четвертый уровень!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '15');
insert into achievement values (nextval('achievement_seq'), 5, currval('file_seq'), 'Пятый уровень!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '20');
insert into achievement values (nextval('achievement_seq'), 6, currval('file_seq'), 'Шестой уровень!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '30');
insert into achievement values (nextval('achievement_seq'), 7, currval('file_seq'), 'Седьмой уровень!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '100');
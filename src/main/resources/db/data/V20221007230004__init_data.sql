insert into file
values (nextval('file_seq'), '6cc7b0a9-8295-4756-a0d1-5a58031abc1e', 'empty',
        '.png', 'image/png', '', CURRENT_TIMESTAMP);

insert into achievement values (nextval('achievement_seq'), 1, currval('file_seq'), 'Первый NFT!');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'REWARD_TYPE', 'XP');
insert into achievement_attribute values (nextval('achievement_attribute_seq'), currval('achievement_seq'), 'NUMBER_AWARDS', '10');
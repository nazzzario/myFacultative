create table subject
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table usr
(
    id          bigint auto_increment
        primary key,
    email       varchar(255) not null,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    password    varchar(255) null,
    role        varchar(255) null,
    user_status varchar(255) null,
    username    varchar(255) not null,
    constraint UK_dfui7gxngrgwn9ewee3ogtgym
        unique (username),
    constraint UK_g9l96r670qkidthshajdtxrqf
        unique (email)
);

create table course
(
    id          bigint auto_increment
        primary key,
    course_name varchar(255) null,
    description varchar(255) null,
    end_date    date         null,
    language    varchar(255) null,
    start_date  date         null,
    status      varchar(255) null,
    subject_id  bigint       null,
    usr_id      bigint       null,
    constraint FKm1expnaas0onmafqpktmjixnx
        foreign key (subject_id) references subject (id),
    constraint FKsejfk38mq4692eofie09eqty
        foreign key (usr_id) references usr (id)
);

create table journal
(
    id         bigint auto_increment
        primary key,
    grade      varchar(255) null,
    course_id  bigint       not null,
    student_id bigint       not null,
    constraint FK477muqllyrbi21adftd4egeex
        foreign key (student_id) references usr (id),
    constraint FKo0hpi7432yb90eg9piuy9e38s
        foreign key (course_id) references course (id)
);

create table student_course
(
    student_id bigint not null,
    course_id  bigint not null,
    primary key (student_id, course_id),
    constraint FK6lglmeqpccakp0dhlykn968f2
        foreign key (student_id) references usr (id),
    constraint FKejrkh4gv8iqgmspsanaji90ws
        foreign key (course_id) references course (id)
);


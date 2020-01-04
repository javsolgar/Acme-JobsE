
    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `announcement` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `more_info` varchar(255),
        `text` varchar(1024),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `answer` varchar(1024),
        `has_been_protected` bit not null,
        `has_password` bit not null,
        `hasxxxx` bit not null,
        `justification` varchar(255),
        `moment` datetime(6),
        `optional_application` varchar(255),
        `password` varchar(255),
        `qualifications` varchar(1024),
        `reference` varchar(255),
        `skills` varchar(1024),
        `statement` varchar(1024),
        `status` varchar(255),
        `job_id` integer not null,
        `worker_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `resp_statement` varchar(1024),
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditorrequest` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(1024),
        `firm` varchar(255),
        `moment` datetime(6),
        `resp_statement` varchar(255),
        `status` varchar(255),
        `user_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditrecord` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(1024),
        `is_final_mode` bit,
        `moment` datetime(6),
        `title` varchar(255),
        `auditor_id` integer not null,
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `final_mode` bit not null,
        `slogan` varchar(255),
        `url_picture` varchar(255),
        `url_target` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(1024),
        `goal_bronze` varchar(1024),
        `goal_gold` varchar(1024),
        `goal_silver` varchar(1024),
        `reward_bronze_amount` double precision,
        `reward_bronze_currency` varchar(255),
        `reward_gold_amount` double precision,
        `reward_gold_currency` varchar(255),
        `reward_silver_amount` double precision,
        `reward_silver_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `comercialbanner` (
       `id` integer not null,
        `version` integer not null,
        `final_mode` bit not null,
        `slogan` varchar(255),
        `url_picture` varchar(255),
        `url_target` varchar(255),
        `credit_number` varchar(255),
        `expiration` datetime(6),
        `name` varchar(255),
        `security_code` varchar(255),
        `surname` varchar(255),
        `type` varchar(255),
        `administrator_id` integer,
        `sponsor_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `companyrecord` (
       `id` integer not null,
        `version` integer not null,
        `ceo` varchar(255),
        `description` varchar(1024),
        `email` varchar(255),
        `incorporated` bit,
        `name` varchar(255),
        `number_stars` integer,
        `phone` varchar(255),
        `sector` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `configuration` (
       `id` integer not null,
        `version` integer not null,
        `spam_threshold` double precision,
        `spam_words` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `descriptor` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(1024),
        `job_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `duty` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(1024),
        `percentage` double precision,
        `title` varchar(255),
        `descriptor_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `employer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investorsrecords` (
       `id` integer not null,
        `version` integer not null,
        `name` varchar(255),
        `number_stars` integer,
        `sector` varchar(255),
        `statement` varchar(1024),
        primary key (`id`)
    ) engine=InnoDB;

    create table `job` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `final_mode` bit not null,
        `has_application` bit not null,
        `has_challenge` bit not null,
        `link_info` varchar(255),
        `more_info` varchar(255),
        `reference` varchar(255),
        `salary_amount` double precision,
        `salary_currency` varchar(255),
        `text_challenge` varchar(1024),
        `title` varchar(255),
        `employer_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(1024),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `message_thread_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `messagethread` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `title` varchar(255),
        `usernames` varchar(255),
        `creator_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `noncomercialbanner` (
       `id` integer not null,
        `version` integer not null,
        `final_mode` bit not null,
        `slogan` varchar(255),
        `url_picture` varchar(255),
        `url_target` varchar(255),
        `jingle` varchar(255),
        `administrator_id` integer,
        `sponsor_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `offers` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(1024),
        `lower_range_amount` double precision,
        `lower_range_currency` varchar(255),
        `major_range_amount` double precision,
        `major_range_currency` varchar(255),
        `moment` datetime(6),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `participatein` (
       `id` integer not null,
        `version` integer not null,
        `auditor_id` integer,
        `job_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `participates` (
       `id` integer not null,
        `version` integer not null,
        `authenticated_id` integer,
        `messagethread_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `request` (
       `id` integer not null,
        `version` integer not null,
        `dead_line` datetime(6),
        `description` varchar(1024),
        `moment` datetime(6),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `sponsor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `org_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `worker` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualification_record` varchar(1024),
        `skill_record` varchar(1024),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );

    alter table `application` 
       add constraint UK_ct7r18vvxl5g4c4k7aefpa4do unique (`reference`);

    alter table `job` 
       add constraint UK_7jmfdvs0b0jx7i33qxgv22h7b unique (`reference`);

    alter table `offers` 
       add constraint UK_7680whff1koitvyrrekdt6h8l unique (`ticker`);

    alter table `request` 
       add constraint UK_9mxq3powq8tqctclj0fbi2nih unique (`ticker`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKoa6p4s2oyy7tf80xwc4r04vh6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `application` 
       add constraint `FKmbjdoxi3o93agxosoate4sxbt` 
       foreign key (`worker_id`) 
       references `worker` (`id`);

    alter table `auditor` 
       add constraint FK_clqcq9lyspxdxcp6o4f3vkelj 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `auditorrequest` 
       add constraint `FK48mm6hlb69dlcjnf43eiov74s` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `auditrecord` 
       add constraint `FKditgyx355sc4ye86w7tj22cq6` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `auditrecord` 
       add constraint `FKa5p4w0gnuwmtb07juvrg8ptn6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `comercialbanner` 
       add constraint `FKcvbsmt5226xsmf6kxc5p8leal` 
       foreign key (`administrator_id`) 
       references `administrator` (`id`);

    alter table `comercialbanner` 
       add constraint `FKii9iupedxt6hx534i7mm6wjhv` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `descriptor` 
       add constraint `FKgfulfilmwi4hhaquiu7fr5g0g` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `duty` 
       add constraint `FK3cc3garl37bl7gswreqwr7pj4` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `employer` 
       add constraint FK_na4dfobmeuxkwf6p75abmb2tr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `job` 
       add constraint `FK3rxjf8uh6fh2u990pe8i2at0e` 
       foreign key (`employer_id`) 
       references `employer` (`id`);

    alter table `message` 
       add constraint `FKhlmmbswdtxwq1f6w6gmj14oci` 
       foreign key (`message_thread_id`) 
       references `messagethread` (`id`);

    alter table `messagethread` 
       add constraint `FKjrdkemfq5su0eieym0n8bdtgy` 
       foreign key (`creator_id`) 
       references `authenticated` (`id`);

    alter table `noncomercialbanner` 
       add constraint `FKafyjtxoa8c41616xvnuaphdgp` 
       foreign key (`administrator_id`) 
       references `administrator` (`id`);

    alter table `noncomercialbanner` 
       add constraint `FKiqlwh7t99w47gee8as9xvk5xt` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `participatein` 
       add constraint `FKe840v4bo3khcf20ud5hi49eqn` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `participatein` 
       add constraint `FKasttc8nkvw2of9y4x2mpt16os` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `participates` 
       add constraint `FK2v2b6kxya4od7kymllfa9iv0v` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `participates` 
       add constraint `FKsyju38rbst3bgj3okjyo7ovly` 
       foreign key (`messagethread_id`) 
       references `messagethread` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `sponsor` 
       add constraint FK_20xk0ev32hlg96kqynl6laie2 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `worker` 
       add constraint FK_l5q1f33vs2drypmbdhpdgwfv3 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);


    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKoa6p4s2oyy7tf80xwc4r04vh6`;

    alter table `application` 
       drop 
       foreign key `FKmbjdoxi3o93agxosoate4sxbt`;

    alter table `auditor` 
       drop 
       foreign key FK_clqcq9lyspxdxcp6o4f3vkelj;

    alter table `auditorrequest` 
       drop 
       foreign key `FK48mm6hlb69dlcjnf43eiov74s`;

    alter table `auditrecord` 
       drop 
       foreign key `FKditgyx355sc4ye86w7tj22cq6`;

    alter table `auditrecord` 
       drop 
       foreign key `FKa5p4w0gnuwmtb07juvrg8ptn6`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `comercialbanner` 
       drop 
       foreign key `FKcvbsmt5226xsmf6kxc5p8leal`;

    alter table `comercialbanner` 
       drop 
       foreign key `FKii9iupedxt6hx534i7mm6wjhv`;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `descriptor` 
       drop 
       foreign key `FKgfulfilmwi4hhaquiu7fr5g0g`;

    alter table `duty` 
       drop 
       foreign key `FK3cc3garl37bl7gswreqwr7pj4`;

    alter table `employer` 
       drop 
       foreign key FK_na4dfobmeuxkwf6p75abmb2tr;

    alter table `job` 
       drop 
       foreign key `FK3rxjf8uh6fh2u990pe8i2at0e`;

    alter table `message` 
       drop 
       foreign key `FKhlmmbswdtxwq1f6w6gmj14oci`;

    alter table `messagethread` 
       drop 
       foreign key `FKjrdkemfq5su0eieym0n8bdtgy`;

    alter table `noncomercialbanner` 
       drop 
       foreign key `FKafyjtxoa8c41616xvnuaphdgp`;

    alter table `noncomercialbanner` 
       drop 
       foreign key `FKiqlwh7t99w47gee8as9xvk5xt`;

    alter table `participatein` 
       drop 
       foreign key `FKe840v4bo3khcf20ud5hi49eqn`;

    alter table `participatein` 
       drop 
       foreign key `FKasttc8nkvw2of9y4x2mpt16os`;

    alter table `participates` 
       drop 
       foreign key `FK2v2b6kxya4od7kymllfa9iv0v`;

    alter table `participates` 
       drop 
       foreign key `FKsyju38rbst3bgj3okjyo7ovly`;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `sponsor` 
       drop 
       foreign key FK_20xk0ev32hlg96kqynl6laie2;

    alter table `worker` 
       drop 
       foreign key FK_l5q1f33vs2drypmbdhpdgwfv3;

    drop table if exists `administrator`;

    drop table if exists `announcement`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `auditor`;

    drop table if exists `auditorrequest`;

    drop table if exists `auditrecord`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `challenge`;

    drop table if exists `comercialbanner`;

    drop table if exists `companyrecord`;

    drop table if exists `configuration`;

    drop table if exists `consumer`;

    drop table if exists `descriptor`;

    drop table if exists `duty`;

    drop table if exists `employer`;

    drop table if exists `investorsrecords`;

    drop table if exists `job`;

    drop table if exists `message`;

    drop table if exists `messagethread`;

    drop table if exists `noncomercialbanner`;

    drop table if exists `offers`;

    drop table if exists `participatein`;

    drop table if exists `participates`;

    drop table if exists `provider`;

    drop table if exists `request`;

    drop table if exists `sponsor`;

    drop table if exists `user_account`;

    drop table if exists `worker`;

    drop table if exists `hibernate_sequence`;

create table food_facility
(
    id                        int unsigned not null
        primary key auto_increment,
    location_id               int          not null comment 'location ID',
    applicant                 varchar(256) not null comment 'applicant',
    facility_type             varchar(64)  null comment 'FacilityType',
    cnn                       varchar(200) null comment 'cnn',
    location_description      varchar(512) null comment 'LocationDescription',
    address                   varchar(512) null comment 'Address',
    block_lot                 varchar(256) null comment 'blocklot',
    block                     varchar(128) null comment 'block',
    lot                       varchar(128) null comment 'lot',
    permit                    varchar(256) null comment 'permit',
    status                    tinyint      null comment 'status: -1-EXPIRED, 1-APPROVED, 2-ISSUED, 3-REQUESTED, 4-SUSPEND',
    food_items                varchar(512) null comment 'FoodItems',
    location_x                int          null comment 'x',
    location_y                int          null comment 'y',
    latitude                  varchar(64)  null comment 'latitude',
    longitude                 varchar(64)  null comment 'longitude',
    schedule                  varchar(512) null comment 'schedule',
    dayshours                 varchar(128) null comment 'dayshours',
    noi_sent                  varchar(512) null comment 'NOISent',
    approved_date             varchar(128) null comment 'Approved',
    received                  varchar(64)  null comment 'Received',
    expiration_date           varchar(128) null comment 'ExpirationDate',
    prior_permit              bit          null comment 'PriorPermit',
    location                  varchar(512) null comment 'Location',
    fire_prevention_districts int          null comment 'Fire Prevention Districts',
    police_districts          int          null comment 'Police Districts',
    supervisor_districts      int          null comment 'Supervisor Districts',
    zip_codes                 varchar(64)  null comment 'Zip Codes',
    neighborhoods             varchar(64)  null comment 'Neighborhoods(old)',
    create_time               datetime(3)  null,
    update_time               datetime(3)  null,
    create_by                 varchar(40)  null,
    update_by                 varchar(40)  null,
    key idx_food_facility_location (`location_id`)
)
    comment 'The table of mobile food facility and permit' charset = utf8mb4;

create table facility_foods
(
    id          int unsigned not null
        primary key auto_increment,
    location_id int          not null comment 'location ID',
    foods_name  varchar(256) not null comment 'FoodsName',
    create_time datetime(3)  null,
    update_time datetime(3)  null,
    create_by   varchar(40)  null,
    update_by   varchar(40)  null,
    key idx_facility_foods_location (`location_id`)
)
    comment 'The table of facility\'s foods ' charset = utf8mb4;

create table facility_time
(
    id          int unsigned not null
        primary key auto_increment,
    location_id int          not null comment 'location ID',
    week_day    varchar(256) null comment 'FoodsName',
    open_time   tinyint      null comment 'OpenTime',
    close_time  tinyint      null comment 'CloseTIme',
    create_time datetime(3)  null,
    update_time datetime(3)  null,
    create_by   varchar(40)  null,
    update_by   varchar(40)  null,
    key idx_facility_time_location (`location_id`),
    key idx_facility_time_week_day (`week_day`)
)
    comment 'The table of business hour' charset = utf8mb4;


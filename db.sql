create table registrationdb.orders
(
    id               int auto_increment
        primary key,
    region           varchar(45) null,
    type             varchar(45) null,
    name             varchar(45) null,
    numberOfPackages int         null,
    weightOfPacking  int         null,
    taste            varchar(45) null,
    delivery         varchar(45) null,
    orderCost        int         null,
    status           varchar(45) null
)
    charset = utf8mb3;

create table registrationdb.product
(
    id                  int auto_increment
        primary key,
    type                varchar(45) null,
    name                varchar(45) null,
    price               int         null,
    numberOfPackages    int         null,
    weightOfPacking     int         null,
    manufacturer        varchar(45) null,
    accountingDiscounts varchar(45) null
)
    charset = utf8mb3;

create table registrationdb.registration
(
    id       int auto_increment
        primary key,
    login    varchar(45) null,
    password varchar(45) null,
    email    varchar(45) null
)
    charset = utf8mb3;

insert into registrationdb.registration (id, login, password, email)
values  (1, 'admin', 'f6fdffe48c908deb0f4c3bd36c032e72', 'admin@mail.ru'),
#         pass:adminadmin
        (2, 'qwerty', '12478e7ad0e39aa9c35be4b9a694ba9b', 'qwerty@mail.ru'),
#         pass:qwertyqwerty
        (3, 'user', '5cc32e366c87c4cb49e4309b75f57d64', 'user@mail.ru');
#         pass:useruser

insert into registrationdb.product (id, type, name, price, numberOfPackages, weightOfPacking, manufacturer, accountingDiscounts)
values  (1, 'Протеины', 'Whey', 26, 22, 600, 'Америка', '0'),
        (2, 'Протеины', 'WheyPumpExtreme', 36, 25, 1200, 'Америка', '0'),
        (4, 'Протеины', 'Isolate100', 49, 15, 750, 'Россия', '0'),
        (5, 'Протеины', 'Cosein100', 34, 35, 600, 'Германия', '5'),
        (6, 'Протеины', 'Nitropogen', 42, 50, 750, 'Америка', '5'),
        (7, 'Протеины', 'BeefMuscle', 101, 25, 3180, 'Германия', '10'),
        (8, 'Протеины', 'ProteinDelite', 34, 100, 750, 'Россия', '10'),
        (9, 'Протеины', 'AnabolicWhey', 47, 40, 900, 'Америка', '5'),
        (10, 'Протеины', 'WeiderPremiumWhey', 30, 80, 500, 'Америка', '5'),
        (11, 'Креатины', 'CM3Powder', 18, 100, 250, 'Америка', '5'),
        (12, 'Креатины', 'Crea9Xtreme', 6, 40, 100, 'Италия', '5'),
        (13, 'Креатины', 'Krealkalyn', 19, 25, 300, 'Китай', '0'),
        (14, 'Креатины', 'Creatine', 30, 60, 400, 'Китай', '5'),
        (15, 'Креатины', 'ReactroPro', 21, 40, 150, 'Россия', '5'),
        (16, 'Протеины', 'CM31250', 10, 25, 200, 'Америка', '0'),
        (17, 'Креатины', 'Creatrix', 32, 30, 150, 'Китай', '0'),
        (18, 'Креатины', 'Crea9', 28, 100, 200, 'Америка', '10'),
        (19, 'Креатины', 'TCMPowder', 28, 35, 300, 'Германия', '0'),
        (20, 'Аминокислоты', 'AminoMuscle', 25, 90, 500, 'Греция', '10'),
        (21, 'Аминокислоты', 'Leucine', 59, 23, 600, 'Италия', '5'),
        (22, 'Аминокислоты', 'L-Glutamine', 45, 45, 600, 'Канада', '5'),
        (23, 'Аминокислоты', 'AminoVIP', 120, 10, 4000, 'Америка', '0'),
        (24, 'Креатины', 'CreatixVIP', 39, 20, 400, 'Россия', '0'),
        (25, 'Аминокислоты', 'UltraAmino', 41, 30, 500, 'Канада', '5'),
        (26, 'BCAA', 'BcaaG-Force', 5, 100, 50, 'Россия', '0'),
        (27, 'BCAA', 'BcaaTurbo', 73, 30, 400, 'Канада', '5'),
        (28, 'BCAA', 'BcaaPowder', 80, 40, 500, 'Америка', '5'),
        (29, 'BCAA', 'MEGABcaa', 120, 30, 700, 'Италия', '10'),
        (30, 'BCAA', 'BcaaX', 78, 40, 400, 'Китай', '0'),
        (31, 'Жиросжигатели', 'CLA', 42, 30, 400, 'Япония', '0'),
        (32, 'Жиросжигатели', 'L-Carnitine', 45, 10, 600, 'Россия', '0'),
        (33, 'Жиросжигатели', 'FatKiller', 53, 30, 400, 'Канада', '5'),
        (34, 'Жиросжигатели', 'FatTransporter', 70, 35, 500, 'Италия', '5'),
        (35, 'Жиросжигатели', 'TurboRipper', 27, 100, 400, 'Китай', '10'),
        (36, 'Гейнеры', 'HardMass', 120, 35, 3000, 'Америка', '5'),
        (37, 'Гейнеры', 'MassXXL', 59, 45, 1200, 'Япония', '5'),
        (38, 'Гейнеры', 'Magnum8000', 89, 30, 5000, 'Япония', '5'),
        (39, 'Гейнеры', 'Mass', 88, 35, 3000, 'Канада', '0'),
        (40, 'Гейнеры', 'WeiderMegaMass', 56, 20, 2000, 'Россия', '5'),
        (41, 'Энергетики', 'Redfaster', 10, 120, 150, 'Америка', '10'),
        (42, 'Энергетики', 'Dextrose', 18, 35, 300, 'Норвегия', '0'),
        (43, 'Энергетики', 'MaxCarb', 49, 50, 350, 'Норвегия', '5'),
        (44, 'Энергетики', 'Energy', 12, 37, 200, 'Россия', '0'),
        (45, 'Энергетики', 'GelExtreme', 30, 120, 300, 'Япония', '10'),
        (46, 'ВитаминныеКомплексы', 'Spirulina', 8, 40, 100, 'Швеция', '5'),
        (47, 'ВитаминныеКомплексы', 'MultiPack', 34, 120, 300, 'Япония', '10'),
        (48, 'ВитаминныеКомплексы', 'Mange-100', 13, 35, 200, 'Америка', '0'),
        (49, 'ВитаминныеКомплексы', 'VitalStack', 67, 80, 1200, 'Россия', '5'),
        (50, 'ВитаминныеКомплексы', 'OptiFarm', 34, 35, 400, 'Россия', '5'),
        (53, 'Протеины', 'БизонPro', 34, 50, 1200, 'Беларусь', '5');

insert into registrationdb.orders (id, region, type, name, numberOfPackages, weightOfPacking, taste, delivery, orderCost, status)
values  (1, 'Минск', 'Протеины', 'Продан', 3, 1500, 'Клубника', 'БезДоставки', 154, 'Обработан'),
        (2, 'Минск', 'Креатины', 'Продан', 4, 1200, 'Шоколад', 'БезДоставки', 231, 'Обработан'),
        (3, 'Минск', 'Протеины', 'Продан', 5, 1200, 'Клубника', 'БезДоставки', 257, 'Обработан'),
        (4, 'Гродно', 'Аминокислоты', 'Продан', 2, 3000, 'Ваниль', 'ОбычнаяДоставка', 664, 'Обработан'),
        (7, 'Минск', 'Энергетики', 'Продан', 6, 700, 'Клубника', 'ОбычнаяДоставка', 69, 'Обработан'),
        (8, 'Минск', 'Гейнеры', 'HardMass', 3, 1200, 'Шоколад', 'ОбычнаяДоставка', 823, 'В_обработке'),
        (9, 'Брест', 'Энергетики', 'Продан', 2, 1000, 'Клубника', 'БезДоставки', 54, 'Обработан'),
        (13, 'Минск', 'Протеины', 'Продан', 3, 1200, 'Клубника', 'БезДоставки', 98, 'Обработан'),
        (14, 'Минск', 'Протеины', 'Продан', 5, 1400, 'Клубника', 'ОбычнаяДоставка', 180, 'Обработан'),
        (15, 'Минск', 'Протеины', 'khkkkb', 7, 1200, 'Клубника', 'БезДоставки', null, 'В_обработке'),
        (16, 'Минск', 'Протеины', 'WheyResult', 100, 9, 'Клубника', 'БезДоставки', null, 'В_обработке');
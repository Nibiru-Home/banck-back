-- Client
INSERT INTO clients (id, login, password, first_name, last_name, second_last_name, dni) 
VALUES ('55555555-5555-5555-5555-555555555555', 'Marta', 'marta123', 'Marta', 'Martinez', 'Garcia', '12345678A');
INSERT INTO clients (id, login, password, first_name, last_name, second_last_name, dni) 
VALUES ('66666666-6666-6666-6666-666666666666', 'Alfonso', 'alfonso123', 'Alfonso', 'Hernandez', 'Lopez', '91011121R');
INSERT INTO clients (id, login, password, first_name, last_name, second_last_name, dni) 
VALUES ('77777777-7777-7777-7777-777777777777', 'Ignacio', 'ignacio123', 'Ignacio', 'Diaz', 'Gonzalez', '31415161I');
INSERT INTO clients (id, login, password, first_name, last_name, second_last_name, dni) 
VALUES ('88888888-8888-8888-8888-888888888888', 'Sonia', 'sonia123', 'Sonia', 'Rodriguez', 'Gomez', '71819202G');
INSERT INTO clients (id, login, password, first_name, last_name, second_last_name, dni) 
VALUES ('99999999-9999-9999-9999-999999999999', 'Juan', 'juan123', 'Juan', 'Perez', 'Sanchez', '12223242Z');

-- Bank Account
INSERT INTO bank_accounts (id, iban, balance, client_id) 
VALUES (1, 'ES91 2100 0418 4502 0005 1332', 1612.00, '55555555-5555-5555-5555-555555555555');
INSERT INTO bank_accounts (id, iban, balance, client_id) 
VALUES (2, 'ES12 1465 0001 3320 1234 5678', 235.00, '66666666-6666-6666-6666-666666666666');
INSERT INTO bank_accounts (id, iban, balance, client_id) 
VALUES (3, 'ES45 0049 1500 1234 5678 9012', 691.00, '77777777-7777-7777-7777-777777777777');
INSERT INTO bank_accounts (id, iban, balance, client_id) 
VALUES (4, 'ES78 2038 1234 5600 9876 5432', 2367.00, '88888888-8888-8888-8888-888888888888');
INSERT INTO bank_accounts (id, iban, balance, client_id) 
VALUES (5, 'ES33 0081 5220 0001 2345 6789', 12765.00, '99999999-9999-9999-9999-999999999999');

-- Credit Card
INSERT INTO credit_cards (id, number, expiration_date, cvv, name, bank_account_id) 
VALUES (1, '4111 1111 1111 1111', '2030-12-31', 321, 'Marta Martinez', 1);
INSERT INTO credit_cards (id, number, expiration_date, cvv, name, bank_account_id) 
VALUES (2, '5555 5555 5555 4444', '2031-11-30', 654, 'Alfonso Hernandez', 2);
INSERT INTO credit_cards (id, number, expiration_date, cvv, name, bank_account_id) 
VALUES (3, '4000 0000 0000 0002', '2032-10-29', 987, 'Ignacio Diaz', 3);
INSERT INTO credit_cards (id, number, expiration_date, cvv, name, bank_account_id) 
VALUES (4, '5105 1051 0510 5100', '2033-09-28', 159, 'Sonia Rodriguez', 4);
INSERT INTO credit_cards (id, number, expiration_date, cvv, name, bank_account_id) 
VALUES (5, '4242 4242 4242 4242', '2034-08-27', 753, 'Juan Perez', 5);

-- Bank Movements
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (1, 'Add', 'Transfer', '2024-11-18 07:42:15', 10.50, 'Abono nómina empresa', 1, 3);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (2, 'Remove', 'Bank_card', '2025-03-06 21:09:33', 75.00, 'Pago con tarjeta comercio', 1, 3);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (3, 'Add', 'Transfer', '2025-10-21 14:38:52', 1500.00, 'Transferencia recibida (contrapartida movimiento 10)', 1, 2);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (4, 'Add', 'Direct_debit', '2023-12-09 03:18:47', 120.50, 'Devolución recibo', 2, 1);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (5, 'Remove', 'Transfer', '2024-02-22 11:04:58', 85.00, 'Retirada de efectivo en cajero', 2, 1);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (6, 'Remove', 'Transfer', '2024-11-18 07:42:15', 10.50, 'Transferencia emitida (contrapartida movimiento 1)', 2, 3);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (7, 'Add', 'Bank_card', '2025-07-30 06:37:44', 310.50, 'Abono pago cliente', 3, 4);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (8, 'Remove', 'Direct_debit', '2023-09-16 15:12:05', 245.00, 'Pago suscripción mensual', 3, 4);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (9, 'Add', 'Transfer', '2024-02-22 11:04:58', 85.00, 'Transferencia recibida (contrapartida movimiento 4)', 3, 1);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (10, 'Remove', 'Transfer', '2024-06-12 08:45:19', 430.00, 'Transferencia emitida (contrapartida movimiento 7)', 3, 5);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (11, 'Add', 'Transfer', '2024-06-12 08:45:19', 430.00, 'Ingreso efectivo en oficina', 4, 5);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (12, 'Remove', 'Bank_card', '2025-09-03 17:22:41', 62.05, 'Cargo recibo domiciliado', 4, 5);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (13, 'Add', 'Direct_debit', '2023-11-27 04:59:08', 89.50, 'Ingreso por transferencia recibida', 5, 2);
INSERT INTO bank_movements (id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id) 
VALUES (14, 'Remove', 'Transfer', '2025-10-21 14:38:52', 1500.00, 'Transferencia emitida', 5, 2);

-- Sessions
INSERT INTO sesions (id, token_value, client_id, created_at)
VALUES ('00000000-0000-0000-0000-000000000000', 'token1', '55555555-5555-5555-5555-555555555555', '2025-02-02 10:00:00');
INSERT INTO sesions (id, token_value, client_id, created_at)
VALUES ('11111111-1111-1111-1111-111111111111', 'token2', '66666666-6666-6666-6666-666666666666', '2025-01-30 10:00:00');
INSERT INTO sesions (id, token_value, client_id, created_at)
VALUES ('22222222-2222-2222-2222-222222222222', 'token3', '77777777-7777-7777-7777-777777777777', '2025-02-24 10:00:00');
INSERT INTO sesions (id, token_value, client_id, created_at)
VALUES ('33333333-3333-3333-3333-333333333333', 'token4', '88888888-8888-8888-8888-888888888888', '2025-01-13 10:00:00');
INSERT INTO sesions (id, token_value, client_id, created_at)
VALUES ('44444444-4444-4444-4444-444444444444', 'token5', '99999999-9999-9999-9999-999999999999', '2025-01-05 10:00:00');

-- NUEVAS CUENTAS (2 por cliente) -> IDs 6..15
INSERT INTO bank_accounts (id, iban, balance, client_id) VALUES
-- Marta (client 555...)
(6,  'ES10 2100 0418 4502 0005 1401',  540.25, '55555555-5555-5555-5555-555555555555'),
(7,  'ES11 2100 0418 4502 0005 1402', 2450.00, '55555555-5555-5555-5555-555555555555'),

-- Alfonso (client 666...)
(8,  'ES20 1465 0001 3320 1234 5701',  120.00, '66666666-6666-6666-6666-666666666666'),
(9,  'ES21 1465 0001 3320 1234 5702',  980.75, '66666666-6666-6666-6666-666666666666'),

-- Ignacio (client 777...)
(10, 'ES30 0049 1500 1234 5678 9101',   75.90, '77777777-7777-7777-7777-777777777777'),
(11, 'ES31 0049 1500 1234 5678 9102', 3500.00, '77777777-7777-7777-7777-777777777777'),

-- Sonia (client 888...)
(12, 'ES40 2038 1234 5600 9876 5501',  640.10, '88888888-8888-8888-8888-888888888888'),
(13, 'ES41 2038 1234 5600 9876 5502',   30.00, '88888888-8888-8888-8888-888888888888'),

-- Juan (client 999...)
(14, 'ES50 0081 5220 0001 2345 6801', 12500.00, '99999999-9999-9999-9999-999999999999'),
(15, 'ES51 0081 5220 0001 2345 6802',  210.45, '99999999-9999-9999-9999-999999999999');


-- NUEVAS TARJETAS (2 por cliente) -> IDs 6..15, conectadas a las cuentas nuevas
INSERT INTO credit_cards (id, number, expiration_date, cvv, name, bank_account_id) VALUES
-- Marta -> cuentas 6 y 7
(6,  '4000 0000 0000 0101', '2031-06-30', 112, 'Marta Martinez',    6),
(7,  '5105 1051 0510 0202', '2032-07-31', 223, 'Marta Martinez',    7),

-- Alfonso -> cuentas 8 y 9
(8,  '4000 0000 0000 0303', '2031-05-31', 334, 'Alfonso Hernandez', 8),
(9,  '5555 5555 5555 0606', '2032-04-30', 445, 'Alfonso Hernandez', 9),

-- Ignacio -> cuentas 10 y 11
(10, '4242 4242 4242 1010', '2033-03-31', 556, 'Ignacio Diaz',      10),
(11, '4111 1111 1111 1212', '2034-02-28', 667, 'Ignacio Diaz',      11),

-- Sonia -> cuentas 12 y 13
(12, '4000 0000 0000 1414', '2031-08-31', 778, 'Sonia Rodriguez',   12),
(13, '5105 1051 0510 1616', '2032-09-30', 889, 'Sonia Rodriguez',   13),

-- Juan -> cuentas 14 y 15
(14, '5555 5555 5555 1818', '2033-10-31', 191, 'Juan Perez',        14),
(15, '4242 4242 4242 2020', '2034-11-30', 292, 'Juan Perez',        15);


-- NUEVOS MOVIMIENTOS -> IDs 15..44 (Add/Remove)
INSERT INTO bank_movements
(id, movement_type, movement_origin, timestamp, amount, concept, bank_account_id, origin_credit_card_id)
VALUES
-- Marta - cuenta 6 (tarjeta 6)
(15, 'Add',    'Transfer',     '2026-01-12 09:15:10',  600.00, 'Ingreso inicial apertura cuenta', 6,  6),
(16, 'Remove', 'Bank_card',    '2026-01-10 18:22:03',   43.20, 'Compra supermercado',             6,  6),
(17, 'Remove', 'Direct_debit', '2025-02-01 06:05:00',   29.99, 'Suscripción streaming',           6,  6),
(18, 'Add',    'Transfer',     '2025-02-28 08:10:00', 1200.00, 'Abono nómina',                    6,  6),

-- Marta - cuenta 7 (tarjeta 7)
(19, 'Add',    'Transfer',     '2025-03-10 12:00:00', 2500.00, 'Traspaso a cuenta ahorro',        7,  7),
(20, 'Remove', 'Transfer',     '2025-03-12 11:30:15',  300.00, 'Transferencia emitida a familiar',7,  7),
(21, 'Remove', 'Bank_card',    '2025-04-02 20:41:55',   87.65, 'Compra electrónica',              7,  7),

-- Alfonso - cuenta 8 (tarjeta 8)
(22, 'Add',    'Transfer',     '2025-02-05 10:05:10',  150.00, 'Ingreso efectivo',                8,  8),
(23, 'Remove', 'Bank_card',    '2025-02-07 14:11:02',   22.10, 'Restaurante',                     8,  8),
(24, 'Remove', 'Direct_debit', '2025-03-01 07:00:00',   45.00, 'Recibo telefonía',                8,  8),

-- Alfonso - cuenta 9 (tarjeta 9)
(25, 'Add',    'Transfer',     '2025-03-15 09:00:00', 1000.00, 'Abono nómina',                    9,  9),
(26, 'Remove', 'Transfer',     '2025-03-16 09:10:00',  200.00, 'Transferencia a ahorro',          9,  9),
(27, 'Remove', 'Bank_card',    '2025-03-18 19:44:30',   61.40, 'Gasolinera',                      9,  9),

-- Ignacio - cuenta 10 (tarjeta 10)
(28, 'Add',    'Transfer',     '2024-12-20 16:30:00',  300.00, 'Ingreso regalo',                  10, 10),
(29, 'Remove', 'Bank_card',    '2024-12-21 12:05:12',   19.90, 'Compra librería',                 10, 10),
(30, 'Remove', 'Transfer',     '2025-01-03 09:45:00',   50.00, 'Retirada cajero',                 10, 10),

-- Ignacio - cuenta 11 (tarjeta 11)
(31, 'Add',    'Transfer',     '2025-05-01 08:00:00', 3500.00, 'Ingreso por bonus',               11, 11),
(32, 'Remove', 'Direct_debit', '2025-05-02 06:50:00',  650.00, 'Alquiler vivienda',               11, 11),
(33, 'Remove', 'Bank_card',    '2025-05-10 21:12:09',  120.35, 'Compra online',                   11, 11),

-- Sonia - cuenta 12 (tarjeta 12)
(34, 'Add',    'Transfer',     '2025-06-05 11:20:00',  700.00, 'Ingreso por ventas',              12, 12),
(35, 'Remove', 'Bank_card',    '2025-06-06 13:33:21',   35.80, 'Farmacia',                        12, 12),
(36, 'Remove', 'Direct_debit', '2025-06-10 07:00:00',   55.00, 'Recibo internet',                 12, 12),

-- Sonia - cuenta 13 (tarjeta 13)
(37, 'Add',    'Transfer',     '2025-07-01 09:10:00',  100.00, 'Ingreso puntual',                 13, 13),
(38, 'Remove', 'Bank_card',    '2025-07-02 10:00:00',   12.50, 'Cafetería',                       13, 13),
(39, 'Remove', 'Transfer',     '2025-07-03 17:25:00',   20.00, 'Bizum/transferencia pequeña',     13, 13),

-- Juan - cuenta 14 (tarjeta 14)
(40, 'Add',    'Transfer',     '2025-08-20 08:30:00', 5000.00, 'Ingreso venta vehículo',          14, 14),
(41, 'Remove', 'Transfer',     '2025-08-21 09:00:00', 1500.00, 'Transferencia inversión',         14, 14),
(42, 'Remove', 'Bank_card',    '2025-08-22 16:45:10',  210.00, 'Compra hotel',                    14, 14),

-- Juan - cuenta 15 (tarjeta 15)
(43, 'Add',    'Transfer',     '2025-09-01 08:00:00',  250.00, 'Ingreso nómina parcial',          15, 15),
(44, 'Remove', 'Direct_debit', '2025-09-02 06:55:00',   90.00, 'Recibo luz',                      15, 15);

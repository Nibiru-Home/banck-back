-- Client
INSERT INTO clients (id, login, password, first_name, last_name, second_last_name, dni) 
VALUES ('55555555-5555-5555-5555-555555555555', 'Marta', 'pmarta123', 'Marta', 'Martinez', 'Garcia', '12345678A');
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


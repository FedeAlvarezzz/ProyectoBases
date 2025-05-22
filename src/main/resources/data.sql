

-- Tabla de Pacientes
CREATE TABLE IF NOT EXISTS paciente
(
    cedula           INT PRIMARY KEY,
    nombre           VARCHAR(100)  NOT NULL,
    apellido         VARCHAR(100)  NOT NULL,
    fecha_nacimiento DATE          NOT NULL,
    altura           DECIMAL(3, 2) NOT NULL CHECK (altura BETWEEN 0.5 AND 2.5),
    peso             DECIMAL(5, 2) NOT NULL CHECK (peso BETWEEN 1.0 AND 300.0),
    email            VARCHAR(100)  NOT NULL UNIQUE,
    telefono         VARCHAR(15)   NOT NULL,
    direccion        VARCHAR(200)  NOT NULL,
    grupo_sanguineo  VARCHAR(10)   NOT NULL CHECK (grupo_sanguineo REGEXP '^(A|B|AB|O)[+-]$')
) ,
    contrasena       VARCHAR(20) NOT NULL,
    estado           ENUM('ACTIVO', 'INACTIVO', 'ELIMINADO') NOT NULL
);

-- Tabla de Medicos
CREATE TABLE IF NOT EXISTS medico
(
    id       INT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    apellido     VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    telefono     VARCHAR(15)  NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    password     VARCHAR(100) NOT NULL,
    estado       ENUM('ACTIVO', 'INACTIVO') NOT NULL
);

-- Tabla de Historia Clinica
CREATE TABLE IF NOT EXISTS historia_clinica
(
    id_historia_clinica INT AUTO_INCREMENT PRIMARY KEY,
    descripcion         TEXT NOT NULL,
    fecha_creacion      DATE NOT NULL,
    paciente_id         INT  NOT NULL UNIQUE,
    FOREIGN KEY (paciente_id) REFERENCES paciente (cedula)
);

-- Tabla de Enfermedades
CREATE TABLE IF NOT EXISTS enfermedad
(
    id_enfermedad INT AUTO_INCREMENT PRIMARY KEY,
    descripcion   VARCHAR(255),
    id_historia   INT,
    FOREIGN KEY (id_historia) REFERENCES historia_clinica (id_historia_clinica)
);

-- Tabla de Alergias
CREATE TABLE IF NOT EXISTS alergia
(
    id_alergia  INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255),
    id_historia INT,
    FOREIGN KEY (id_historia) REFERENCES historia_clinica (id_historia_clinica)
);

-- Tabla de Consultas
CREATE TABLE IF NOT EXISTS consulta
(
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    id_medico   INT            NOT NULL,
    id_paciente INT            NOT NULL,
    fecha       DATE           NOT NULL,
    hora        TIME           NOT NULL,
    valor       DECIMAL(10, 2) NOT NULL CHECK (valor >= 0.0),
    motivo      VARCHAR(500)   NOT NULL,
    diagnostico VARCHAR(1000),
    FOREIGN KEY (id_medico) REFERENCES medico (cedula),
    FOREIGN KEY (id_paciente) REFERENCES paciente (cedula)
);


-- Insertar pacientes (10)
INSERT INTO paciente (id, nombre, apellido, fecha_nacimiento, altura, peso, correo, celular, direccion, tipo_sangre,
                      contrasena, estado)
VALUES (101, 'Ana', 'Ramírez', '1995-05-20', 1.65, 60.5, 'ana.ramirez@example.com', '+573001112233', 'Calle 1 #23-45',
        'A+', 'anapass123', 'ACTIVO'),
       (102, 'Carlos', 'González', '1988-03-12', 1.75, 75.0, 'carlos.g@example.com', '+573001112234',
        'Carrera 5 #10-20', 'O-', 'carlospass', 'ACTIVO'),
       (103, 'Lucía', 'Martínez', '1990-11-03', 1.60, 55.0, 'lucia.martinez@example.com', '+573001112235',
        'Av 10 #20-30', 'B+', 'luciapass', 'ACTIVO'),
       (104, 'Jorge', 'López', '1982-07-19', 1.80, 85.0, 'jorge.lopez@example.com', '+573001112236', 'Calle 20 #15-50',
        'AB+', 'jorgepass', 'ACTIVO'),
       (105, 'María', 'Fernández', '1997-01-10', 1.55, 52.0, 'maria.fernandez@example.com', '+573001112237',
        'Cra 7 #40-60', 'O+', 'mariapass', 'ACTIVO'),
       (106, 'David', 'Sánchez', '1985-09-15', 1.70, 68.0, 'david.sanchez@example.com', '+573001112238',
        'Calle 12 #50-40', 'A-', 'davidpass', 'ACTIVO'),
       (107, 'Sofía', 'Gómez', '1993-04-21', 1.62, 58.0, 'sofia.gomez@example.com', '+573001112239', 'Av 5 #30-25',
        'B-', 'sofiapass', 'ACTIVO'),
       (108, 'Pedro', 'Díaz', '1989-12-30', 1.77, 72.0, 'pedro.diaz@example.com', '+573001112240', 'Calle 3 #60-10',
        'AB-', 'pedropass', 'ACTIVO'),
       (109, 'Elena', 'Castro', '1994-08-07', 1.58, 54.0, 'elena.castro@example.com', '+573001112241', 'Cra 9 #10-15',
        'O-', 'elenapass', 'ACTIVO'),
       (110, 'Luis', 'Vargas', '1987-06-25', 1.82, 90.0, 'luis.vargas@example.com', '+573001112242', 'Av 20 #70-5',
        'A+', 'luispass', 'ACTIVO');

-- Insertar médicos (10)
INSERT INTO medico (id, nombre, apellido, correo, celular, especialidad, contrasena, estado)
VALUES (201, 'Laura', 'Suárez', 'laura.suarez@hospital.com', '+573001112300', 'Pediatría', 'laura1234', 'ACTIVO'),
       (202, 'Miguel', 'Torres', 'miguel.t@hospital.com', '+573001112301', 'Medicina General', 'miguelpass', 'ACTIVO'),
       (203, 'Isabel', 'Ramírez', 'isabel.ramirez@hospital.com', '+573001112302', 'Cardiología', 'isabelpass',
        'ACTIVO'),
       (204, 'Fernando', 'Pérez', 'fernando.perez@hospital.com', '+573001112303', 'Dermatología', 'fernandopass',
        'ACTIVO'),
       (205, 'Ana', 'Lozano', 'ana.lozano@hospital.com', '+573001112304', 'Neurología', 'analozano', 'ACTIVO'),
       (206, 'Javier', 'Mendoza', 'javier.mendoza@hospital.com', '+573001112305', 'Ortopedia', 'javipass', 'ACTIVO'),
       (207, 'Marta', 'López', 'marta.lopez@hospital.com', '+573001112306', 'Gastroenterología', 'martapass', 'ACTIVO'),
       (208, 'David', 'Ramírez', 'david.ramirez@hospital.com', '+573001112307', 'Oftalmología', 'davidpass', 'ACTIVO'),
       (209, 'Carolina', 'Vega', 'carolina.vega@hospital.com', '+573001112308', 'Endocrinología', 'caropass', 'ACTIVO'),
       (210, 'Ricardo', 'Salazar', 'ricardo.salazar@hospital.com', '+573001112309', 'Psiquiatría', 'ricardopass',
        'ACTIVO');

-- Insertar historias clínicas (10)
INSERT INTO historia_clinica (id, descripcion, fecha_creacion, paciente_id)
VALUES (1, 'Historia clínica de Ana', '2023-01-10', 101),
       (2, 'Historia clínica de Carlos', '2023-02-15', 102),
       (3, 'Historia clínica de Lucía', '2023-03-05', 103),
       (4, 'Historia clínica de Jorge', '2023-03-20', 104),
       (5, 'Historia clínica de María', '2023-04-01', 105),
       (6, 'Historia clínica de David', '2023-04-10', 106),
       (7, 'Historia clínica de Sofía', '2023-05-05', 107),
       (8, 'Historia clínica de Pedro', '2023-05-15', 108),
       (9, 'Historia clínica de Elena', '2023-06-01', 109),
       (10, 'Historia clínica de Luis', '2023-06-20', 110);

-- Insertar enfermedades (10)
INSERT INTO enfermedad (id, descripcion, id_historia)
VALUES (1, 'Hipertensión', 1),
       (2, 'Asma', 2),
       (3, 'Diabetes', 3),
       (4, 'Artritis', 4),
       (5, 'Colesterol alto', 5),
       (6, 'Migrañas', 6),
       (7, 'Bronquitis crónica', 7),
       (8, 'Gastritis', 8),
       (9, 'Depresión', 9),
       (10, 'Anemia', 10);

-- Insertar alergias (10)
INSERT INTO alergia (id, descripcion, id_historia)
VALUES (1, 'Alergia al polen', 1),
       (2, 'Alergia a mariscos', 2),
       (3, 'Alergia a penicilina', 3),
       (4, 'Alergia a látex', 4),
       (5, 'Alergia a polvo doméstico', 5),
       (6, 'Alergia a alimentos', 6),
       (7, 'Alergia a picaduras de insectos', 7),
       (8, 'Alergia al moho', 8),
       (9, 'Alergia al gluten', 9),
       (10, 'Alergia a gatos', 10);

-- Insertar consultas (10)
INSERT INTO consulta (id, id_medico, id_paciente, fecha, hora, valor, motivo, diagnostico)
VALUES (1, 201, 101, '2024-05-01', '10:00:00', 50000, 'Dolor de cabeza', 'Migraña leve'),
       (2, 202, 102, '2024-05-02', '11:00:00', 60000, 'Dolor abdominal', 'Gastritis'),
       (3, 203, 103, '2024-05-03', '09:30:00', 55000, 'Chequeo cardiaco', 'Normal'),
       (4, 204, 104, '2024-05-04', '14:00:00', 70000, 'Erupción cutánea', 'Dermatitis'),
       (5, 205, 105, '2024-05-05', '15:30:00', 65000, 'Dolor de cabeza recurrente', 'Migraña crónica'),
       (6, 206, 106, '2024-05-06', '08:45:00', 60000, 'Dolor articular', 'Artritis'),
       (7, 207, 107, '2024-05-07', '13:15:00', 50000, 'Problemas digestivos', 'Gastritis leve'),
       (8, 208, 108, '2024-05-08', '10:30:00', 72000, 'Chequeo ocular', 'Miopía'),
       (9, 209, 109, '2024-05-09', '16:00:00', 48000, 'Depresión', 'Requiere terapia'),
       (10, 210, 110, '2024-05-10', '12:00:00', 53000, 'Anemia', 'Tratamiento recomendado');


# poo_trabalho_jdbc

## Equipe

| Equipe   | Email    |
|----------|----------|
| Ana Paula de Souza  | aanappauladesouza@gmail.com |
| Thaynara Michels    | thaynaramichels@gmail.com   |

## Configurações

| Item           | Valor       |
|----------------|-----------  |
| Banco de Dados | PostgreSQL  |
| Schema         | imobiliaria |

## Diagrama de classe da UML

![Diagrama de classe](/diagrama_de_classe.png)

## Diagrama MER


![MER](/mer.png)

## Instruções SQL

Criação do schema e tabelas.
```SQL
-- Criação do schema 
CREATE SCHEMA imobiliaria;
SET search_path TO imobiliaria;

-- Tabela de imóveis
CREATE TABLE imovel (
    id_imovel SERIAL PRIMARY KEY,
    endereco VARCHAR(200) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    tipo VARCHAR(50) NOT NULL, 
    quartos INT,
    banheiros INT,
    area_m2 NUMERIC(10,2),
    valor_aluguel_base NUMERIC(12,2)
);

-- Tabela de clientes
CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf CHAR(11) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100)
);

-- Tabela de contratos
CREATE TABLE contrato (
    id_contrato SERIAL PRIMARY KEY,
    id_imovel INT NOT NULL REFERENCES imovel(id_imovel) ON DELETE CASCADE,
    id_cliente INT NOT NULL REFERENCES cliente(id_cliente) ON DELETE CASCADE,
    valor_aluguel NUMERIC(12,2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    status VARCHAR(20) DEFAULT 'Ativo' 
);

```

Inserção de registros.
```SQL
-- Inserindo alguns imóveis
INSERT INTO imovel (endereco, cidade, estado, tipo, quartos, banheiros, area_m2, valor_aluguel_base)
VALUES 
('Rua das Flores, 123', 'São Paulo', 'SP', 'Apartamento', 2, 1, 65.00, 2500.00),
('Av. Brasil, 500', 'Rio de Janeiro', 'RJ', 'Casa', 3, 2, 120.00, 3500.00),
('Rua Central, 45', 'Curitiba', 'PR', 'Sala Comercial', 0, 1, 40.00, 1800.00);

-- Inserindo alguns clientes
INSERT INTO cliente (nome, cpf, telefone, email)
VALUES
('Maria Silva', '12345678901', '(11)99999-1111', 'maria.silva@email.com'),
('João Pereira', '98765432100', '(21)98888-2222', 'joao.pereira@email.com'),
('Ana Costa', '45612378900', '(41)97777-3333', 'ana.costa@email.com');

-- Inserindo contratos
INSERT INTO contrato (id_imovel, id_cliente, valor_aluguel, data_inicio, data_fim, status)
VALUES
(1, 1, 2500.00, '2025-01-01', '2025-12-31', 'Ativo'),
(2, 2, 3500.00, '2025-02-15', '2026-02-14', 'Ativo'),
(3, 3, 1800.00, '2025-03-01', NULL, 'Ativo');

```

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE enderecos (
    id UUID PRIMARY KEY,
    rua VARCHAR(150) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    CONSTRAINT chk_uf_brasil CHECK (uf IN (
        'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO',
        'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI',
        'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'
            ))
);

-- Criar a tabela de Roles
CREATE TABLE roles (
    id UUID PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE usuarios (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(150) NOT NULL,
    cpf VARCHAR(255) UNIQUE NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    contato VARCHAR(15) NOT NULL,
    data_nascimento DATE NOT NULL,
    consentimento_termos BOOLEAN NOT NULL DEFAULT FALSE,
    endereco_id UUID UNIQUE NOT NULL,
    CONSTRAINT fk_usuarios_endereco FOREIGN KEY (endereco_id) REFERENCES enderecos(id) ON DELETE RESTRICT
);

CREATE TABLE usuarios_roles (
    usuarios_id UUID NOT NULL,
    roles_id UUID NOT NULL,
    PRIMARY KEY (usuarios_id, roles_id),
    CONSTRAINT fk_user_role_usuario FOREIGN KEY (usuarios_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_role_role FOREIGN KEY (roles_id) REFERENCES roles(id) ON DELETE CASCADE
);


CREATE TABLE tb_categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE CHECK ( nome IN ('CAMISETAS', 'CALCAS', 'CALCADOS') )
);

CREATE TABLE tb_produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco NUMERIC(10, 2) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    categoria_id BIGINT NOT NULL,
    CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES tb_categoria (id)
);

CREATE TABLE tb_item_estoque (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    tamanho VARCHAR(10) NOT NULL,
    cor VARCHAR(30) NOT NULL,
    quantidade INT NOT NULL CHECK (quantidade >= 0),
    CONSTRAINT fk_item_estoque_produto FOREIGN KEY (produto_id) REFERENCES tb_produto (id) ON DELETE CASCADE,
    CONSTRAINT uk_item_estoque_variacao UNIQUE (produto_id, tamanho, cor)
);

CREATE INDEX idx_produto_categoria ON tb_produto (categoria_id);
CREATE INDEX idx_item_estoque_produto ON tb_item_estoque (produto_id);

INSERT INTO roles (id, name) VALUES (uuid_generate_v4(), 'ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, name) VALUES (uuid_generate_v4(), 'ROLE_ADMIN') ON CONFLICT DO NOTHING;

INSERT INTO tb_categoria (nome) VALUES ('CAMISETAS') ON CONFLICT DO NOTHING;
INSERT INTO tb_categoria (nome) VALUES ('CALCAS') ON CONFLICT DO NOTHING;
INSERT INTO tb_categoria (nome) VALUES ('CALCADOS') ON CONFLICT DO NOTHING;
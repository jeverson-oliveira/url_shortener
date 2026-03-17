<div align="center">

<h1>🔗 URL Shortener</h1>

<p>
  <strong>Encurtador de URLs simples, rápido e eficiente feito com Java.</strong>
</p>

<p>
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/license-MIT-blue?style=for-the-badge" alt="License"/>
</p>

<p>
  <a href="#-sobre">Sobre</a> •
  <a href="#-funcionalidades">Funcionalidades</a> •
  <a href="#-tecnologias">Tecnologias</a> •
  <a href="#-como-rodar">Como Rodar</a> •
  <a href="#-endpoints">Endpoints</a> •
  <a href="#-contribuindo">Contribuindo</a>
</p>

</div>

---

## 📌 Sobre

O **URL Shortener** é uma aplicação back-end desenvolvida em **Java** que permite transformar URLs longas em links curtos e fáceis de compartilhar. Ideal para quem precisa de uma solução própria, sem depender de serviços de terceiros como Bitly ou TinyURL.

---

## ✨ Funcionalidades

- ✅ Encurtar URLs longas em links compactos
- ✅ Redirecionar automaticamente ao acessar o link curto
- ✅ Geração de código único para cada URL
- ✅ API REST para integração com outros serviços
- 🔜 Contagem de acessos por link *(em breve)*
- 🔜 Expiração de links por tempo *(em breve)*

---

## 🛠️ Tecnologias

As seguintes tecnologias foram utilizadas neste projeto:

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 17+ | Linguagem principal |
| Spring Boot | 3.x | Framework web |
| Maven | 3.x | Gerenciador de dependências |
| H2 / PostgreSQL | - | Banco de dados |

---

## 🚀 Como Rodar

### Pré-requisitos

Antes de começar, você vai precisar ter instalado na sua máquina:

- [Java 17+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/devjeverson/url_shortener.git

# 2. Acesse a pasta do projeto
cd url_shortener

# 3. Instale as dependências e gere o build
mvn clean install

# 4. Rode a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: **`http://localhost:8080`**

---

## 📡 Endpoints

### `POST /shorten`
Encurta uma URL longa.

**Request Body:**
```json
{
  "url": "https://www.exemplo.com/pagina-muito-longa/com-varios-parametros"
}
```

**Response:**
```json
{
  "shortUrl": "http://localhost:8080/abc123",
  "originalUrl": "https://www.exemplo.com/pagina-muito-longa/com-varios-parametros"
}
```

---

### `GET /{code}`
Redireciona para a URL original correspondente ao código.

```
GET /abc123  →  302 Redirect  →  https://www.exemplo.com/...
```

---

## 📂 Estrutura do Projeto

```
url_shortener/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/urlshortener/
│   │   │       ├── controller/   # Camada de controllers REST
│   │   │       ├── service/      # Regras de negócio
│   │   │       ├── repository/   # Acesso ao banco de dados
│   │   │       └── model/        # Entidades
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## 🤝 Contribuindo

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um **fork** do projeto
2. Crie uma branch com sua feature: `git checkout -b feature/minha-feature`
3. Commit suas mudanças: `git commit -m 'feat: minha nova feature'`
4. Push para a branch: `git push origin feature/minha-feature`
5. Abra um **Pull Request** 🚀

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](./LICENSE) para mais detalhes.

---

<div align="center">

Feito com ❤️ por <a href="https://github.com/devjeverson"><strong>devjeverson</strong></a>

</div>

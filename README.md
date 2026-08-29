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
| Java | 21 | Linguagem principal (LTS) |
| Spring Boot | 3.5.3 | Framework web |
| Maven | 3.9+ | Gerenciador de dependências |
| MySQL | 8.0 | Banco de dados |
| Docker / Compose | 29+ / v5+ | Containerização |

---

## 🚀 Como Rodar

### Pré-requisitos

- [Java 21](https://www.oracle.com/java/technologies/downloads/) (LTS)
- [Maven 3.9+](https://maven.apache.org/download.cgi) ou use o wrapper `./mvnw`
- [Git](https://git-scm.com/)
- [Docker + Docker Compose](https://docs.docker.com/get-docker/) *(opcional, recomendado)*

### Configuração de ambiente

```bash
cp .env.example .env
# edite .env se necessário: DB_URL, DB_USER, DB_PASS, APP_BASE_URL
```

Variáveis principais (`application.properties` já tem defaults para dev):
- `DB_URL` (default `jdbc:mysql://localhost:3306/shortener?...`)
- `DB_USER` / `DB_PASS`
- `APP_BASE_URL` (default `http://localhost:8080`, usado para montar a URL curta)

### Opção A — Docker (recomendado)

```bash
# build + sobe MySQL 8.0 + app
docker compose up --build

# logs
docker compose logs -f app
```

A aplicação estará em **`http://localhost:8080`** e MySQL em **`localhost:3306`**.

### Opção B — Local (sem Docker)

```bash
# 1. Clone
git clone https://github.com/jeverson-oliveira/url_shortener.git
cd url_shortener

# 2. Configure um MySQL local e ajuste .env ou exporte DB_URL/DB_USER/DB_PASS

# 3. Build
./mvnw clean package -DskipTests

# 4. Run
./mvnw spring-boot:run
# ou
java -jar target/shortener-0.0.1-SNAPSHOT.jar
```

---

## 📡 Endpoints

Documentação interativa: **`http://localhost:8080/docs`** (Swagger) e **`http://localhost:8080/v3/api-docs`**

### `POST /api/v1/urls` — Encurta URL (JSON) ⭐ *recomendado*

```bash
curl -X POST http://localhost:8080/api/v1/urls \
  -H "Content-Type: application/json" \
  -d '{"url":"https://www.exemplo.com/pagina-muito-longa/com-varios-parametros"}'
```

**Response `201 Created`:**
```json
{
  "shortUrl": "http://localhost:8080/abc123",
  "originalUrl": "https://www.exemplo.com/pagina-muito-longa/com-varios-parametros",
  "code": "abc123",
  "createdAt": "2026-08-29T13:30:00"
}
```
Headers: `Location: /api/v1/urls/abc123`

### `GET /api/v1/urls/{code}` — Detalhes

```bash
curl http://localhost:8080/api/v1/urls/abc123
```

**Response `200`:**
```json
{
  "code": "abc123",
  "originalUrl": "https://www.exemplo.com/...",
  "shortUrl": "http://localhost:8080/abc123",
  "createdAt": "2026-08-29T13:30:00"
}
```
`404` se não existir.

### `DELETE /api/v1/urls/{code}` — Remove

```bash
curl -X DELETE http://localhost:8080/api/v1/urls/abc123
```
`204 No Content` ou `404`.

### `GET /{code}` — Redireciona

```
GET /abc123  →  302 Found  →  https://www.exemplo.com/...
```
`404` se código não existir (via `UrlNotFoundException` + `GlobalExceptionHandler`).

### `POST /shorten` — Legado (deprecated)

Mantido por compatibilidade, prefira `POST /api/v1/urls`.

```bash
curl -X POST http://localhost:8080/shorten \
  -H "Content-Type: text/plain" \
  -d "https://www.exemplo.com/pagina-muito-longa/com-varios-parametros"
# → http://localhost:8080/abc123  (text/plain)
```

### Outros

- `GET /` → `200` status + docs
- `GET /health` → `200 {"status":"UP"}`
- `GET /docs` → Swagger UI
- `GET /favicon.ico` → `204`

---

## 📂 Estrutura do Projeto

```
url_shortener/
├── src/
│   ├── main/
│   │   ├── java/com/project/shortener/
│   │   │   ├── controller/   # REST (UrlShortener, ApiV1Url, Home)
│   │   │   ├── service/      # Regras + anti-colisão + find/delete
│   │   │   ├── repository/   # Spring Data JPA
│   │   │   ├── dto/          # CreateUrlRequest/Response, UrlDetailsResponse
│   │   │   ├── exception/    # UrlNotFoundException, GlobalExceptionHandler
│   │   │   └── entity/       # ShortUrl (@Table, @Column)
│   │   └── resources/
│   │       └── application.properties  # defaults + APP_BASE_URL + springdoc /docs
│   └── test/
├── Dockerfile                # multi-stage eclipse-temurin:21 (HEALTHCHECK /health)
├── docker-compose.yml        # app + mysql:8.0 (healthcheck)
├── .dockerignore
├── .env.example
├── pom.xml                   # Java 21, com.mysql:mysql-connector-j, validation, springdoc 2.8.8
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

Feito com ❤️ por <a href="https://github.com/jeverson-oliveira"><strong>jeverson-oliveira</strong></a>

</div>

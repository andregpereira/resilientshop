# Resilient Shop

Resilient Shop Backend GitHub Repository

---

## Sumário

1. [Instruções de uso](#instruções-de-uso)
    1. [Rodar todos os módulos](#rodar-todos-os-módulos)
    2. [Rodar um módulo individualmente](#rodar-um-módulo-individualmente)

---

## Instruções de uso

Para este projeto, você precisará dos seguintes itens instalados na sua máquina:

- Java 17
- Maven (Opcional - o projeto possui um wrapper)
- [Rancher Desktop](https://rancherdesktop.io/) ou [Podman Desktop](https://podman-desktop.io/)

---

### Rodar todos os módulos

Siga o passo a passo a seguir para buildar os módulos, criar suas respectivas imagens e rodar o projeto:

**Obs.:** Use `nerdctl`/`docker` se estiver usando containerd/moby do Rancher Desktop ou `podman` se estiver usando
Podman Desktop.

1. Buildar projeto: `./mvnw clean package -Dmaven.test.skip -f pom.xml`  
   <br>
2. Criar todas as imagens dos módulos: `nerdctl compose -f ./docker-compose.yaml build`  
   <br>
3. Subir os contêineres: `nerdctl compose -f ./docker-compose.yaml up -d`

###### Flags

- `-f`: indica o caminho dos arquivos de configuração `pom.xml` e `docker-compose.yaml`
- `-d`: separa o contêiner em um processo independente

---

### Rodar um módulo individualmente

Siga o passo a passo a seguir para criar e subir o módulo desejado:

**Obs.:** Use `nerdctl`/`docker` se estiver usando containerd/moby do Rancher Desktop ou `podman` se estiver usando
Podman Desktop.

1. Buildar projeto: `./mvnw clean package -Dmaven.test.skip -f pom.xml`  
   <br>
2. Criar imagem: `nerdctl build --platform linux/amd64 ./resilientshop-authentication-api/ -t resilientshop_authentication-api`  
   <br>
3. Criar contêiner:
   `nerdctl run --platform linux/amd64 -e SPRING_DATASOURCE_URL=jdbc:postgresql://auth-db/resilientshop-authentication -e {...} -d resilientshop_authentication-api`

###### Flags

- `--platform`: indica a arquitetura utilizada na criação da imagem e do contêiner
- `-d`: separa o contêiner em um processo independente

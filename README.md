# Resilient Shop

Resilient Shop Backend GitHub Repository

## Instruções de uso

Para este projeto, você precisará dos seguintes itens instalados na sua máquina:

- Java 17
- Maven (Opcional - o projeto possui um wrapper)
- [Rancher Desktop](https://rancherdesktop.io/) ou [Podman Desktop](https://podman-desktop.io/)

Siga o passo a passo a seguir para buildar os módulos, criar suas respectivas imagens e rodar o projeto:

**Obs.:** Use `nerdctl`/`docker` se estiver usando containerd/moby do Rancher Desktop ou `podman` se estiver usando
Podman Desktop.

1. Buildar o projeto: `./mvnw clean package -Dmaven.test.skip -f pom.xml`  
   <br>
2. Buildar as imagens dos módulos: `nerdctl compose -f ./docker-compose.yaml build`  
   <br>
3. Subir os contêineres: `nerdctl compose -f ./docker-compose.yaml up -d`

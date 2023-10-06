<<<<<<<< HEAD:start-frontend.sh
docker compose -f docker-compose-frontend.yml pull

COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker compose -f docker-compose-frontend.yml up --build -d
========
docker compose -f docker-compose-backend.yml pull

COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker compose -f docker-compose-backend.yml up --build -d
>>>>>>>> ba2dd7685093dee5c7bb5090160ab55217ecd09d:start-backend.sh

docker compose -f docker-compose-workers.yml pull

COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker compose -f docker-compose-workers.yml up --build -d

docker rmi -f $(docker images -f "dangling=true" -q) || true

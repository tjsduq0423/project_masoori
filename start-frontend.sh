docker compose -f docker-compose-frontend.yml pull

COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker compose -f docker-compose-frontend.yml up --build -d

docker rmi -f $(docker images -f "dangling=true" -q) || true

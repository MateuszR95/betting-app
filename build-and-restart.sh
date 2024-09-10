docker build -t betapp .
docker stop betapp || true
docker rm betapp || true
docker run -d -p 8080:8080 --name betapp betapp
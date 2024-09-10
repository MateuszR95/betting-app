docker build -t betapp .
docker stop betapp
docker rm betapp
docker run -d -p 8080:8080 --name betapp betapp
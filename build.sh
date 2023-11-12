echo '------------- Build Project ----------------'
mvn install package
echo '------------ Up with Docker Compose --------------'
docker-compose up -d --build

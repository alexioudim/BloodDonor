# Start container with database blooddb

```bash
docker run --name team50  \
-p 5432:5432 \
-e POSTGRES_PASSWORD=pass123 \
-e POSTGRES_USER=dbuser \
-e POSTGRES_DB=blooddb \
postgres:14
```

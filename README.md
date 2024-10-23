# GameShelf

An app to keep track of board games you own and exchange them with your friends.

## Configuration

Before initializing the whole project, you will need to configure some of its applications.
Please first configure :

- The backend with those instructions : [README Backend](./back/README.md)

## Initialization

To launch GameShelf, you need to use [Docker Compose](https://docs.docker.com/compose/).

First, create a new `.env` file at the root of this project and add the following properties :

```dotenv
GAMESHELF_POSTGRES_USER=your_postgres_user
GAMESHELF_POSTGRES_PASSWORD=your_postgres_password

GAMESHELF_PGADMIN_EMAIL=your_pgadmin_email
GAMESHELF_PGADMIN_PASSWORD=your_pgadmin_password

DATABASE_VOLUME_PATH="D:\Your\Path"
```

Then you will need to build images with the command :

```bash
docker compose build
```

And finally launch those containers in detached mode with this command :

```bash
docker compose up -d
```

If everythings worked as planned, you should be able to reach the following applications on your local machine :

- Backend API: http://localhost:8081
- PGAdmin : http://localhost:8082

> [!NOTE]
> The ports can vary depending on the settings you used.

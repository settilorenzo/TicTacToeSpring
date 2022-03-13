Getting started

Start Docker with:

```shell
docker run -it --name tictactoesql -e MYSQL_ROOT_PASSWORD=1234 -p 3306:3306 mysql
```

Created frontend folder with:

```shell
npx create-react-app frontend --template typescript
```

In folder frontend:

```shell
npm run start
```

and

```shell
npm install @mui/material @emotion/react @emotion/styled
```
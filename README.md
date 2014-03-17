To run the test - create Postgresql database:

``` postgresql
create database eager;
create user eager;
grant all on database eager to eager;
alter user eager password 'eager';
```

And then just do `sbt run`

# Local infrastructure

Run `docker compose config --quiet`, then `docker compose up -d`.
PostgreSQL 16 initializes `clients` and `portals` databases on a new volume.
PGAdmin waits for PostgreSQL health. All published ports bind to localhost.
The checked-in credentials remain local demo credentials; do not expose this stack publicly.

## Existing data

The initialization script does not run on existing volumes. If those databases are
missing, create them individually without deleting data:

```sh
docker compose exec postgresql psql -U postgres -d postgres -c 'CREATE DATABASE clients;'
docker compose exec postgresql psql -U postgres -d postgres -c 'CREATE DATABASE portals;'
```

Run only the command for a missing database. Check with
`docker compose exec postgresql psql -U postgres -d postgres -c '\l'` first.

The previous unversioned Postgres image may have initialized a different major version.
Check `docker compose exec postgresql cat /data/postgres/PG_VERSION` using the old image
before switching an existing volume. If it is not 16, dump the databases with the
matching old image, restore into a separate PostgreSQL 16 volume, and verify the restore.
Do not mount another major version's data directory into PostgreSQL 16.
`docker compose down` preserves volumes; do not add `--volumes` unless intentionally deleting data.

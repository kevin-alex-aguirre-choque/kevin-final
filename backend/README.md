
# aapos-api

API REST para gestión de clientes del servicio de agua — AAPOS Potosí.

## Stack
- Java 17 + Spring Boot 4.0.6
- Gradle
- PostgreSQL (JPA / Hibernate)

## Endpoints

### Cliente
| Método | Ruta                | Descripción              |
|--------|---------------------|---------------------------|
| GET    | /api/clientes        | Listar todos los clientes |
| GET    | /api/clientes/{id}   | Buscar por ID              |
| POST   | /api/clientes         | Crear cliente               |
| PUT    | /api/clientes/{id}   | Actualizar cliente          |
| DELETE | /api/clientes/{id}   | Eliminar cliente            |

Body de ejemplo (POST/PUT):
```json
{
  "nombre": "Juan Pérez",
  "ci": "1234567 PT",
  "direccion": "Av. Universitaria #123",
  "telefono": "71234567"
}
```

### Medidor
| Método | Ruta                              | Descripción                     |
|--------|------------------------------------|-----------------------------------|
| GET    | /api/medidores                     | Listar todos                       |
| GET    | /api/medidores/cliente/{idCliente} | Medidores de un cliente            |
| GET    | /api/medidores/{id}                | Buscar por ID                       |
| POST   | /api/medidores                     | Crear medidor                        |
| PUT    | /api/medidores/{id}                | Actualizar medidor                   |
| DELETE | /api/medidores/{id}                | Eliminar medidor                     |

Body de ejemplo (POST/PUT):
```json
{
  "numeroMedidor": "MED-0001",
  "direccionInstalacion": "Av. Universitaria #123",
  "fechaInstalacion": "2026-01-15",
  "estado": "ACTIVO",
  "cliente": { "idCliente": 1 }
}
```

### Factura
| Método | Ruta                              | Descripción                      |
|--------|------------------------------------|------------------------------------|
| GET    | /api/facturas                      | Listar todas                        |
| GET    | /api/facturas/medidor/{idMedidor}  | Facturas de un medidor              |
| GET    | /api/facturas/pendientes           | Facturas no pagadas                 |
| GET    | /api/facturas/{id}                 | Buscar por ID                        |
| POST   | /api/facturas                      | Crear factura (calcula consumo/monto automáticamente) |
| PUT    | /api/facturas/{id}                 | Actualizar lecturas                  |
| PATCH  | /api/facturas/{id}/pagar           | Marcar como pagada                   |
| DELETE | /api/facturas/{id}                 | Eliminar factura                     |

Body de ejemplo (POST):
```json
{
  "periodo": "2026-07",
  "lecturaAnterior": 120,
  "lecturaActual": 145,
  "medidor": { "idMedidor": 1 }
}
```
`consumoM3` y `montoTotal` se calculan solos a partir de las lecturas (tarifa referencial de 3.50 Bs/m³ — ajustar en `FacturaService`).

### Reclamo
| Método | Ruta                              | Descripción                    |
|--------|------------------------------------|-----------------------------------|
| GET    | /api/reclamos                      | Listar todos                       |
| GET    | /api/reclamos/cliente/{idCliente}  | Reclamos de un cliente             |
| GET    | /api/reclamos/estado/{estado}      | Reclamos por estado                |
| GET    | /api/reclamos/{id}                 | Buscar por ID                       |
| POST   | /api/reclamos                      | Crear reclamo                        |
| PUT    | /api/reclamos/{id}                 | Actualizar reclamo                   |
| PATCH  | /api/reclamos/{id}/estado          | Cambiar estado (body: `{"estado":"RESUELTO"}`) |
| DELETE | /api/reclamos/{id}                 | Eliminar reclamo                     |

Body de ejemplo (POST):
```json
{
  "tipo": "FUGA",
  "descripcion": "Fuga de agua en la acera frente al domicilio",
  "cliente": { "idCliente": 1 }
}
```

## Correr local
Necesitas PostgreSQL local o remoto. Configura variables de entorno:
```
DATABASE_URL=jdbc:postgresql://localhost:5432/aapos_db
DB_USERNAME=postgres
DB_PASSWORD=tu_password
```
Luego:
```
./gradlew bootRun
```

## Deploy en Render
Ver pasos detallados abajo.

1. Crea una base PostgreSQL en Render (New + → PostgreSQL)
2. Crea un Web Service → conecta este repo → Runtime: **Docker**
3. En Settings → Environment agrega:
   ```
   DATABASE_URL=jdbc:postgresql://<host>:5432/<db>?sslmode=require
   DB_USERNAME=<usuario>
   DB_PASSWORD=<password>
   ```
   Importante: la URL que Render te da por defecto empieza con `postgres://` — tienes que cambiarla a `jdbc:postgresql://` y agregar `?sslmode=require`.
4. Deploy. Render arma la imagen con el `Dockerfile` y expone el puerto vía la variable `PORT`.
5. Prueba: `GET https://tu-servicio.onrender.com/api/clientes` → debería devolver `[]`.

## Colaboradores
_(agregar usernames de GitHub aquí)_

# aapos-api-aguirre-ch
API REST para gestión de servicios AAPOS Potosí


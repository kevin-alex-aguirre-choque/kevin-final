# AAPOS Potosí - Sitio Institucional (React + Vite)

Proyecto frontend basado en el sitio actual `aapospotosi.com`, siguiendo las
convenciones del proyecto VSIAF (CSS plano, sin frameworks de estilos,
componentes en `src/components/`).

## Estructura

```
src/
  components/   Navbar, Footer, RazonCard, NoticiaCard, WaterMascot
  pages/        Inicio, SobreNosotros, Servicios, Contactos
  App.jsx       Rutas (react-router-dom)
  main.jsx      Punto de entrada
  index.css     Variables de color y estilos globales
```

## Colores usados (extraídos del sitio original)
- Azul principal: `#1e88c7`
- Azul oscuro: `#106a9e`
- Naranja: `#ef7d1f`
- Oscuro (navbar/footer): `#2b2b2b`

## Cómo correr

```bash
npm install
npm run dev
```

## Build de producción

```bash
npm run build
npm run preview
```

## Notas
- Las imágenes reales del sitio (fotos de trabajadores, lagunas, etc.) están
  reemplazadas por bloques de color/gradiente como placeholder. Reemplázalas
  en `src/pages/*.css` (clases `*-image`) por tus fotos reales cuando las
  tengas, o pásamelas y las integro.
- El formulario de Contactos es solo de UI (no envía a backend aún). Si
  quieres que apunte a tu aapos-api, dime el endpoint y lo conecto con fetch.

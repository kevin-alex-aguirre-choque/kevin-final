import { useState } from 'react';
import './Contactos.css';

const API_URL = 'https://aapos-api-aguirre-ch.onrender.com/api';

function Contactos() {
  const [formData, setFormData] = useState({
    nombre: '',
    ci: '',
    telefono: '',
    direccion: '',
    tipoReclamo: '',
    descripcion: ''
  });

  const [mensaje, setMensaje] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMensaje('');
    setError('');
    setLoading(true);

    try {
      console.log(' Paso 1: Creando cliente...');
      
      // PASO 1: Crear cliente
      const resCliente = await fetch(`${API_URL}/clientes`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          nombre: formData.nombre,
          ci: formData.ci,
          telefono: formData.telefono,
          direccion: formData.direccion
        })
      });

      if (!resCliente.ok) {
        const errText = await resCliente.text();
        throw new Error('Error al crear cliente: ' + errText);
      }

      const cliente = await resCliente.json();
      console.log(' Cliente creado:', cliente);
      console.log(' ID del cliente:', cliente.idCliente);

      // PASO 2: Crear reclamo
      console.log(' Paso 2: Creando reclamo...');
      
      const resReclamo = await fetch(`${API_URL}/reclamos`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          tipo: formData.tipoReclamo,
          descripcion: formData.descripcion,
          cliente: { idCliente: cliente.idCliente }
        })
      });

      if (!resReclamo.ok) {
        const errText = await resReclamo.text();
        throw new Error('Error al crear reclamo: ' + errText);
      }

      const reclamo = await resReclamo.json();
      console.log(' Reclamo creado:', reclamo);

      setMensaje(' ¡Datos guardados en la base de datos! Revisa Swagger');
      
      // Limpiar formulario
      setFormData({
        nombre: '',
        ci: '',
        telefono: '',
        direccion: '',
        tipoReclamo: '',
        descripcion: ''
      });

    } catch (err) {
      console.error(' Error completo:', err);
      setError(' ' + err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="contactos-page">
      <h1>📝 Formulario de Reclamo</h1>

      {mensaje && <div className="alert success">{mensaje}</div>}
      {error && <div className="alert error">{error}</div>}

      <form onSubmit={handleSubmit} className="form-contacto">
        <div className="form-group">
          <label>Nombre completo</label>
          <input
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            placeholder="Tu nombre completo"
            required
          />
        </div>

        <div className="form-group">
          <label>CI</label>
          <input
            type="text"
            name="ci"
            value={formData.ci}
            onChange={handleChange}
            placeholder="Carnet de identidad"
            required
          />
        </div>

        <div className="form-group">
          <label>Teléfono</label>
          <input
            type="tel"
            name="telefono"
            value={formData.telefono}
            onChange={handleChange}
            placeholder="Número de teléfono"
          />
        </div>

        <div className="form-group">
          <label>Dirección</label>
          <input
            type="text"
            name="direccion"
            value={formData.direccion}
            onChange={handleChange}
            placeholder="Tu dirección"
          />
        </div>

        <div className="form-group">
          <label>Tipo de reclamo</label>
          <input
            type="text"
            name="tipoReclamo"
            value={formData.tipoReclamo}
            onChange={handleChange}
            placeholder="Ej: Fuga de agua"
            required
          />
        </div>

        <div className="form-group">
          <label>Descripción</label>
          <textarea
            name="descripcion"
            value={formData.descripcion}
            onChange={handleChange}
            rows="5"
            placeholder="Describe tu reclamo..."
            required
          />
        </div>

        <button type="submit" disabled={loading} className="btn-submit">
          {loading ? '⏳ Guardando...' : '💾 Guardar en Base de Datos'}
        </button>
      </form>

      <div className="swagger-info">
        <h3>🔍 Verificar en Swagger</h3>
        <p>Después de guardar, abre:</p>
        <a href="http://localhost:8080/swagger-ui/index.html" target="_blank" rel="noopener noreferrer">
          http://localhost:8080/swagger-ui/index.html
        </a>
        <p>Y prueba:</p>
        <ul>
          <li><code>GET /api/clientes</code> → Ver cliente creado</li>
          <li><code>GET /api/reclamos</code> → Ver reclamo creado</li>
        </ul>
      </div>
    </div>
  );
}

export default Contactos;
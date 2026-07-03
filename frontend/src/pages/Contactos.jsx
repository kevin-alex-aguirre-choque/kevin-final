import { useState, useEffect } from 'react';
import './Contactos.css';

const API_URL = 'http://localhost:8080/api';

function Contactos() {
  const [formData, setFormData] = useState({
    nombre: '',
    ci: '',
    telefono: '',
    direccion: '',
    tipoReclamo: '',
    descripcion: ''
  });

  const [loading, setLoading] = useState(false);
  const [mensaje, setMensaje] = useState('');
  const [reclamosGuardados, setReclamosGuardados] = useState([]);

  // Cargar reclamos al iniciar
  useEffect(() => {
    cargarReclamos();
  }, []);

  const cargarReclamos = async () => {
    try {
      const res = await fetch(`${API_URL}/reclamos`);
      const datos = await res.json();
      setReclamosGuardados(datos);
    } catch (error) {
      console.error('Error al cargar reclamos:', error);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMensaje('');

    try {
      // 1. Buscar o crear cliente
      const resClientes = await fetch(`${API_URL}/clientes`);
      const clientes = await resClientes.json();
      let clienteExistente = clientes.find(c => c.ci === formData.ci);

      let idCliente;

      if (clienteExistente) {
        idCliente = clienteExistente.idCliente;
      } else {
        const resNuevoCliente = await fetch(`${API_URL}/clientes`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            nombre: formData.nombre,
            ci: formData.ci,
            telefono: formData.telefono,
            direccion: formData.direccion
          })
        });

        if (!resNuevoCliente.ok) throw new Error('Error al registrar cliente');

        const clienteCreado = await resNuevoCliente.json();
        idCliente = clienteCreado.idCliente;
      }

      // 2. Crear reclamo
      const resReclamo = await fetch(`${API_URL}/reclamos`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          tipo: formData.tipoReclamo,
          descripcion: formData.descripcion,
          cliente: { idCliente: idCliente }
        })
      });

      if (!resReclamo.ok) throw new Error('Error al crear el reclamo');

      setMensaje('✅ Reclamo registrado exitosamente en la base de datos');
      
      // Limpiar formulario
      setFormData({
        nombre: '', ci: '', telefono: '', direccion: '',
        tipoReclamo: '', descripcion: ''
      });

      // Recargar lista de reclamos
      cargarReclamos();

    } catch (error) {
      setMensaje('❌ Error: ' + error.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="contactos-container">
      <h1>Contactos</h1>

      {/* Información */}
      <div className="info-card">
        <p><strong>Horarios:</strong> Lunes a viernes de 8:00 a 12:00 y de 14:00 a 18:00</p>
        <p><strong>Email:</strong> aapos@aapos.com.bo</p>
        <p><strong>Facebook:</strong> AAPOSOFICIAL</p>
        <p><strong>Ciudad:</strong> Potosí, Bolivia</p>
      </div>

      {/* Mensaje */}
      {mensaje && (
        <div className={`alert ${mensaje.includes('✅') ? 'success' : 'error'}`}>
          {mensaje}
        </div>
      )}

      {/* Formulario */}
      <form onSubmit={handleSubmit} className="form-contacto">
        <h2>📝 Formulario de Reclamo</h2>
        
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
            placeholder="Ej: Fuga de agua, Facturación..."
            required
          />
        </div>

        <div className="form-group">
          <label>Descripción</label>
          <textarea
            name="descripcion"
            value={formData.descripcion}
            onChange={handleChange}
            placeholder="Describe tu reclamo..."
            rows="5"
            required
          />
        </div>

        <button type="submit" disabled={loading} className="btn-submit">
          {loading ? '💾 Guardando...' : '📤 Enviar Reclamo'}
        </button>
      </form>

      {/* Lista de reclamos guardados */}
      <div className="reclamos-lista">
        <h2>📋 Reclamos Registrados en la Base de Datos</h2>
        {reclamosGuardados.length === 0 ? (
          <p>No hay reclamos registrados aún</p>
        ) : (
          <table className="tabla-reclamos">
            <thead>
              <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Tipo</th>
                <th>Descripción</th>
                <th>Estado</th>
              </tr>
            </thead>
            <tbody>
              {reclamosGuardados.map((reclamo) => (
                <tr key={reclamo.idReclamo}>
                  <td>{reclamo.idReclamo}</td>
                  <td>{reclamo.cliente?.nombre || 'N/A'}</td>
                  <td>{reclamo.tipo}</td>
                  <td>{reclamo.descripcion}</td>
                  <td>
                    <span className={`estado ${reclamo.estado?.toLowerCase()}`}>
                      {reclamo.estado}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default Contactos;
import RazonCard from '../components/RazonCard.jsx'
import './Servicios.css'

const razones = [
  {
    title: 'TRABAJO PROFESIONAL',
    text: 'Su objetivo es apoyar el fortalecimiento e incremento de la cobertura de los servicios de agua potable, alcantarillado y saneamiento que prestan los organismos operadores, de los municipios, a través de las entidades.',
    image: '/v.jpg', 
    imageAlt: 'Trabajadores realizando mantenimiento en laguna',
  },
  {
    title: 'CALIDAD',
    text: 'Con la transparencia e inspecciones necesarias para el buen uso del agua potable.',
    image: '/n.jpg',  
    imageAlt: 'Trabajadores instalando válvulas de agua potable',
    reverse: true,
  },
]

const servicios = [
  {
    nombre: 'Agua Potable',
    detalle: 'Distribución y mantenimiento de la red de agua potable para la ciudad de Potosí.',
  },
  {
    nombre: 'Alcantarillado Sanitario',
    detalle: 'Recolección y tratamiento de aguas residuales para el saneamiento de la ciudad.',
  },
  {
    nombre: 'Atención al Cliente',
    detalle: 'Gestión de reclamos, medidores y facturación para nuestros usuarios.',
  },
]

function Servicios() {
  return (
    <>
      <section className="serv-razones">
        <div className="container">
          <h1 className="serv-razones-title">¡RAZONES PRINCIPALES PARA ELEGIRNOS!</h1>
          {razones.map((r) => (
            <RazonCard key={r.title} {...r} />
          ))}
        </div>
      </section>

      <section className="serv-lista container">
        <h2 className="section-title">Nuestros servicios</h2>
        <div className="serv-lista-grid">
          {servicios.map((s) => (
            <div className="serv-item" key={s.nombre}>
              <h3>{s.nombre}</h3>
              <p>{s.detalle}</p>
            </div>
          ))}
        </div>
      </section>
    </>
  )
}

export default Servicios
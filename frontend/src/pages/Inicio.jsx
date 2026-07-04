import { Link } from 'react-router-dom'
import WaterMascot from '../components/WaterMascot.jsx'
import NoticiaCard from '../components/NoticiaCard.jsx'
import './Inicio.css'

const noticias = [
  {
    titulo: 'AAPOS INFORMA',
    subtitulo: 'ACCIÓN COMUNAL DE LIMPIEZA LAGUNAS DEL KARI KARI',
    descripcion:
      'De la mano de todos los trabajadores de AAPOS POTOSÍ, se realizó la tarea de limpieza y mantenimiento de nuestras lagunas de la cuenca del Kari Kari.',
  },
  {
    titulo: 'AAPOS INFORMA',
    subtitulo: 'MANTENIMIENTO DE REDES DE AGUA POTABLE',
    descripcion:
      'Nuestro equipo técnico realizó trabajos de inspección y mantenimiento en las redes de distribución para garantizar un servicio de calidad.',
  },
  {
    titulo: 'AAPOS INFORMA',
    subtitulo: 'ATENCIÓN A LA COMUNIDAD',
    descripcion:
      'Se llevó a cabo una reunión informativa con vecinos y trabajadores del sindicato para coordinar las próximas acciones comunales.',
  },
]

function Inicio() {
  return (
    <>
      <section className="hero">
        <div className="hero-image" />
        <div className="hero-mascot">
          <WaterMascot />
        </div>
      </section>

      <section className="hero-intro container">
        <p>
          La Administración Autónoma para Obras Sanitarias AAPOS-POTOSÍ es
          responsable de brindar los servicios de abastecimiento de agua
          potable y alcantarillado sanitario a la ciudad de Potosí.
        </p>
        <Link to="/sobre-nosotros" className="hero-link">
          Conoce más sobre nosotros →
        </Link>
      </section>

      <section className="noticias container">
        <h2 className="section-title">Últimas noticias</h2>
        <div className="noticias-grid">
          {noticias.map((n) => (
            <NoticiaCard key={n.subtitulo} {...n} />
          ))}
        </div>
      </section>
    </>
  )
}

export default Inicio

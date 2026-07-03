import WaterMascot from '../components/WaterMascot.jsx'
import './SobreNosotros.css'

const equipo = [
  { cargo: 'Gerente General' },
  { cargo: 'Jefe Técnico' },
  { cargo: 'Jefe Comercial' },
  { cargo: 'Jefe Administrativo Financiero' },
]

function SobreNosotros() {
  return (
    <>
      <section className="sn-intro">
        <div className="container sn-intro-inner">
          <p>
            La Administración Autónoma para Obras Sanitarias AAPOS-POTOSÍ es
            responsable de brindar los servicios de abastecimiento de agua
            potable y alcantarillado sanitario a la ciudad de Potosí.
          </p>
          <WaterMascot size={130} />
        </div>
      </section>

      <section className="sn-equipo">
        <h2 className="sn-equipo-title">NUESTRO EQUIPO</h2>
        <div className="container sn-equipo-grid">
          {equipo.map((persona) => (
            <div className="sn-equipo-card" key={persona.cargo}>
              <div className="sn-equipo-avatar" />
              <p>{persona.cargo}</p>
            </div>
          ))}
        </div>
      </section>

      <section className="sn-mision container">
        <h2 className="section-title">Misión</h2>
        <p>
          Brindar servicios de agua potable y alcantarillado sanitario con
          calidad, transparencia y compromiso social, fortaleciendo la
          cobertura para toda la ciudad de Potosí.
        </p>
      </section>
    </>
  )
}

export default SobreNosotros

import { Routes, Route } from 'react-router-dom'
import Navbar from './components/Navbar.jsx'
import Footer from './components/Footer.jsx'
import Inicio from './pages/Inicio.jsx'
import SobreNosotros from './pages/SobreNosotros.jsx'
import Servicios from './pages/Servicios.jsx'
import Contactos from './pages/Contactos.jsx'

function App() {
  return (
    <>
      <Navbar />
      <main style={{ flex: 1 }}>
        <Routes>
          <Route path="/" element={<Inicio />} />
          <Route path="/sobre-nosotros" element={<SobreNosotros />} />
          <Route path="/servicios" element={<Servicios />} />
          <Route path="/contactos" element={<Contactos />} />
        </Routes>
      </main>
      <Footer />
    </>
  )
}

export default App

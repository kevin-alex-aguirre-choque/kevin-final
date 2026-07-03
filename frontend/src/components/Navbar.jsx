import { useState } from 'react'
import { NavLink } from 'react-router-dom'
import './Navbar.css'

const links = [
  { to: '/', label: 'Inicio' },
  { to: '/sobre-nosotros', label: 'Sobre nosotros' },
  { to: '/servicios', label: 'Servicios' },
  { to: '/contactos', label: 'Contactos' },
]

function Navbar() {
  const [open, setOpen] = useState(false)

  return (
    <header className="navbar">
      <div className="navbar-inner container">
        <NavLink to="/" className="navbar-brand" onClick={() => setOpen(false)}>
          <span className="navbar-brand-drop">💧</span>
          AAPOS POTOSÍ
        </NavLink>

        <button
          className="navbar-toggle"
          aria-label="Abrir menú"
          onClick={() => setOpen((v) => !v)}
        >
          <span />
          <span />
          <span />
        </button>

        <nav className={`navbar-links ${open ? 'is-open' : ''}`}>
          {links.map((link) => (
            <NavLink
              key={link.to}
              to={link.to}
              end={link.to === '/'}
              className={({ isActive }) =>
                'navbar-link' + (isActive ? ' active' : '')
              }
              onClick={() => setOpen(false)}
            >
              {link.label}
            </NavLink>
          ))}
        </nav>
      </div>
    </header>
  )
}

export default Navbar

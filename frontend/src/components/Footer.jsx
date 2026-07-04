import './Footer.css'

function Footer() {
  const year = new Date().getFullYear()

  return (
    <footer className="footer">
      <div className="footer-inner container">
        <div className="footer-info">
          <h3 className="footer-title">AAPOS POTOSÍ</h3>
          <p>Horarios de atención: Lunes a viernes de 8:00 a 12:00 y de 14:00 a 18:00</p>
          <p>Correo electrónico: aapos@aapos.com.bo</p>
          <a
            className="footer-social"
            href="https://facebook.com/AAPOSOFICIAL"
            target="_blank"
            rel="noreferrer"
          >
            <span className="footer-social-icon">f</span>
            AAPOSOFICIAL
          </a>
        </div>

        <div className="footer-copy">
          <p>© {year}</p>
          <p>aapospotosi.com</p>
        </div>
      </div>
    </footer>
  )
}

export default Footer

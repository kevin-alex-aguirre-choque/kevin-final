import './NoticiaCard.css'

function NoticiaCard({ titulo, subtitulo, descripcion, imagen }) {
  return (
    <div className="noticia-card">
      <div className="noticia-imagen">
        {imagen && <img src={imagen} alt={titulo} />}
      </div>
      <div className="noticia-contenido">
        <h3>{titulo}</h3>
        <h4>{subtitulo}</h4>
        <p>{descripcion}</p>
      </div>
    </div>
  )
}

export default NoticiaCard
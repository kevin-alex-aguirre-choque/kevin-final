import './RazonCard.css'

function RazonCard({ title, text, imageAlt, reverse }) {
  return (
    <div className={`razon-card ${reverse ? 'reverse' : ''}`}>
      <div className="razon-image" role="img" aria-label={imageAlt} />
      <div className="razon-text">
        <h3>{title}</h3>
        <p>{text}</p>
      </div>
    </div>
  )
}

export default RazonCard

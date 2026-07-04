function WaterMascot({ size = 160 }) {
  return (
    <svg
      width={size}
      height={size * 1.3}
      viewBox="0 0 160 210"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M80 5C80 5 30 80 30 120C30 152 52 178 80 178C108 178 130 152 130 120C130 80 80 5 80 5Z"
        fill="#3fa9dc"
        stroke="#1c1c1c"
        strokeWidth="3"
      />
      <circle cx="62" cy="105" r="7" fill="#1c1c1c" />
      <circle cx="98" cy="105" r="7" fill="#1c1c1c" />
      <path
        d="M55 130 Q80 118 105 130"
        stroke="#1c1c1c"
        strokeWidth="3"
        fill="none"
        strokeLinecap="round"
      />
      <ellipse cx="80" cy="150" rx="14" ry="7" fill="#ef7d1f" />
      <rect x="55" y="178" width="50" height="22" rx="4" fill="#ffffff" stroke="#1c1c1c" strokeWidth="2" />
      <text x="80" y="193" fontSize="9" textAnchor="middle" fill="#1c1c1c" fontWeight="bold">
        AAPOS
      </text>
    </svg>
  )
}

export default WaterMascot

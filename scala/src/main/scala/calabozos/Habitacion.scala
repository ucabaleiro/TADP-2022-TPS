package calabozos

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends (Grupo => Estado) {

  def apply(grupo: Grupo): Estado = {
    situacion(grupo) match {
      case TodosMurieron(estado) => estado
      case NoHayPuertas(estado) => estado
      case SalieronConExito(estado) => estado
      case Continuan(estado) => estado
    }
  }
}

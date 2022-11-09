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

  // TODO: Esto iría en otro lado
  // def apply(estado: Estado): Estado = {
  //   estado match
  //     case Continuan(grupo) => serRecorridaPor(grupo)
  //     case _ => estado
  // }

}

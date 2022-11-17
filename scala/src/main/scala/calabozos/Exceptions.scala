package calabozos

case class GrupoMuertoException()
  extends RuntimeException("El grupo está muerto")

case class GrupoEncerradoException()
  extends RuntimeException("El grupo no ha encontrado ninguna salida")

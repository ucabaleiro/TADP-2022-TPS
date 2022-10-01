class AsercionNoPasoError < StandardError
  attr_reader :nombre, :valor_esperado, :valor_obtenido

  def initialize(nombre, valor_esperado, valor_obtenido)
    @nombre = nombre
    @valor_esperado = valor_esperado
    @valor_obtenido = valor_obtenido
  end
end

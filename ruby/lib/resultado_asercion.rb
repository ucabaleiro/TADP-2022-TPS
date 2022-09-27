class ResultadoAsercion
  def initialize(nombre, valor_esperado, valor_obtenido, pasa)
    @nombre = nombre
    @pasa = pasa
    @valor_esperado = valor_esperado
    @valor_obtenido = valor_obtenido
  end

  def pasa?
    @pasa
  end
end

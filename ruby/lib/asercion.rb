class Asercion
  def initialize(&condicion_a_pasar)
    @condicion_a_pasar = condicion_a_pasar
    @pasos_intermedios = []
  end

  def desde(&bloque)
    @pasos_intermedios.push(bloque)
    self
  end

  def ejecutar_en(objeto)
    valor_obtenido = @pasos_intermedios.inject(objeto) { |o, proc| proc.call(o) }
    @condicion_a_pasar.call(valor_obtenido)
  end

end

class Asercion
  def initialize(nombre, valor_esperado, &condicion_a_pasar)
    @nombre = nombre
    @valor_esperado = valor_esperado
    @condicion_a_pasar = condicion_a_pasar
    @pasos_intermedios = []
  end

  def desde(&bloque)
    @pasos_intermedios.push(bloque)
    self
  end

  def ejecutar_en(objeto)
    valor_obtenido = @pasos_intermedios.inject(objeto) { |o, proc| proc.call(o) }
    pasa = @condicion_a_pasar.call(valor_obtenido)
    raise AsercionNoPasoError.new(@nombre, @valor_esperado, valor_obtenido) unless pasa
  end

end

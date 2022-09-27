class Asercion
  # Recibe un predicado que devuelve si la aserción fue correcta o no
  def initialize(nombre, valor_esperado, &condicion_a_pasar)
    @nombre = nombre
    @valor_esperado = valor_esperado
    @condicion_a_pasar = condicion_a_pasar
    @pasos_intermedios = []
  end

  # El bloque recibe al objeto y lo transforma en otro valor desde el cual se va a hacer la aserción
  def desde(&bloque)
    @pasos_intermedios.push(bloque)
    self # Se retorna a sí mismo para poder encadenarse, similar a una Promise de JS
  end

  # Va transformando el objeto hasta llegar al predicado inicial
  def ejecutar_en(objeto)
    valor_obtenido = @pasos_intermedios.inject(objeto) { |o, proc| proc.call(o) }
    pasa = @condicion_a_pasar.call(valor_obtenido)
    raise NoPasoAsercion.new(@nombre, @valor_esperado, valor_obtenido) unless pasa
  end

end

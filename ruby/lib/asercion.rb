class Asercion
  # Recibe un predicado que devuelve si la aserción fue correcta o no
  def initialize(nombre, valor_esperado, &proc)
    @nombre = nombre
    @valor_esperado = valor_esperado
    @proc = proc
    @pasos = []
  end

  # El bloque recibe al objeto y lo transforma en otro valor desde el cual se va a hacer la aserción
  def desde(&bloque)
    @pasos.push(bloque)
    self # Se retorna a sí mismo para poder encadenarse
  end

  # Va transformando el objeto hasta llegar al predicado inicial
  def ejecutar_en(objeto)
    valor_obtenido = @pasos.inject(objeto) { |o, proc| proc.call(o) }
    pasa = @proc.call(valor_obtenido)
    ResultadoAsercion.new(self, pasa)
  end

end

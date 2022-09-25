class Asercion
  # Recibe un predicado que devuelve si la aserción fue correcta o no
  def initialize(&predicate)
    @aserciones = [predicate]
  end

  # El bloque recibe al objeto y lo transforma en otro valor desde el cual se va a hacer la aserción
  def desde(&bloque)
    @aserciones.unshift(bloque)
    self # Se retorna a sí mismo para poder encadenarse
  end

  # Va transformando el objeto hasta llegar al predicado inicial
  def ejecutar_en(objeto)
    exitoso = @aserciones.inject(objeto) { |o, proc| proc.call(o) }
    Resultado.new(exitoso)
  end
end

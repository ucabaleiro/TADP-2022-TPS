module Asertable
  # Ejecuta una aserción y devuelve un resultado
  def deberia(asercion)
    asercion.ejecutar_en(self)
  end
end

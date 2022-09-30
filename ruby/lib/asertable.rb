module Asertable
  def deberia(asercion)
    asercion.ejecutar_en(self)
    true
  end
end

module Asertable
  def self.incluir_en(clase)
    # Ejecuta una aserción y devuelve un resultado
    clase.define_method(:deberia) do |asercion|
      asercion.ejecutar_en(self)
    end
  end

  def self.quitar_de(clase)
    clase.undef_method(:deberia)
  end
end

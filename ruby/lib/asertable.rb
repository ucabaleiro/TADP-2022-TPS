class NoPasoAsercion < StandardError
  attr_reader :resultado

  def initialize(resultado)
    @resultado = resultado
  end
end

module Asertable
  def self.incluir_en(clase)
    # Ejecuta una aserción y devuelve un resultado
    clase.define_method(:deberia) do |asercion|
      resultado = asercion.ejecutar_en(self)
      raise NoPasoAsercion.new(resultado) unless resultado.pasa?
      resultado
    end
  end

  def self.quitar_de(clase)
    clase.undef_method(:deberia)
  end
end


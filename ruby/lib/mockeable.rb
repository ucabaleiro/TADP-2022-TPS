module Mockeable
  def mockear(nombre, &bloque)
    Cambios.guardar(Mock.new(self, nombre, &bloque))
  end
end

class Mock
  def initialize(clase, mensaje, &implementacion)
    @clase = clase
    @metodo_original = clase.instance_method(mensaje)
    clase.define_method(mensaje, &implementacion)
  end

  def revertir
    @metodo_original.owner.remove_method(@metodo_original.name)
    if @metodo_original.owner == @clase
      @metodo_original.owner.define_method(@metodo_original.name, @metodo_original)
    end
  end
end

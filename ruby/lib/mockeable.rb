module Mockeable
  def mockear(nombre, &bloque)
    Cambios.guardar(Mock.new(self, nombre, &bloque))
  end
end

class Mock
  def initialize(clase, metodo, &implementacion)
    @metodo_original = clase.instance_method(metodo)
    clase.define_method(metodo, &implementacion)
  end

  def revertir
    @metodo_original.owner.define_method(@metodo_original.name, @metodo_original)
  end
end

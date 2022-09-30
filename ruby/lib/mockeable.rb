module Mockeable
  def self.guardar(metodo)
    @mocks ||= []
    @mocks << metodo
  end

  def self.restaurar
    @mocks ||= []
    @mocks.each do |metodo|
      metodo.owner.define_method(metodo.name, metodo)
    end
  end

  def mockear(nombre, &bloque)
    Mockeable.guardar(instance_method(nombre))
    define_method(nombre, &bloque)
  end
end

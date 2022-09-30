module Mockeable
  def self.guardar(metodo)
    @mocks ||= []
    @mocks << metodo
  end

  def self.restaurar
    @mocks ||= []
    @mocks.each do |metodo|
      metodo.receiver.define_singleton_method(metodo.name, metodo)
    end
  end

  def mockear(nombre, &bloque)
    Mockeable.guardar(method(nombre))
    define_singleton_method(nombre, &bloque)
  end
end

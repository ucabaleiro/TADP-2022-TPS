module Mock
  def self.guardar(metodo)
    @mocks ||= []
    @mocks << metodo
  end

  def self.restaurar
    @mocks ||= []
    @mocks.each do |metodo|
      # TODO: NO ANDA!!!!!!!! xdn't
      metodo.receiver.define_singleton_method(metodo.name, metodo.to_proc)
    end
  end

  def mockear(nombre, &bloque)
    Mock.guardar(method(nombre))
    define_singleton_method(nombre, &bloque)
  end
end

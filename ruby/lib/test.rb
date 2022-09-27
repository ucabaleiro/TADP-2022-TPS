class Test
  def initialize(nombre, clase)
    @nombre = nombre
    @clase = clase
  end

  def testear
    nombre = @nombre
    @clase.new.instance_eval do
      singleton_class.include Aserciones
      begin
        send("testear_que_#{nombre}")
        # TODO: El test pasó
      rescue NoPasoAsercion => error
        error.resultado
        # TODO: El test falló
      rescue StandardError => error
        # TODO: El test explotó
      end
    end
  end
end

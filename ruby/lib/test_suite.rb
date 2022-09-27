class TestSuite
  def initialize(clase)
    @clase = clase
  end

  def tiene_clase?(clase)
    @clase == clase
  end

  def tests
    @clase.instance_methods(false)
          .filter { |metodo| metodo.to_s.start_with? "testear_que_" }
          .map { |metodo| metodo.to_s.delete_prefix("testear_que_").to_sym }
  end

  def testear(*metodos)
    metodos.push(*tests) if metodos.empty?

    metodos.map do |metodo|
      @clase.new.instance_eval do
        singleton_class.include Aserciones
        send("testear_que_#{metodo}")
      end
    end
  end
end


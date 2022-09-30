require_relative './helpers'

describe "Tests y Suites" do
  let(:clase_suite) do
    Class.new do
      class << self
        attr_accessor :pasa_algo_ejecutado, :pasa_otra_cosa_ejecutado, :metodo_comun_ejecutado
      end

      def testear_que_pasa_algo
        self.class.pasa_algo_ejecutado = true
      end

      def testear_que_pasa_otra_cosa
        self.class.pasa_otra_cosa_ejecutado = true
      end

      def metodo_comun
        self.class.metodo_comun_ejecutado = true
      end
    end
  end

  before do
    clase_suite.pasa_algo_ejecutado = false
    clase_suite.pasa_otra_cosa_ejecutado = false
    clase_suite.metodo_comun_ejecutado = false
  end

  context "Test Suites" do
    it "una suite ejecuta todos los métodos que empiecen con 'testear_que_'" do
      TestSuite.new(clase_suite).testear

      expect(clase_suite.pasa_algo_ejecutado).to be true
      expect(clase_suite.pasa_otra_cosa_ejecutado).to be true
      expect(clase_suite.metodo_comun_ejecutado).to be false
    end

    it "una suite ejecuta solo los métodos que reciba por parámetro" do
      TestSuite.new(clase_suite).testear :pasa_algo

      expect(clase_suite.pasa_algo_ejecutado).to be true
      expect(clase_suite.pasa_otra_cosa_ejecutado).to be false
    end
  end

  context "TADsPec" do
    let(:otra_suite) do
      Class.new do
        class << self
          attr_accessor :ejecutada
        end

        def testear_que_pasa_algo
          self.class.ejecutada = true
          self.class.ejecutada.deberia ser true
        end
      end
    end

    before do
      otra_suite.ejecutada = false
    end

    it "tadspec ejecuta todas las suites sin recibir parámetros" do
      TADsPec.testear

      expect(clase_suite.pasa_algo_ejecutado).to be true
      expect(clase_suite.pasa_otra_cosa_ejecutado).to be true
      expect(otra_suite.ejecutada).to be true
    end

    it "tadspec ejecuta solo las suites que reciba por parámetro" do
      TADsPec.testear clase_suite

      expect(clase_suite.pasa_algo_ejecutado).to be true
      expect(clase_suite.pasa_otra_cosa_ejecutado).to be true
      expect(otra_suite.ejecutada).to be false
    end

    it "tadspec ejecuta solo los tests que reciba por parámetro" do
      TADsPec.testear clase_suite, :pasa_algo

      expect(clase_suite.pasa_algo_ejecutado).to be true
      expect(clase_suite.pasa_otra_cosa_ejecutado).to be false
    end

    it "tadspec no debería redefinir :deberia por fuera de los tests" do
      TADsPec.testear
      expect { Object.new.deberia }.to raise_error NoMethodError
    end

    it "tadspec no deberia redefinir :mockear por fuera de los tests" do
      TADsPec.testear
      expect { Object.new.mockear }.to raise_error NoMethodError
    end
  end

end

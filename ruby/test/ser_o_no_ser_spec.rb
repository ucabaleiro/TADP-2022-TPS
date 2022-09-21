require 'rspec'
require_relative '../lib/tadspec'

describe 'Ser_o_no_ser' do

  class Docente
    def initialize(edad)
      @edad = edad
    end
    def edad
      @edad
    end
    def viejo?
      @edad > 29
    end
  end
  nico = Docente.new(30)
  leandro = Docente.new(22)
  docentes = [nico, leandro]

  before do
    # Do nothing
  end

  after do
    # Do nothing
  end

  context '7 deberia ser 7' do
    include Aserciones
    it 'pasa' do
      expect(7.deberia ser 7).to eq(true)
    end
  end

  context 'true deberia ser false' do
    include Aserciones
    it 'falla' do
      expect(true .deberia ser false ).to eq(false )
    end
  end

  context 'leandro deberia tener 25' do
    include Aserciones
    it 'falla, tiene 22' do
      expect(leandro.edad.deberia ser 25).to eq(false)
    end
  end

  context 'leandro deberia ser mayor a 20' do
    include Aserciones
    it 'pasa, tiene 22' do
      expect(leandro.edad.deberia ser mayor_a 20).to eq(true )
    end
  end

  context 'leandro deberia ser menor a 25' do
    include Aserciones
    it 'pasa, tiene 22' do
      expect(leandro.edad.deberia ser menor_a 25).to eq(true )
    end
  end

  context 'la edad de leandro deberia ser una de estas' do
    include Aserciones
    it 'pasa, esta' do
      expect(leandro.edad.deberia ser uno_de_estos [7,22,"hola"]).to eq(true )
    end
  end

  context 'la edad de leandro deberia ser una de estas, pero usando varargs' do
    include Aserciones
    it 'pasa, esta' do
      expect(leandro.edad.deberia ser uno_de_estos 7,22,"hola").to eq(true )
    end
  end

  context 'nico deberia ser viejo' do
    include Aserciones
    it 'pasa, tiene 30' do
      expect(nico.deberia ser_viejo).to eq(true )
    end
  end

  context 'nico deberia ser viejo, escrito con signo de preguntas' do
    include Aserciones
    it 'pasa, tiene 30' do
      expect(nico.viejo?).to eq(true )
    end
  end

  context 'leandro deberia ser viejo' do
    include Aserciones
    it 'falla, tiene 22' do
      expect(leandro.deberia ser_viejo).to eq(false )
    end
  end

  context 'leandro deberia ser joven' do
    include Aserciones
    it 'explota, leandro no entiende el mensaje joven?' do
      expect { leandro.deberia ser_joven }.to raise_error(NoMethodError)
    end
  end


end
